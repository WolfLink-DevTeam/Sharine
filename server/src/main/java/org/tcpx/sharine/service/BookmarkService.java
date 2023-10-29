package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.Bookmark;
import org.tcpx.sharine.repository.BookmarkRepository;

@Service
public class BookmarkService {

    BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Long countUserBookmarks(Long userId) {
        return bookmarkRepository.countByUserId(userId);
    }
    public Long countVideoBookmarks(Long videoId) {
        return bookmarkRepository.countByVideoId(videoId);
    }
    /**
     * 切换用户收藏视频状态
     * @param userId    用户ID
     * @param videoId   视频ID
     * @return          收藏状态，true为收藏成功，false为取消收藏
     */
    public boolean toggleBookmark(Long userId,Long videoId) {
        if(bookmarkRepository.existsByUserIdAndVideoId(userId,videoId)) {
            bookmarkRepository.deleteByUserIdAndVideoId(userId,videoId);
            return false;
        }
        Bookmark bookmark = Bookmark.builder()
                .userId(userId)
                .videoId(videoId)
                .build();
        bookmarkRepository.save(bookmark);
        return true;
    }

}
