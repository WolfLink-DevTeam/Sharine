package org.wolflink.sharine.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.RedisAction;
import org.wolflink.sharine.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ViewCountService {

    @Value("${application.cache-view-video-size}")
    Integer cacheSize;
    @Resource
    RedisAction redisAction;
    @Resource
    VideoRepository videoRepository;

    private List<Long> getUserViewList(String userIp) {
        String key = "viewlist-"+userIp;
        List<Long> viewVideoIdList = new ArrayList<>();
        try {
            viewVideoIdList = Objects.requireNonNull(redisAction.lRange(key, 0, cacheSize-1))
                    .stream().map(it -> Long.parseLong(String.valueOf(it))).collect(Collectors.toList());
        } catch (Exception ignore) {}
        return viewVideoIdList;
    }

    public void addViewCount(String userIp,Long videoId) {
        String key = "viewlist-"+userIp;
        List<Long> viewVideoIdList = getUserViewList(userIp);
        if(viewVideoIdList.size() > cacheSize) return;
        if(viewVideoIdList.contains(videoId)) return;
        redisAction.lPush(key,videoId,60 * 60 * 24);
        videoRepository.incrementCount(videoId);
    }
}
