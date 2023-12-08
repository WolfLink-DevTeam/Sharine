package org.wolflink.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wolflink.sharine.entity.Category;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoVO {
    Long id;

    UserDetailVO userDetailVO;

    String title;

    String url;

    String coverUrl;
    // 不存在字段
    Long likes;
    // 不存在字段
    Long bookmarks;
    // 不存在字段
    Long shares;
    // 不存在字段
    Long views;
    // 不存在字段
    Category category;

    String content;

    Long updateTime;

    Long createTime;


}
