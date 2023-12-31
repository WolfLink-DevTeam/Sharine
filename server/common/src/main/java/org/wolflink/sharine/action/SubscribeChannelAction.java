package org.wolflink.sharine.action;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.repository.UserRelationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订阅频道服务
 * 查询用户订阅频道数据
 * 插入数据到用户订阅频道
 */
@Component
public class SubscribeChannelAction {

    @Value("${application.subscribe-channel-size}")
    Integer channelSize;
    RedisAction redisAction;
    UserRelationRepository userRelationRepository;

    public SubscribeChannelAction(RedisAction redisAction, UserRelationRepository userRelationRepository) {
        this.redisAction = redisAction;
        this.userRelationRepository = userRelationRepository;
    }

    /**
     * 插入视频ID到用户订阅队列
     * @param userId    用户ID
     * @param videoId   视频ID
     */
    private void insertVideoId(Long userId, Long videoId) {
        String key = String.valueOf(userId);
        if(redisAction.lSize(key) >= channelSize) redisAction.lRightPop(key);
        redisAction.lLeftPush(key,videoId);
    }

    /**
     * 获取用户订阅队列的视频ID列表
     * @param userId    用户ID
     * @return          视频ID列表(索引越大视频越早发布，索引越小视频越新发布)
     */
    public List<Long> getSubscribeVideoIds(Long userId) {
        try {
            return Objects.requireNonNull(redisAction.lRange(String.valueOf(userId), 0, channelSize-1))
                    .stream().map(it -> Long.parseLong(String.valueOf(it))).collect(Collectors.toList());
        } catch (Exception ignore) {
            return new ArrayList<>();
        }
    }

    /**
     * 在视频成功发布后更新 UP 当前的所有粉丝的订阅队列
     * @param userId    投稿用户ID
     * @param videoId   视频ID
     */
    @Async
    public void notifyFans(Long userId,Long videoId) {
        // TODO 耦合 UserRelationService
        List<Long> fanIds = findAllFollowed(userId);
        fanIds.forEach(id -> insertVideoId(id,videoId));
    }

    /**
     * 获取用户的粉丝ID列表
     * @param userId    用户ID
     * @return          粉丝ID列表
     */
    private List<Long> findAllFollowed(Long userId) {
        return userRelationRepository.findAllUserFollowed(userId).stream().map((userRelation)->{
            if(userRelation.getUserId1().equals(userId)) return userRelation.getUserId2();
            else return userRelation.getUserId1();
        }).toList();
    }


}
