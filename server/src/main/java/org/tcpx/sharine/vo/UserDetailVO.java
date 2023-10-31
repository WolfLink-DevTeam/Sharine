package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.utils.BeanCopyUtils;

import java.util.List;

/**
 * 完整用户档案信息
 * 不包括用户敏感数据
 * 包括用户收藏视频列表，用户投稿视频列表等
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailVO {
    Long id;

    String nickname;

    String avatar;

    String content;

    // 关注
    Long followingCount;

    // 粉丝
    Long followedCount;

    // 视频点赞
    Long favouriteCount;

    // 视频收藏数
    Long bookmarkCount;

    Long createTime;

    Long updateTime;

    public static UserDetailVO of(User user) {
        return BeanCopyUtils.copyObject(user, UserDetailVO.class);
    }
}
