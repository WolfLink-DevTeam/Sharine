package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.repository.UserRelationRepository;

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
