package org.wolflink.sharine.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long categoryId;
}
