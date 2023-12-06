package org.tcpx.sharine.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.entity.Bookmark;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.BookmarkRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    final BookmarkRepository bookmarkRepository;
    final UserRelationService userRelationService;

    public BookmarkService(BookmarkRepository bookmarkRepository,UserRelationService userRelationService) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRelationService = userRelationService;
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
        userRelationService.followVideo(userId,videoId);
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
        userRelationService.undoFollowVideo(userId,videoId);
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
