package org.wolflink.sharine.service;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.ObjectParseAction;
import org.wolflink.sharine.action.SubscribeChannelAction;
import org.wolflink.sharine.rpc.IVideoService;
import org.wolflink.sharine.vo.VideoVO;

import java.util.List;

@Service
public class AggregatedService {

    @DubboReference
    private IVideoService videoService;
    @Resource
    private SubscribeChannelAction subscribeChannelAction;
    @Resource
    private ObjectParseAction objectParseAction;
    /**
     * 查询用户订阅频道
     * @param userId    用户ID
     * @return          视频列表
     */
    public List<VideoVO> getSubscribeVideos(Long userId) {
        List<Long> videoIds = subscribeChannelAction.getSubscribeVideoIds(userId);
        return videoService.findVideos(videoIds).stream().map(objectParseAction::parse).toList();
    }
}
