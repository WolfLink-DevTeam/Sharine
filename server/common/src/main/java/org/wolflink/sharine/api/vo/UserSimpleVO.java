package org.wolflink.sharine.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wolflink.sharine.api.entity.User;
import org.wolflink.sharine.api.utils.BeanCopyUtils;

/**
 * 用户档案信息
 * 不包含用户关注数，粉丝数，点赞数等详细数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleVO {
    Long id;

    String nickname;

    String avatar;

    String content;

    Long createTime;

    Long updateTime;

    public static UserSimpleVO of(User user) {
        return BeanCopyUtils.copyObject(user, UserSimpleVO.class);
    }
}
