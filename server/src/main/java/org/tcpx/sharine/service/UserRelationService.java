package org.tcpx.sharine.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.UserRelation;
import org.tcpx.sharine.entity.Video;
import org.tcpx.sharine.enums.UserRelationEnum;
import org.tcpx.sharine.repository.UserRelationRepository;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;

@Service
public class UserRelationService {
    final UserRelationRepository userRelationRepository;
    final VideoService videoService;

    public UserRelationService(UserRelationRepository userRelationRepository,@Lazy VideoService videoService) {
        this.userRelationRepository = userRelationRepository;
        this.videoService = videoService;
    }

    /**
     * 统计用户关注数量
     * @param userId    用户ID
     * @return          关注数量
     */
    public Long countUserFollowing(Long userId) {
        return userRelationRepository.countUserFollowing(userId).longValue();
    }

    public void followVideo(Long followerId,Long videoId) {
        VideoVO videoVO = videoService.findVideoInfo(videoId);
        follow(followerId,videoVO.getAuthor().getId());
    }
    public void undoFollowVideo(Long followerId,Long videoId) {
        VideoVO videoVO = videoService.findVideoInfo(videoId);
        undoFollow(followerId,videoVO.getAuthor().getId());
    }
    /**
     * userId1 关注用户 userId2
     * @param followerId   粉丝
     * @param followedId   被关注者
     */
    public void follow(Long followerId,Long followedId) {
        if(followerId.equals(followedId)) return;
        UserRelation userRelation = new UserRelation();
        if(followerId < followedId) {
            userRelation.setUserId1(followerId);
            userRelation.setUserId2(followedId);
            userRelation.setStatus(UserRelationEnum.FOLLOW);
        } else {
            userRelation.setUserId1(followedId);
            userRelation.setUserId2(followerId);
            userRelation.setStatus(UserRelationEnum.FOLLOWED);
        }
        Example<UserRelation> example = Example.of(userRelation);
        if(userRelationRepository.exists(example)) return;
        userRelationRepository.save(userRelation);
    }
    public void undoFollow(Long followerId,Long followedId) {
        if(followerId.equals(followedId)) return;
        if(followerId < followedId) {
            userRelationRepository.deleteByUserId1AndUserId2(followerId,followedId);
        } else userRelationRepository.deleteByUserId1AndUserId2(followedId,followerId);
    }

    /**
     * 统计用户的粉丝数量
     * @param userId    用户ID
     * @return          粉丝数量
     */
    public Long countUserFollowed(Long userId) {
        return userRelationRepository.countUserFollowed(userId).longValue();
    }

    /**
     * 获取用户的粉丝ID列表
     * @param userId    用户ID
     * @return          粉丝ID列表
     */
    public List<Long> findAllFollowed(Long userId) {
        return userRelationRepository.findAllUserFollowed(userId).stream().map((userRelation)->{
            if(userRelation.getUserId1().equals(userId)) return userRelation.getUserId2();
            else return userRelation.getUserId1();
        }).toList();
    }
}
