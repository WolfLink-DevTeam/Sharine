package org.wolflink.sharine.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.entity.UserRelation;
import org.wolflink.sharine.enums.UserRelationEnum;
import org.wolflink.sharine.repository.UserRelationRepository;

import java.util.List;

@Service
public class UserRelationService {
    final UserRelationRepository userRelationRepository;

    public UserRelationService(UserRelationRepository userRelationRepository) {
        this.userRelationRepository = userRelationRepository;
    }

    /**
     * 统计用户关注数量
     * @param userId    用户ID
     * @return          关注数量
     */
    public Long countUserFollowing(Long userId) {
        return userRelationRepository.countUserFollowing(userId).longValue();
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

    /**
     * userId1 是否关注了 userId2
     * @param userId1       粉丝
     * @param userId2       被关注者
     * @return              粉丝是否关注被关注者
     */
    public boolean hasFollow(Long userId1,Long userId2) {
        if(userId1.equals(userId2)) return false;
        UserRelation userRelation;
        if(userId1 < userId2) {
            userRelation = userRelationRepository.findUserRelationByUserId1AndUserId2(userId1,userId2);
        } else userRelation = userRelationRepository.findUserRelationByUserId1AndUserId2(userId2,userId1);
        return userRelation.getStatus().equals(UserRelationEnum.FOLLOW);
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
