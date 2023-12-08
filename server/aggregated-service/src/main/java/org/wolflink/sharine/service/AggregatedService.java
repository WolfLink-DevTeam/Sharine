package org.wolflink.sharine.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.ObjectParseAction;
import org.wolflink.sharine.action.SubscribeChannelAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.rpc.VideoServiceClient;
import org.wolflink.sharine.vo.VideoVO;

import java.util.List;

@Service
@AllArgsConstructor
public class AggregatedService {

    private final VideoServiceClient videoServiceClient;
    private final SubscribeChannelAction subscribeChannelAction;
    private final ObjectParseAction objectParseAction;
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 查询用户订阅频道
     * @param userId    用户ID
     * @return          视频列表
     */
    public List<VideoVO> getSubscribeVideos(Long userId) throws JsonProcessingException {
        List<Long> videoIds = subscribeChannelAction.getSubscribeVideoIds(userId);
        ResultPack resultPack = videoServiceClient.getVideos(videoIds);
        if(resultPack == null) throw new WarnException(StatusCodeEnum.SERVICE_CALL_TIMEOUT);
        if(!StatusCodeEnum.SUCCESS.getCode().equals(resultPack.getCode())) throw new WarnException(resultPack.getCode(),resultPack.getMsg());
        List<Video> videos = objectMapper.readValue((String) resultPack.getData(), new TypeReference<>() {});
        return videos.stream().map(objectParseAction::parse).toList();
    }

}
