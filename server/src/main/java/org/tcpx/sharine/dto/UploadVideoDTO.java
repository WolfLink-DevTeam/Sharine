package org.tcpx.sharine.dto;

import lombok.Data;
import org.tcpx.sharine.enums.VideoTypeEnum;

@Data
public class UploadVideoDTO {

    private Long userId;
    private String fileName;
    private String md5;
    private Long categoryId;
    private String title;
    private String content;
    private String url;
    private String coverUrl;
    private VideoTypeEnum type;

}
