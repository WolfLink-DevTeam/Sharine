package org.wolflink.sharine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    // TODO 新增项，前端需补充
    Long userId;

    Long replyId;

    Long videoId;

    String content;
}
