package org.tcpx.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 查询条件
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDTO {
    /**
     * 页码
     */
    @Min(1)
    @Builder.Default
    private Integer current = 1;
    /**
     * 条数
     */
    @Min(10)
    @Max(500)
    @Builder.Default
    private Integer size = 10;
    /**
     * 搜索内容
     */
    @Builder.Default
    private String keywords = "";

    @Builder.Default
    private Long id = 0L;
}
