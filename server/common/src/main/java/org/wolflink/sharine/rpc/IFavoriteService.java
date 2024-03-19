package org.wolflink.sharine.rpc;

import java.util.List;

public interface IFavoriteService {
    void favoriteVideo(Long userId, Long videoId);

    boolean hasFavoriteVideo(Long userId, Long videoId);

    void undoFavoriteVideo(Long userId, Long videoId);

    List<Long> findUserFavoriteVideoIds(Long userId);

    List<Long> findUserFavoriteVideoIds(Long userId, Integer current, Integer size);
}
