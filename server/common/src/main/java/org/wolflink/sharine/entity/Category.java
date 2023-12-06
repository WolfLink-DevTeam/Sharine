package org.wolflink.sharine.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constants.DatabaseConst;

/**
 * 分类
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConst.CATEGORY)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String url;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;

    public Category(String title,String url) {
        this.title = title;
        this.url = url;
    }
}
