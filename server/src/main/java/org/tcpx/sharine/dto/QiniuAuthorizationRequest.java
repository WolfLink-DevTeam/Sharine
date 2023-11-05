package org.tcpx.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiniuAuthorizationRequest {
    // TODO UserPass
    String url;
    String jsonBody;
}
