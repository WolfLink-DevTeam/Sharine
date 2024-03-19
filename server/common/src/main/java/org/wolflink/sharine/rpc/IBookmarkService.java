package org.wolflink.sharine.rpc;

import org.wolflink.sharine.entity.Bookmark;

import java.util.List;

public interface IBookmarkService {
    void bookmarkVideo(Long userId, Long videoId);

    boolean hasBookmarkVideo(Long userId, Long videoId);

    Bookmark getBookmark(Long userId, Long videoId);

    void undoBookmarkVideo(Long userId, Long videoId);

    List<Long> findUserBookmarkVideoIds(Long userId);

    List<Long> findUserBookmarkVideoIds(Long userId, Integer current, Integer size);
}
