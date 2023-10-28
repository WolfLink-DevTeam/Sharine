package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    List<TagVO> tags;


    Long updateTime;

    Long createTime;
}
