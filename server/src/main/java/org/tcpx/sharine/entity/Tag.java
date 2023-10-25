package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConstants;
import org.tcpx.sharine.dto.TagDTO;

/**
 * 标签
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.TAG)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;

    public static Tag of(TagDTO tagDTO) {
        return Tag.builder()
                .title(tagDTO.getTitle())
                .build();
    }
}
