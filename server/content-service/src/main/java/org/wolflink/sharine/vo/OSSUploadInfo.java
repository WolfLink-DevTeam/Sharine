package org.wolflink.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OSSUploadInfo {
    String fileKey;
    String accessToken;
}
