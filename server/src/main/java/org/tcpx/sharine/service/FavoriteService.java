package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.Favorite;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.FavoriteRepository;

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
     * 用户点赞视频
     * @param userId    用户ID
     * @param videoId   视频ID
     */
    public void favoriteVideo(Long userId,Long videoId) {
        // 已经点赞了
        if(favoriteRepository.existsByUserIdAndVideoId(userId,videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_EXIST);
        }
        Favorite favorite = Favorite.builder()
                .videoId(videoId)
                .userId(userId)
                .build();
        favoriteRepository.save(favorite);
    }
    /**
     * 用户撤销点赞视频
     * @param userId    用户ID
     * @param videoId   视频ID
     */
    public void undoFavoriteVideo(Long userId,Long videoId) {
        // 数据不存在
        if(!favoriteRepository.existsByUserIdAndVideoId(userId,videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_NOT_EXIST);
        }
        favoriteRepository.deleteByUserIdAndVideoId(userId,videoId);
    }
}
