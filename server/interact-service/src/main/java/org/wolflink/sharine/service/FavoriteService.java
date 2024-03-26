package org.wolflink.sharine.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.entity.Upvote;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.UpvoteRepository;
import org.wolflink.sharine.rpc.IFavoriteService;

import java.util.List;
import java.util.stream.Collectors;

@DubboService
@Service
public class FavoriteService implements IFavoriteService {

    final UpvoteRepository upvoteRepository;

    public FavoriteService(UpvoteRepository upvoteRepository) {
        this.upvoteRepository = upvoteRepository;
    }

    /**
     * 用户点赞视频
     *
     * @param userId  用户ID
     * @param videoId 视频ID
     */
    @Override
    public void favoriteVideo(Long userId, Long videoId) {
        // 已经点赞了
        if (hasFavoriteVideo(userId, videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_EXIST);
        }
        Upvote upvote = Upvote.builder().videoId(videoId).userId(userId).build();
        upvoteRepository.save(upvote);
    }
    @Override
    public boolean hasFavoriteVideo(Long userId, Long videoId) {
        return upvoteRepository.existsByUserIdAndVideoId(userId,videoId);
    }
    /**
     * 用户撤销点赞视频
     *
     * @param userId  用户ID
     * @param videoId 视频ID
     */
    @Override
    public void undoFavoriteVideo(Long userId, Long videoId) {
        // 数据不存在
        if (!upvoteRepository.existsByUserIdAndVideoId(userId, videoId)) {
            throw new WarnException(StatusCodeEnum.DATA_NOT_EXIST);
        }
        upvoteRepository.deleteByUserIdAndVideoId(userId, videoId);
    }

    /**
     * 查询用户关注的视频ID列表
     * @param userId    用户ID
     * @return          用户关注视频ID列表
     */
    @Override
    public List<Long> findUserFavoriteVideoIds(Long userId) {
        List<Upvote> byUserId = upvoteRepository.findAllByUserId(userId);
        System.out.println(byUserId);
        return byUserId.stream().map(Upvote::getVideoId).collect(Collectors.toList());
    }
    @Override
    public List<Long> findUserFavoriteVideoIds(Long userId, Integer current, Integer size) {
        Pageable pageable = PageRequest.of(current, size);

        List<Upvote> byUserId = upvoteRepository.findAllByUserId(userId, pageable);

        return byUserId.stream().map(Upvote::getVideoId).collect(Collectors.toList());
    }
}
