package org.tcpx.sharine.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.tcpx.sharine.enums.QiniuFileType;

@Data
@AllArgsConstructor
public class QiniuUptokenRequest {
    UserPass userPass;
    String rawFileName;
}
