package org.wolflink.sharine.rpc;

import org.wolflink.sharine.entity.Video;

import java.util.List;

public interface IVideoService {
    void signature(Video video, String fileKey, String hash, Long categoryId);

    List<Video> findVideosByUserId(Long userId);

    Video findVideo(Long videoId);

    List<Video> findVideos(Integer current, Integer size);

    List<Video> findVideos(List<Long> videoIds);
}
