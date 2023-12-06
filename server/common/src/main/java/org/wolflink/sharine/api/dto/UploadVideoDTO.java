package org.wolflink.sharine.api.dto;

import lombok.Data;
import org.wolflink.sharine.api.enums.VideoTypeEnum;

@Data
public class UploadVideoDTO {

    private String fileKey;
    private String hash;
    private Long categoryId;
    private String title;
    private String content;
    private String url;
    private String coverUrl;
    private VideoTypeEnum videoType;

}
