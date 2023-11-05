package org.tcpx.sharine.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.enums.QiniuFileType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiniuUptokenResponse {
    String key;
    String token;
}
