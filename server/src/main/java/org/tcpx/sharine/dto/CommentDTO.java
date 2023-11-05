package org.tcpx.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    UserPass userPass;

    Long replyId;

    Long videoId;

    String content;
}
