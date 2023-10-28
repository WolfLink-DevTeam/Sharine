package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.repository.UserRelationRepository;

@Service
public class UserRelationService {
    final UserRelationRepository userRelationRepository;

    public UserRelationService(UserRelationRepository userRelationRepository) {
        this.userRelationRepository = userRelationRepository;
    }

    public Long countUserFollowing(Long userId) {
        return userRelationRepository.countUserFollowing(userId);
    }

    public Long countUserFollowed(Long userId) {
        return userRelationRepository.countUserFollowed(userId);
    }
}
