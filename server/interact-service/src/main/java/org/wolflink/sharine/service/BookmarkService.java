package org.wolflink.sharine.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.dto.ConditionDTO;
import org.wolflink.sharine.entity.Bookmark;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.BookmarkRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

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
    public void bookmarkVideo(Long userId, Long videoId) {
        // 已经收藏
        if (hasBookmarkVideo(userId, videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_EXIST);
        }
        Bookmark bookmark = Bookmark.builder().userId(userId).videoId(videoId).build();
        bookmarkRepository.save(bookmark);
        // TODO 不应该聚合到此处
//        userRelationService.follow(userId,authorId);
    }
    public boolean hasBookmarkVideo(Long userId,Long videoId) {
        return bookmarkRepository.existsByUserIdAndVideoId(userId,videoId);
    }

    /**
     * 用户撤销收藏视频
     *
     * @param userId  用户ID
     * @param videoId 视频ID
     */
    public void undoBookmarkVideo(Long userId, Long videoId) {
        // 数据不存在
        if (!bookmarkRepository.existsByUserIdAndVideoId(userId, videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_NOT_EXIST);
        }
        bookmarkRepository.deleteByUserIdAndVideoId(userId, videoId);
        // TODO 不应该聚合到此处
//        userRelationService.undoFollow(userId,authorId);
    }

    public List<Long> findUserBookmarkVideoIds(Long userId) {
        List<Bookmark> byUserId = bookmarkRepository.findByUserId(userId);
        return byUserId.stream().map(Bookmark::getVideoId).collect(Collectors.toList());
    }
    public List<Long> findUserBookmarkVideoIds(Long userId, ConditionDTO conditionDTO) {
        Pageable pageable = PageRequest.of(
                conditionDTO.getCurrent(),
                conditionDTO.getSize()
        );

        List<Bookmark> byUserId = bookmarkRepository.findByUserId(userId, pageable);

        return byUserId.stream().map(Bookmark::getVideoId).collect(Collectors.toList());
    }
}
