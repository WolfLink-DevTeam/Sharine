package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页
 *
 * @author march
 * @since 2023/6/2 上午9:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {
    /**
     * 数据
     */
    private List<T> list;

    /**
     * 总数
     */
    private Long total;
}
