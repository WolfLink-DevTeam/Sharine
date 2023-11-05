package org.tcpx.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.tcpx.sharine.vo.UserSimpleVO;

@Data
@AllArgsConstructor
public class UserLoginDTO {
    UserSimpleVO userSimpleVO;
    String token;
}
