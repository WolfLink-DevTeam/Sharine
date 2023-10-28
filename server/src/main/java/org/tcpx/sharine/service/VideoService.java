package org.tcpx.sharine.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;

@Service
@CacheConfig(cacheNames = DatabaseConst.VIDEO)
public class VideoService {

    public List<VideoVO> findAll(List<Long> videoIds) {
        // todo 继续完善
        return null;
    }

    public List<Long> findAllUserVideoIds(Long userId) {
        return null;
    }
}
