package org.wolflink.sharine.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiniuUptokenResponse {
    String key;
    String token;
}
