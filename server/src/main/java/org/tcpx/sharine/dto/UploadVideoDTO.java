package org.tcpx.sharine.dto;

import lombok.Data;
import org.tcpx.sharine.enums.VideoTypeEnum;

@Data
public class UploadVideoDTO {

    private UserPass userPass;
    private String fileKey;
    private String hash;
    private Long categoryId;
    private String title;
    private String content;
    private String url;
    private String coverUrl;
    private VideoTypeEnum videoType;
}
