package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.Bookmark;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.BookmarkRepository;

@Service
public class BookmarkService {

    BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    /**
     * 用户收藏视频
     * @param userId    用户ID
     * @param videoId   视频ID
     */
    public void bookmarkVideo(Long userId,Long videoId) {
        // 已经点赞了
        if(bookmarkRepository.existsByUserIdAndVideoId(userId,videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_EXIST);
        }
        Bookmark bookmark = Bookmark.builder()
                .userId(userId)
                .videoId(videoId)
                .build();
        bookmarkRepository.save(bookmark);
    }
    /**
     * 用户撤销收藏视频
     * @param userId    用户ID
     * @param videoId   视频ID
     */
    public void undoBookmarkVideo(Long userId,Long videoId) {
        // 数据不存在
        if(!bookmarkRepository.existsByUserIdAndVideoId(userId,videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_NOT_EXIST);
        }
        bookmarkRepository.deleteByUserIdAndVideoId(userId,videoId);
    }

}
