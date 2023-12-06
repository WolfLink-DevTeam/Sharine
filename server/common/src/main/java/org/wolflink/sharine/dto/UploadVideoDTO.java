package org.wolflink.sharine.dto;

import lombok.Data;
import org.wolflink.sharine.enums.VideoTypeEnum;

@Data
public class UploadVideoDTO {

    // TODO 新增
    private Long userId;
    private String fileKey;
    private String hash;
    private Long categoryId;
    private String title;
    private String content;
    private String url;
    private String coverUrl;
    private VideoTypeEnum videoType;

}
