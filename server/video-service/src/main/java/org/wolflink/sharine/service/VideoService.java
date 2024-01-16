package org.wolflink.sharine.service;

import com.qiniu.storage.model.FileInfo;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.SubscribeChannelAction;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.enums.MicroServiceEnum;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.event.VideoSaveEvent;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.BookmarkRepository;
import org.wolflink.sharine.repository.UpvoteRepository;
import org.wolflink.sharine.repository.VideoRepository;
import org.wolflink.sharine.action.QiniuAction;
import org.wolflink.sharine.rpc.IVideoService;

import java.util.List;

@DubboService
@Service
@AllArgsConstructor
public class VideoService implements IVideoService {

    private final VideoRepository videoRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UpvoteRepository upvoteRepository;
    private final QiniuAction qiniuAction;
    private final SubscribeChannelAction subscribeChannelAction;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 验证用户投稿信息并保存视频数据
     * @param video         视频对象
     * @param fileKey       文件名
     * @param hash          文件哈希
     * @param categoryId    分类Id
     */
    @Override
    public void signature(Video video, String fileKey, String hash, Long categoryId) {
        // 七牛云数据库查询
        FileInfo fileInfo = qiniuAction.getFileInfo(video.getUserId(), fileKey)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        if (!fileInfo.hash.equals(hash)) throw new WarnException(StatusCodeEnum.VERIFY_FAILED);
        // 文本内容审核
        if(!qiniuAction.textSensor(video.getContent())) throw new WarnException(StatusCodeEnum.JUDGE_FAILED);
        videoRepository.save(video);
        // TODO 广播事件
//        eventPublisher.publishEvent(new VideoSaveEvent(this, MicroServiceEnum.VIDEO_SERVICE,
//                MicroServiceEnum.CONTENT_SERVICE, video,categoryId));
        // 异步更新视频队列
        subscribeChannelAction.notifyFans(video.getUserId(), video.getId());
    }

    /**
     * 查询用户投稿作品
     * @param userId    用户ID
     * @return          用户投稿视频信息列表
     */
    @Override
    public List<Video> findVideosByUserId(Long userId) {
        return videoRepository.findAllByUserId(userId);
    }

    /**
     * 查询单个视频信息
     * @param videoId   视频ID
     * @return          视频信息
     */
    @Override
    public Video findVideo(Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
    }

    /**
     * 条件查找视频
     * @param current   当前页码
     * @param size      请求分页大小
     * @return          对应页的视频列表
     */
    @Override
    public List<Video> findVideos(Integer current, Integer size) {
        Pageable pageable = PageRequest.of(current, size);
        Page<Video> videos = videoRepository.findAll(pageable);
        return videos.toList();
    }

    /**
     * 批量查找视频信息
     * @param videoIds  视频ID列表
     * @return          视频信息列表
     */
    @Override
    public List<Video> findVideos(List<Long> videoIds) {
        return videoRepository.findAllById(videoIds);
    }

    @Override
    public void deleteVideo(Long videoId) {
        videoRepository.deleteById(videoId);
    }

    @Override
    public void updateVideo(Long videoId, String title, String content, String coverUrl) {
        Video video = videoRepository.findById(videoId).orElseThrow();
        if(title != null) video.setTitle(title);
        if(content != null) video.setContent(content);
        if(coverUrl != null) video.setCoverUrl(coverUrl);
        videoRepository.save(video);
    }

    /**
     * 构造视频展示数据对象
     * @param videos    视频数据列表
     * @return          视频展示数据对象列表
     */
//    private List<VideoVO> buildVideoVOs(List<Video> videos) {
//        return videos.stream().map(this::buildVideoVO).collect(Collectors.toList());
//    }
//    public VideoVO buildVideoVO(Video video) {
//        VideoVO videoVO = objectParseAction.parse(video);
//        videoVO.setUserId(video.getUserId());
//        videoVO.setCategory(videoCategoryService.findVideoCategory(video.getId()));
//        videoVO.setBookmarkCount(bookmarkRepository.countByVideoId(video.getId()));
//        videoVO.setFavoriteCount(upvoteRepository.countByVideoId(video.getId()));
//        return videoVO;
//    }
}
