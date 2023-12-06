package org.wolflink.sharine.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginVO {
    UserSimpleVO userSimpleVO;
    String token;
}
