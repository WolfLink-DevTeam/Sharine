package org.wolflink.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPass {

    private String account;

    private String password;

    private String verificationCode;

}
