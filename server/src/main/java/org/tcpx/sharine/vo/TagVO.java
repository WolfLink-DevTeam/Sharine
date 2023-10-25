package org.tcpx.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.entity.Tag;
import org.tcpx.sharine.utils.BeanCopyUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagVO {

    Long id;

    String title;

    Long createTime;

    Long updateTime;

    public static TagVO of(Tag tag) {
        return BeanCopyUtils.copyObject(tag, TagVO.class);
    }
}
