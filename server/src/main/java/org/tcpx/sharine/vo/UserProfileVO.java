package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.utils.BeanCopyUtils;

/**
 * 用户档案信息
 * 不包含用户敏感数据和用户投稿视频，用户收藏视频等
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileVO {
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

    public static UserProfileVO of(User user) {
        return BeanCopyUtils.copyObject(user, UserProfileVO.class);
    }
}
