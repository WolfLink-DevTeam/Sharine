package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.constants.DatabaseConst;

@Data
@Builder
@Entity
@Table(name = DatabaseConst.VIDEO_CATEGORY)
@NoArgsConstructor
@AllArgsConstructor
public class VideoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long videoId;

    @Column(nullable = false)
    private Long categoryId;
}
