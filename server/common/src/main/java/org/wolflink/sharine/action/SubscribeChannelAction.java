package org.wolflink.sharine.action;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.entity.SubscribeVideoRecord;
import org.wolflink.sharine.repository.SubscribeVideoRecordRepository;
import org.wolflink.sharine.repository.UserRelationRepository;

import java.util.List;

/**
 * 订阅频道服务
 * 查询用户订阅频道数据
 * 插入数据到用户订阅频道
 */
@AllArgsConstructor
@Component
public class SubscribeChannelAction {

    UserRelationRepository userRelationRepository;
    SubscribeVideoRecordRepository subscribeVideoRecordRepository;

    /**
     * 插入视频ID到用户订阅队列
     *
     * @param userId  用户ID
     * @param videoId 视频ID
     */
    private void insertSubscribeVideoId(Long userId, Long videoId) {
        SubscribeVideoRecord record = SubscribeVideoRecord.builder()
                .userId(userId)
                .videoId(videoId)
                .build();
        subscribeVideoRecordRepository.save(record);
    }

    /**
     * 获取用户订阅队列的视频ID列表
     *
     * @param userId 用户ID
     * @return 视频ID列表(索引越大视频越早发布 ， 索引越小视频越新发布)
     */
    public List<Long> getSubscribeVideoIds(Long userId) {
        return subscribeVideoRecordRepository.findAllByUserId(userId)
                .stream().map(SubscribeVideoRecord::getVideoId).toList();
    }

    /**
     * 在视频成功发布后更新 UP 当前的所有粉丝的订阅队列
     *
     * @param userId  投稿用户ID
     * @param videoId 视频ID
     */
    @Async
    public void notifyFans(Long userId, Long videoId) {
        List<Long> fanIds = findAllFollowed(userId);
        fanIds.forEach(id -> insertSubscribeVideoId(id, videoId));
    }

    /**
     * 获取用户的粉丝ID列表
     *
     * @param userId 用户ID
     * @return 粉丝ID列表
     */
    private List<Long> findAllFollowed(Long userId) {
        return userRelationRepository.findAllUserFollowed(userId).stream().map((userRelation) -> {
            if (userRelation.getUserId1().equals(userId)) return userRelation.getUserId2();
            else return userRelation.getUserId1();
        }).toList();
    }


}
