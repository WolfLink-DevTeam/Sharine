package org.tcpx.sharine.vo;

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
    // 不存在字段 通过 VideoCategoryService 获取
    CategoryVO category;

    Long updateTime;

    Long createTime;

    public static VideoVO of(Video video) {
        return BeanCopyUtils.copyObject(video, VideoVO.class);
    }
}
