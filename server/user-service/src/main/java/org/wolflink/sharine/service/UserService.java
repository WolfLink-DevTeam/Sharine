package org.wolflink.sharine.service;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.*;
import org.wolflink.sharine.constant.RedisPrefixConst;
import org.wolflink.sharine.constant.UserConst;
import org.wolflink.sharine.dto.UserPass;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.ErrorException;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.BookmarkRepository;
import org.wolflink.sharine.repository.FavoriteRepository;
import org.wolflink.sharine.repository.UserRepository;
import org.wolflink.sharine.vo.UserSimpleVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final FavoriteRepository favoriteRepository;
    private final RedisAction redisAction;
    private final EmailAction emailAction;
    private final ObjectParseAction objectParseAction;
    private final EncryptAction encryptAction;
    private final StringAction stringAction;

    public UserSimpleVO login(UserPass userPass) {
        User user = verifyUserPass(userPass);
        StpUtil.login(user.getId());
        System.out.println("用户"+user.getId()+"登录成功");
        return objectParseAction.parse(user);
    }
    /**
     * 验证用户通行证的账号密码是否正确
     * @param userPass  用户通行证
     * @return          用户信息
     */
    @Deprecated
    public User verifyUserPass(UserPass userPass) {
        User user = userRepository.findByAccount(userPass.getAccount()).orElseThrow(()-> new ErrorException(StatusCodeEnum.DATA_NOT_EXIST));
        if (!encryptAction.match(userPass.getPassword(), user.getPassword())) {
            throw new ErrorException(StatusCodeEnum.PASSWORD_NOT_MATCHED);
        }
        return user;
    }

    public UserSimpleVO register(String ipAddress,UserPass userPass) {
        String account = userPass.getAccount();
        emailAction.mailVerify(ipAddress,account, userPass.getVerificationCode());

        // 用户已存在
        if (userRepository.existsByAccount(account).equals(true)) {
            throw new ErrorException(StatusCodeEnum.DATA_EXIST);
        }

        User user = User.builder()
                .account(account)
                .password(encryptAction.encode(userPass.getPassword()))
                .nickname("用户"+UUID.randomUUID().toString().substring(0,8))
                .avatar(UserConst.DEFAULT_AVATAR)
                .content(UserConst.DEFAULT_CONTENT)
                .build();

        user = userRepository.save(user);
        return objectParseAction.parse(user);
    }

    public UserSimpleVO changePassword(UserPass userPass) {
        String account = userPass.getAccount();
        boolean checked = stringAction.checkEmail(account);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        String code = (String) redisAction.get(RedisPrefixConst.TOKEN + account);
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
        user.setPassword(encryptAction.encode(userPass.getPassword()));
        user = userRepository.save(user);

        return objectParseAction.parse(user);
    }

    public void requestForCode(String ipAddress,UserPass userPass) {
        String username = userPass.getAccount();
        boolean checked = stringAction.checkEmail(username);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }
        String cacheKey = "email-cache:"+ipAddress;
        if (redisAction.get(cacheKey) != null) {
            throw new WarnException(StatusCodeEnum.FAIL.getCode(), "请在" + redisAction.getExpire(cacheKey) + "秒后重新发送");
        }

        // 五分钟过期
        String code = stringAction.getRandomCode(6);
        redisAction.set(cacheKey, code, 5 * 60);

        try {
            emailAction.sendCode(username, code, 5L);
        } catch (MessagingException e) {
            throw new ErrorException("邮件发送失败");
        }
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
        return objectParseAction.parse(user);
    }
    public UserSimpleVO findUserSimpleInfo(String account) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        return objectParseAction.parse(user);
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

        return allByIdIn.stream().map(objectParseAction::parse)
                .collect(
                        Collectors.toMap(UserSimpleVO::getId, person -> person)
                );
    }

    /**
     * TODO 聚合查询 查询用户详细信息
     *
     * @param userId 用户ID
     * @return 用户详细信息
     */
//    public UserDetailVO findUserDetailInfo(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
//
//        UserDetailVO userDetailVO = UserDetailVO.of(user);
//        userDetailVO.setFavouriteCount(favoriteService.countUserFavorite(userId));
//        userDetailVO.setFollowingCount(userRelationService.countUserFollowing(userId));
//        userDetailVO.setFollowedCount(userRelationService.countUserFollowed(userId));
//        userDetailVO.setBookmarkCount(bookmarkRepository.countByUserId(userId).longValue());
//        AtomicReference<Long> beenFavoriteCount = new AtomicReference<>(0L);
//        AtomicReference<Long> beenViewCount = new AtomicReference<>(0L);
//        videoService.findVideosByUserId(userId).forEach(video -> {
//            beenFavoriteCount.updateAndGet(v -> v + favoriteRepository.countByVideoId(video.getId()));
//            beenViewCount.updateAndGet(v -> v + video.getViewCount());
//        });
//        userDetailVO.setBeenFavoriteCount(beenFavoriteCount.get());
//        userDetailVO.setBeenViewCount(beenViewCount.get());
//        return userDetailVO;
//    }

    /**
     * TODO 聚合查询 批量查询用户详细信息
     *
     * @param userIds 用户ID
     * @return 用户详细信息
     */
//    public Map<Long, UserDetailVO> findUserDetailInfo(List<Long> userIds) {
//        userIds = userIds.stream().distinct().collect(Collectors.toList());
//
//        List<User> users = userRepository.findAllById(userIds);
//
//        return users.stream().map(user -> {
//                    UserDetailVO result = buildUserDetailVO(user);
//                    result.setBookmarkCount(bookmarkRepository.countByUserId(user.getId()).longValue());
//                    result.setFollowedCount(userRelationService.countUserFollowed(user.getId()));
//                    result.setFollowingCount(userRelationService.countUserFollowing(user.getId()));
//                    result.setFavouriteCount(favoriteService.countUserFavorite(user.getId()));
//                    return result;
//                })
//                .collect(
//                        Collectors.toMap(UserDetailVO::getId, person -> person)
//                );
//    }
}
