package org.tcpx.sharine.vo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.Video;
import org.tcpx.sharine.utils.BeanCopyUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoVO {
    Long id;
    // 不一致字段 userId -> author
    UserDetailVO author;

    String title;

    String url;

    String coverUrl;
    // 不存在字段 通过 VideoService 获取
    Integer favoriteCount;
    // 不存在字段 通过 VideoService 获取
    Integer bookmarkCount;
    // 不存在字段 通过 VideoCategoryService 获取
    CategoryVO category;

    String content;

    Long viewCount;

    Long updateTime;

    Long createTime;

    @SuppressWarnings("请使用相关 Service 类的 build 方法进行构造，否则会缺失参数")
    public static VideoVO of(Video video) {
        return BeanCopyUtils.copyObject(video, VideoVO.class);
    }
}
