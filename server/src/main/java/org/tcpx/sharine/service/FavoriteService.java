package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.repository.FavoriteRepository;

import java.util.List;

@Service
public class FavoriteService {

    final FavoriteRepository favoriteRepository;

    final VideoService videoService;

    public FavoriteService(FavoriteRepository favoriteRepository, VideoService videoService) {
        this.favoriteRepository = favoriteRepository;
        this.videoService = videoService;
    }

    public Long countUserFavoured(Long userId) {
        List<Long> videoIds = videoService.findAllUserVideoIds(userId);

        return favoriteRepository.countByVideoIdIn(videoIds);
    }
}
