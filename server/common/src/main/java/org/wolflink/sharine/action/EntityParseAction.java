package org.wolflink.sharine.action;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.vo.CommentVO;
import org.wolflink.sharine.vo.UserDetailVO;
import org.wolflink.sharine.vo.UserSimpleVO;

/**
 * 对象类型转换操作
 * 简单转换 Entity -> VO 不会组装缺失的复杂参数(涉及到具体微服务模块的)
 * 负责转换 DTO -> Entity
 */
@Component
@AllArgsConstructor
public class EntityParseAction {
    private final BeanCopyAction beanCopyAction;
    public CommentVO parse(Comment comment) {
        return beanCopyAction.copyObject(comment, CommentVO.class);
    }
    public UserDetailVO parse(UserSimpleVO userSimpleVO) {
        return beanCopyAction.copyObject(userSimpleVO, UserDetailVO.class);
    }
    public UserSimpleVO parse(User user) {
        return beanCopyAction.copyObject(user, UserSimpleVO.class);
    }
}
