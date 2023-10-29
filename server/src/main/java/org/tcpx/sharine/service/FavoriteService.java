package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.Favorite;
import org.tcpx.sharine.exception.WarnException;
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

    /**
     * 切换用户点赞视频状态
     * @param userId    用户ID
     * @param videoId   视频ID
     * @return          点赞状态，true为点赞成功，false为取消点赞
     */
    public boolean toggleVideoFavorite(Long userId,Long videoId) {
        // 已经点赞了，再次点击取消点赞
        if(favoriteRepository.existsByUserIdAndVideoId(userId,videoId)) {
            favoriteRepository.deleteByUserIdAndVideoId(userId,videoId);
            return false;
        }
        // 没有点赞过
        Favorite favorite = Favorite.builder()
                .videoId(videoId)
                .userId(userId)
                .build();
        favoriteRepository.save(favorite);
        return true;
    }
}
