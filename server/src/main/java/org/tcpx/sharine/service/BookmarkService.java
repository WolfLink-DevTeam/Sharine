package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
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

}
