package org.wolflink.sharine.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    Long replyId;

    Long videoId;

    String content;
}
