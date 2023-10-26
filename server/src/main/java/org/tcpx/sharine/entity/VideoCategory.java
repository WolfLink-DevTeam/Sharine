package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tcpx.sharine.constants.DatabaseConstants;

@Data
@Builder
@Enabled
@Entity
@Table(name = DatabaseConstants.VIDEO_CATEGORY)
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
