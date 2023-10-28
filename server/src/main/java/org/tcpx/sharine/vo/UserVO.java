package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.utils.BeanCopyUtils;

import java.util.List;

/**
 * 完整用户信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    Long id;

    String nickname;

    String avatar;

    String content;

    // 关注
    Long followingCOunt;

    // 粉丝
    Long followedCount;

    // 视频点赞
    Long favouriteCount;

    List<VideoVO> videos;

    Long createTime;

    Long updateTime;

    public static UserVO of(User user) {
        return BeanCopyUtils.copyObject(user, UserVO.class);
    }
}
