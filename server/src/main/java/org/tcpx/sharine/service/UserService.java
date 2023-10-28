package org.tcpx.sharine.service;

import jakarta.mail.MessagingException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.constants.RedisPrefixConst;
import org.tcpx.sharine.constants.UserConst;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.UsernamePassword;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.ErrorException;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.UserRepository;
import org.tcpx.sharine.utils.EncryptionUtil;
import org.tcpx.sharine.utils.StringUtils;
import org.tcpx.sharine.vo.UserProfileVO;
import org.tcpx.sharine.vo.UserVO;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = DatabaseConst.USER)
public class UserService {

    final UserRepository userRepository;

    final FavoriteService favoriteService;

    final UserRelationService userRelationService;

    final RedisService redisService;

    final EmailService emailService;

    final VideoService videoService;

    public UserService(UserRepository userRepository, FavoriteService favoriteService, UserRelationService userRelationService, RedisService redisService, EmailService emailService, VideoService videoService) {
        this.userRepository = userRepository;
        this.favoriteService = favoriteService;
        this.userRelationService = userRelationService;
        this.redisService = redisService;
        this.emailService = emailService;
        this.videoService = videoService;
    }

    public UserProfileVO login(UsernamePassword usernamePassword) {
        Optional<User> byUsername = userRepository.findByUsername(usernamePassword.getUsername());
        if (byUsername.isEmpty()) {
            throw new ErrorException(StatusCodeEnum.DATA_NOT_EXIST);
        }

        User user = byUsername.get();
        if (!EncryptionUtil.match(usernamePassword.getPassword(), user.getPassword())) {
            throw new ErrorException(StatusCodeEnum.PASSWORD_NOT_MATCHED);
        }

        return buildUserProfileVO(user);
    }

    public UserProfileVO register(UsernamePassword usernamePassword) {
        String username = usernamePassword.getUsername();
        boolean checked = StringUtils.checkEmail(username);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        String code = (String) redisService.get(RedisPrefixConst.TOKEN + username);
        // 验证码错误
        if (code == null || !code.equals(usernamePassword.getCode())) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        // 用户已存在
        if (userRepository.existsByUsername(username).equals(true)) {
            throw new ErrorException(StatusCodeEnum.DATA_EXIST);
        }

        User user = User.builder()
                .username(username)
                .password(EncryptionUtil.encode(usernamePassword.getPassword()))
                .nickname(UserConst.DEFAULT_NICKNAME)
                .avatar(UserConst.DEFAULT_AVATAR)
                .content(UserConst.DEFAULT_CONTENT)
                .build();

        user = userRepository.save(user);
        return buildUserProfileVO(user);
    }

    public UserProfileVO forget(UsernamePassword usernamePassword) {
        String username = usernamePassword.getUsername();
        boolean checked = StringUtils.checkEmail(username);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        String code = (String) redisService.get(RedisPrefixConst.TOKEN + username);
        // 验证码错误
        if (code == null || !code.equals(usernamePassword.getCode())) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        // 用户不存在
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new ErrorException(StatusCodeEnum.DATA_NOT_EXIST);
        }

        // 修改数据
        User user = byUsername.get();
        user.setPassword(EncryptionUtil.encode(usernamePassword.getPassword()));
        user = userRepository.save(user);

        return buildUserProfileVO(user);
    }

    public void sendCode(UsernamePassword usernamePassword) {
        String username = usernamePassword.getUsername();
        boolean checked = StringUtils.checkEmail(username);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        if (redisService.get(username) != null) {
            throw new WarnException(StatusCodeEnum.FAIL.getCode(), "请在" + redisService.getExpire(username) + "秒后重新发送");
        }

        // 五分钟过期
        String code = StringUtils.getRandomCode(6);
        redisService.set(RedisPrefixConst.TOKEN + username, code, 5 * 60);

        try {
            emailService.sendCode(username, code, 5L);
        } catch (MessagingException e) {
            throw new ErrorException("邮件发送失败");
        }
    }

    private UserProfileVO buildUserProfileVO(User user) {
        UserProfileVO userVO = UserProfileVO.of(user);
        userVO.setFavouriteCount(favoriteService.countUserFavoured(user.getId()));
        userVO.setFollowingCOunt(userRelationService.countUserFollowing(user.getId()));
        userVO.setFollowedCount(userRelationService.countUserFollowed(user.getId()));
        return userVO;
    }

    public UserVO findUserInfo(Long userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new WarnException(StatusCodeEnum.DATA_NOT_EXIST);
        }

        User user = byId.get();

        UserVO userVO = UserVO.of(user);
        userVO.setFavouriteCount(favoriteService.countUserFavoured(user.getId()));
        userVO.setFollowingCOunt(userRelationService.countUserFollowing(user.getId()));
        userVO.setFollowedCount(userRelationService.countUserFollowed(user.getId()));

        return userVO;
    }
}
