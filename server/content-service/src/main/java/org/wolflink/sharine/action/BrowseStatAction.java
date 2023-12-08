package org.wolflink.sharine.action;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.repository.VideoMetadataRepository;
import org.wolflink.sharine.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BrowseStatAction {

    @Value("${application.cache-view-video-size}")
    Integer cacheSize;
    @Resource
    RedisAction redisAction;
    @Resource
    VideoMetadataRepository videoMetadataRepository;

    private List<Long> getUserBrowseList(String userIp) {
        String key = "viewlist-"+userIp;
        List<Long> viewVideoIdList = new ArrayList<>();
        try {
            viewVideoIdList = Objects.requireNonNull(redisAction.lRange(key, 0, cacheSize-1))
                    .stream().map(it -> Long.parseLong(String.valueOf(it))).collect(Collectors.toList());
        } catch (Exception ignore) {}
        return viewVideoIdList;
    }

    public void addBrowseCount(String userIp,Long videoId) {
        String key = "viewlist-"+userIp;
        List<Long> viewVideoIdList = getUserBrowseList(userIp);
        if(viewVideoIdList.size() > cacheSize) return;
        if(viewVideoIdList.contains(videoId)) return;
        redisAction.lPush(key,videoId,60 * 60 * 24);
        videoMetadataRepository.incrementViews(videoId);
    }
}
