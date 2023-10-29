package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.entity.Video;
import org.tcpx.sharine.utils.BeanCopyUtils;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoVO {
    Long id;

    UserProfileVO author;

    String title;

    String url;

    String coverUrl;

    CategoryVO category;

    List<TagVO> tags;

    Long updateTime;

    Long createTime;
    public static VideoVO of(Video video) {
        return BeanCopyUtils.copyObject(video, VideoVO.class);
    }
}
