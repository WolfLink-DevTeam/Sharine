package org.wolflink.sharine.service;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.AggregateParseAction;
import org.wolflink.sharine.action.SubscribeChannelAction;
import org.wolflink.sharine.rpc.IBookmarkService;
import org.wolflink.sharine.rpc.IFavoriteService;
import org.wolflink.sharine.rpc.IVideoService;
import org.wolflink.sharine.vo.VideoVO;

import java.util.List;

@Service
public class AggregatedService {

    @DubboReference
    private IVideoService videoService;
    @DubboReference
    private IFavoriteService favoriteService;
    @DubboReference
    private IBookmarkService bookmarkService;
    @Resource
    private SubscribeChannelAction subscribeChannelAction;
    @Resource
    private AggregateParseAction aggregateParseAction;
    /**
     * 查询用户订阅频道
     * @param userId    用户ID
     * @return          视频列表
     */
    public List<VideoVO> getSubscribeVideos(Long userId) {
        List<Long> videoIds = subscribeChannelAction.getSubscribeVideoIds(userId);
        return videoService.findVideos(videoIds).stream().map(aggregateParseAction::parse).toList();
    }
    public List<VideoVO> getFavoriteVideos(Long userId) {
        List<Long> videoIds = favoriteService.findUserFavoriteVideoIds(userId);
        return videoService.findVideos(videoIds).stream().map(aggregateParseAction::parse).toList();
    }
    public List<VideoVO> getBookmarkVideos(Long userId) {
        List<Long> videoIds = bookmarkService.findUserBookmarkVideoIds(userId);
        return videoService.findVideos(videoIds).stream().map(aggregateParseAction::parse).toList();
    }
}
