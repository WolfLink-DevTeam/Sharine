package org.tcpx.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserPass {

    private String account;

    private String password;

    private String verificationCode;

}
