package org.tcpx.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsernamePassword {
    private String username;

    private String password;
}
