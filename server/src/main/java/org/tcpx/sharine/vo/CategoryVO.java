package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.Category;
import org.tcpx.sharine.utils.BeanCopyUtils;
import org.tcpx.sharine.utils.IOC;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO {

    Long id;

    String title;

    Long createTime;

    Long updateTime;

    public static CategoryVO of(Category category) {
        return IOC.getBean(BeanCopyUtils.class).copyObject(category, CategoryVO.class);
    }
}
