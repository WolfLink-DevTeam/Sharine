package org.tcpx.sharine.service;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.RedisPrefixConst;
import org.tcpx.sharine.constants.UserConst;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.ErrorException;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.BookmarkRepository;
import org.tcpx.sharine.repository.FavoriteRepository;
import org.tcpx.sharine.repository.UserRepository;
import org.tcpx.sharine.utils.EncryptionUtil;
import org.tcpx.sharine.utils.StringUtils;
import org.tcpx.sharine.vo.UserDetailVO;
import org.tcpx.sharine.vo.UserSimpleVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Resource
    UserRepository userRepository;
    @Resource
    BookmarkRepository bookmarkRepository;
    @Resource
    FavoriteRepository favoriteRepository;
    @Resource
    FavoriteService favoriteService;
    @Resource
    UserRelationService userRelationService;
    @Resource
    RedisService redisService;
    @Resource
    EmailService emailService;
    @Resource
    @Lazy
    VideoService videoService;


    /**
     * 获取当前连接登录的用户ID(会检查用户登录状态)
     * @return  用户ID
     */
    public Long getSessionUserId() {
        if(!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        return StpUtil.getLoginIdAsLong();
    }
    /**
     * 获取当前连接登录的用户对象(会检查用户登录状态)
     * @return  用户
     */
    public User getSessionUser() {
        return userRepository.findById(getSessionUserId())
                .orElseThrow(()->new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
    }
    public UserSimpleVO login(UserPass userPass) {
        User user = verifyUserPass(userPass);
        StpUtil.login(user.getId());
        System.out.println("用户"+user.getId()+"登录成功");
        return buildUserSimpleVO(user);
    }
    /**
     * 验证用户通行证的账号密码是否正确
     * @param userPass  用户通行证
     * @return          用户信息
     */
    @Deprecated
    public User verifyUserPass(UserPass userPass) {
        User user = userRepository.findByAccount(userPass.getAccount()).orElseThrow(()-> new ErrorException(StatusCodeEnum.DATA_NOT_EXIST));
        if (!EncryptionUtil.match(userPass.getPassword(), user.getPassword())) {
            throw new ErrorException(StatusCodeEnum.PASSWORD_NOT_MATCHED);
        }
        return user;
    }

    public UserSimpleVO register(String ipAddress,UserPass userPass) {
        String account = userPass.getAccount();
        emailService.mailVerify(ipAddress,account, userPass.getVerificationCode());

        // 用户已存在
        if (userRepository.existsByAccount(account).equals(true)) {
            throw new ErrorException(StatusCodeEnum.DATA_EXIST);
        }

        User user = User.builder()
                .account(account)
                .password(EncryptionUtil.encode(userPass.getPassword()))
                .nickname("用户"+UUID.randomUUID().toString().substring(0,8))
                .avatar(UserConst.DEFAULT_AVATAR)
                .content(UserConst.DEFAULT_CONTENT)
                .build();

        user = userRepository.save(user);
        return buildUserSimpleVO(user);
    }

    public UserSimpleVO changePassword(UserPass userPass) {
        String account = userPass.getAccount();
        boolean checked = StringUtils.checkEmail(account);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        String code = (String) redisService.get(RedisPrefixConst.TOKEN + account);
        // 验证码错误
        if (code == null || !code.equals(userPass.getVerificationCode())) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        // 用户不存在
        Optional<User> byUsername = userRepository.findByAccount(account);
        if (byUsername.isEmpty()) {
            throw new ErrorException(StatusCodeEnum.DATA_NOT_EXIST);
        }

        // 修改数据
        User user = byUsername.get();
        user.setPassword(EncryptionUtil.encode(userPass.getPassword()));
        user = userRepository.save(user);

        return buildUserSimpleVO(user);
    }

    public void requestForCode(String ipAddress,UserPass userPass) {
        String username = userPass.getAccount();
        boolean checked = StringUtils.checkEmail(username);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }
        String cacheKey = "email-cache:"+ipAddress;
        if (redisService.get(cacheKey) != null) {
            throw new WarnException(StatusCodeEnum.FAIL.getCode(), "请在" + redisService.getExpire(cacheKey) + "秒后重新发送");
        }

        // 五分钟过期
        String code = StringUtils.getRandomCode(6);
        redisService.set(cacheKey, code, 5 * 60);

        try {
            emailService.sendCode(username, code, 5L);
        } catch (MessagingException e) {
            throw new ErrorException("邮件发送失败");
        }
    }

    public UserSimpleVO buildUserSimpleVO(User user) {
        return UserSimpleVO.of(user);
    }

    public UserDetailVO buildUserDetailVO(User user) {
        return UserDetailVO.of(user);
    }

    /**
     * 查询用户粗略档案信息
     *
     * @param userId 用户ID
     * @return 用户粗略档案信息
     */
    public UserSimpleVO findUserSimpleInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        return buildUserSimpleVO(user);
    }
    public UserSimpleVO findUserSimpleInfo(String account) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        return buildUserSimpleVO(user);
    }

    /**
     * 批量查询用户粗略信息
     *
     * @param userIds 用户ID
     * @return 用户粗略信息
     */
    public Map<Long, UserSimpleVO> findUserSimpleInfo(List<Long> userIds) {
        userIds = userIds.stream().distinct().collect(Collectors.toList());

        List<User> allByIdIn = userRepository.findAllById(userIds);

        return allByIdIn.stream().map(this::buildUserSimpleVO)
                .collect(
                        Collectors.toMap(UserSimpleVO::getId, person -> person)
                );
    }

    /**
     * 查询用户详细信息
     *
     * @param userId 用户ID
     * @return 用户详细信息
     */
    public UserDetailVO findUserDetailInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));

        UserDetailVO userDetailVO = UserDetailVO.of(user);
        userDetailVO.setFavouriteCount(favoriteService.countUserFavorite(userId));
        userDetailVO.setFollowingCount(userRelationService.countUserFollowing(userId));
        userDetailVO.setFollowedCount(userRelationService.countUserFollowed(userId));
        userDetailVO.setBookmarkCount(bookmarkRepository.countByUserId(userId).longValue());
        AtomicReference<Long> beenFavoriteCount = new AtomicReference<>(0L);
        AtomicReference<Long> beenViewCount = new AtomicReference<>(0L);
        videoService.findVideosByUserId(userId).forEach(video -> {
            beenFavoriteCount.updateAndGet(v -> v + favoriteRepository.countByVideoId(video.getId()));
            beenViewCount.updateAndGet(v -> v + video.getViewCount());
        });
        userDetailVO.setBeenFavoriteCount(beenFavoriteCount.get());
        userDetailVO.setBeenViewCount(beenViewCount.get());
        return userDetailVO;
    }

    /**
     * 批量查询用户详细信息
     *
     * @param userIds 用户ID
     * @return 用户详细信息
     */
    public Map<Long, UserDetailVO> findUserDetailInfo(List<Long> userIds) {
        userIds = userIds.stream().distinct().collect(Collectors.toList());

        List<User> users = userRepository.findAllById(userIds);

        return users.stream().map(user -> {
                    UserDetailVO result = buildUserDetailVO(user);
                    result.setBookmarkCount(bookmarkRepository.countByUserId(user.getId()).longValue());
                    result.setFollowedCount(userRelationService.countUserFollowed(user.getId()));
                    result.setFollowingCount(userRelationService.countUserFollowing(user.getId()));
                    result.setFavouriteCount(favoriteService.countUserFavorite(user.getId()));
                    return result;
                })
                .collect(
                        Collectors.toMap(UserDetailVO::getId, person -> person)
                );
    }
}
