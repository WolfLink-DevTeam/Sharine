package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.utils.BeanCopyUtils;

/**
 * 完整用户档案信息
 * 包括用户关注数，粉丝数，点赞数等详细数据
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

    // 关注 不存在字段 通过 UserRelationService 获取
    Long followingCount;

    // 粉丝 不存在字段 通过 UserRelationService 获取
    Long followedCount;

    // 视频点赞 不存在字段 通过 FavoriteService 获取
    Long favouriteCount;

    // 视频收藏数 不存在字段 通过 BookmarkService 获取
    Long bookmarkCount;

    Long createTime;

    Long updateTime;

    public static UserDetailVO of(User user) {
        return BeanCopyUtils.copyObject(user, UserDetailVO.class);
    }
}
