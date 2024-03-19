package org.wolflink.sharine.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.entity.Bookmark;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.BookmarkRepository;
import org.wolflink.sharine.rpc.IBookmarkService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkService implements IBookmarkService {

    final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    /**
     * 用户收藏视频
     *
     * @param userId  用户ID
     * @param videoId 视频ID
     */
    @Override
    public void bookmarkVideo(Long userId, Long videoId) {
        // 已经收藏
        if (hasBookmarkVideo(userId, videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_EXIST);
        }
        Bookmark bookmark = Bookmark.builder().userId(userId).videoId(videoId).build();
        bookmarkRepository.save(bookmark);
    }
    @Override
    public boolean hasBookmarkVideo(Long userId, Long videoId) {
        return bookmarkRepository.existsByUserIdAndVideoId(userId,videoId);
    }
    @Override
    public Bookmark getBookmark(Long userId, Long videoId) {
        return bookmarkRepository.findByUserIdAndVideoId(userId,videoId)
                .orElseThrow(()->new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
    }

    /**
     * 用户撤销收藏视频
     *
     * @param userId  用户ID
     * @param videoId 视频ID
     */
    @Override
    public void undoBookmarkVideo(Long userId, Long videoId) {
        // 数据不存在
        if (!bookmarkRepository.existsByUserIdAndVideoId(userId, videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_NOT_EXIST);
        }
        bookmarkRepository.deleteByUserIdAndVideoId(userId, videoId);
    }

    @Override
    public List<Long> findUserBookmarkVideoIds(Long userId) {
        List<Bookmark> byUserId = bookmarkRepository.findByUserId(userId);
        return byUserId.stream().map(Bookmark::getVideoId).collect(Collectors.toList());
    }
    @Override
    public List<Long> findUserBookmarkVideoIds(Long userId, Integer current, Integer size) {
        Pageable pageable = PageRequest.of(current, size);

        List<Bookmark> byUserId = bookmarkRepository.findByUserId(userId, pageable);

        return byUserId.stream().map(Bookmark::getVideoId).collect(Collectors.toList());
    }
}
