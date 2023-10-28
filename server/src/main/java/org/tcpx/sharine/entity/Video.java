package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.dto.UploadVideoDTO;
import org.tcpx.sharine.enums.VideoTypeEnum;

/**
 * 视频
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = DatabaseConst.VIDEO)
public class Video {

    public Video(UploadVideoDTO uploadVideoDTO) {
        userId = uploadVideoDTO.getUserId();
        categoryId = uploadVideoDTO.getCategoryId();
        title = uploadVideoDTO.getTitle();
        content = uploadVideoDTO.getContent();
        url = uploadVideoDTO.getUrl();
        coverUrl = uploadVideoDTO.getCoverUrl();
        type = uploadVideoDTO.getType();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    Long categoryId;

    @Column(nullable = false)
    String title;

    @Column
    String content;

    @Column(nullable = false)
    String url;

    @Column(nullable = false)
    String coverUrl;

    @Column(nullable = false)
    VideoTypeEnum type;

    @UpdateTimestamp
    Long updateTime;

    @CreationTimestamp
    Long createTime;
}
