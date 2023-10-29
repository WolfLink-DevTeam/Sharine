package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.repository.FavoriteRepository;
import org.tcpx.sharine.repository.VideoRepository;

import java.util.List;

@Service
public class FavoriteService {

    final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    /**
     * 计算用户关注数
     * @param userId    用户ID
     * @return          关注数
     */
    public Long countUserFavorite(Long userId) {
        return favoriteRepository.countByUserId(userId);
    }
}
