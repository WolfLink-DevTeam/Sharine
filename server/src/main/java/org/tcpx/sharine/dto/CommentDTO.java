package org.tcpx.sharine.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class CommentDTO {
    Long replyId;

    Long videoId;

    String content;
}
