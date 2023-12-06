package org.wolflink.sharine.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wolflink.sharine.api.entity.Category;
import org.wolflink.sharine.api.utils.BeanCopyUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO {

    Long id;

    String title;

    String url;

    Long createTime;

    Long updateTime;

    public static CategoryVO of(Category category) {
        return BeanCopyUtils.copyObject(category, CategoryVO.class);
    }
}
