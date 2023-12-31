package org.wolflink.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.action.BeanCopyAction;

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
}
