package org.wolflink.sharine.service;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.EmailAction;
import org.wolflink.sharine.action.EncryptAction;
import org.wolflink.sharine.action.RedisAction;
import org.wolflink.sharine.action.StringAction;
import org.wolflink.sharine.constant.RedisPrefixConst;
import org.wolflink.sharine.constant.UserConst;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.ErrorException;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RedisAction redisAction;
    private final EmailAction emailAction;
    private final EncryptAction encryptAction;
    private final StringAction stringAction;

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
    }
    public User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
    }
    public void updateUser(Long id,String nickname,String avatar,String content) {
        User user = userRepository.findById(id).orElseThrow(()->new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        if(nickname != null) user.setNickname(nickname);
        if(avatar != null) user.setAvatar(avatar);
        if(content != null) user.setContent(content);
        userRepository.save(user);
    }
    /**
     * 尝试登录
     * @param account   用户账号
     * @param password  用户密码
     */
    public void login(String account,String password) {
        User user = userRepository.findByEmail(account).orElseThrow(()-> new ErrorException(StatusCodeEnum.DATA_NOT_EXIST));
        if (!encryptAction.match(password, user.getPassword())) {
            throw new ErrorException(StatusCodeEnum.PASSWORD_NOT_MATCHED);
        }
        StpUtil.login(user.getId());
        System.out.println("用户"+user.getId()+"登录成功");
    }


    public void register(String ipAddress,String email,String password,String verificationCode) {
        emailAction.mailVerify(ipAddress,email, verificationCode);
        // 用户已存在
        if (userRepository.existsByEmail(email).equals(true)) {
            throw new ErrorException(StatusCodeEnum.DATA_EXIST);
        }
        User user = User.builder()
                .email(email)
                .password(encryptAction.encode(password))
                .nickname("用户"+UUID.randomUUID().toString().substring(0,8))
                .avatar(UserConst.DEFAULT_AVATAR)
                .content(UserConst.DEFAULT_CONTENT)
                .build();
        userRepository.save(user);
    }

    public void changePassword(String account,String newPassword,String verificationCode) {
        boolean checked = stringAction.checkEmail(account);
        // 非邮箱
        if (!checked) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        String code = (String) redisAction.get(RedisPrefixConst.TOKEN + account);
        // 验证码错误
        if (code == null || !code.equals(verificationCode)) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }

        // 用户不存在
        Optional<User> byUsername = userRepository.findByEmail(account);
        if (byUsername.isEmpty()) {
            throw new ErrorException(StatusCodeEnum.DATA_NOT_EXIST);
        }

        // 修改数据
        User user = byUsername.get();
        user.setPassword(encryptAction.encode(newPassword));
        userRepository.save(user);
    }

    public void requestForCode(String ipAddress,String account) {
        boolean checked = stringAction.checkEmail(account);
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
            emailAction.sendCode(account, code, 5L);
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
//    public UserSimpleVO findUserSimpleInfo(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
//        return objectParseAction.parse(user);
//    }
//    public UserSimpleVO findUserSimpleInfo(String account) {
//        User user = userRepository.findByAccount(account)
//                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
//        return objectParseAction.parse(user);
//    }

    /**
     * 批量查询用户粗略信息
     *
     * @param userIds 用户ID
     * @return 用户粗略信息
     */
//    public Map<Long, UserSimpleVO> findUserSimpleInfo(List<Long> userIds) {
//        userIds = userIds.stream().distinct().collect(Collectors.toList());
//
//        List<User> allByIdIn = userRepository.findAllById(userIds);
//
//        return allByIdIn.stream().map(objectParseAction::parse)
//                .collect(
//                        Collectors.toMap(UserSimpleVO::getId, person -> person)
//                );
//    }

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
//            beenFavoriteCount.updateAndGet(v -> v + upvoteRepository.countByVideoId(video.getId()));
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
