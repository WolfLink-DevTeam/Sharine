package org.wolflink.sharine.action;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.UserRepository;

@Component
@AllArgsConstructor
public class SessionAction {

    private final UserRepository userRepository;

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
}
