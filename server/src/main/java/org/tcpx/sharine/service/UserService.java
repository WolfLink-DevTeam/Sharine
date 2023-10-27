package org.tcpx.sharine.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.DatabaseConstants;
import org.tcpx.sharine.dto.UsernamePassword;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.exception.ErrorException;
import org.tcpx.sharine.repository.UserRepository;
import org.tcpx.sharine.utils.EncryptionUtil;
import org.tcpx.sharine.vo.UserVO;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = DatabaseConstants.USER)
public class UserService {

    final UserRepository userRepository;

    final FavoriteService favoriteService;

    final UserRelationService userRelationService;

    public UserService(UserRepository userRepository, FavoriteService favoriteService, UserRelationService userRelationService) {
        this.userRepository = userRepository;
        this.favoriteService = favoriteService;
        this.userRelationService = userRelationService;
    }

    public UserVO login(UsernamePassword usernamePassword) {
        Optional<User> byUsername = userRepository.findByUsername(usernamePassword.getUsername());
        if (byUsername.isEmpty()) {
            throw new ErrorException("用户不存在！");
        }

        User user = byUsername.get();
        if (!EncryptionUtil.match(usernamePassword.getPassword(), user.getPassword())) {
            throw new ErrorException("密码错误！");
        }

        UserVO userVO = UserVO.of(user);
        userVO.setFavouriteCount(favoriteService.countUserFavoured(user.getId()));
        userVO.setFollowingCOunt(userRelationService.countUserFollowing(user.getId()));
        userVO.setFollowedCount(userRelationService.countUserFollowed(user.getId()));
        return userVO;
    }
}
