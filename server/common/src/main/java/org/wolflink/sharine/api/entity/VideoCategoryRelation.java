package org.wolflink.sharine.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wolflink.sharine.api.constants.DatabaseConst;

@Data
@Builder
@Entity
@Table(name = DatabaseConst.VIDEO_CATEGORY_RELATION)
@NoArgsConstructor
@AllArgsConstructor
public class VideoCategoryRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long videoId;

    @Column(nullable = false)
    private Long categoryId;
}
