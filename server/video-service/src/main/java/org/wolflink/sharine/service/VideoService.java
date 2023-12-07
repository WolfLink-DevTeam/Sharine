package org.wolflink.sharine.service;

import com.qiniu.storage.model.FileInfo;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.RedisService;
import org.wolflink.sharine.action.SubscribeChannelService;
import org.wolflink.sharine.dto.ConditionDTO;
import org.wolflink.sharine.dto.UploadVideoDTO;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.BookmarkRepository;
import org.wolflink.sharine.repository.FavoriteRepository;
import org.wolflink.sharine.repository.VideoRepository;
import org.wolflink.sharine.action.QiniuUtils;
import org.wolflink.sharine.vo.VideoVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Resource
    private VideoRepository videoRepository;
    @Resource
    private BookmarkRepository bookmarkRepository;
    @Resource
    private FavoriteRepository favoriteRepository;
    @Resource
    private QiniuUtils qiniuUtils;


    final VideoCategoryService videoCategoryService;

    final SubscribeChannelService subscribeChannelService;

    final RedisService redisService;

    public VideoService(VideoCategoryService videoCategoryService, SubscribeChannelService subscribeChannelService,RedisService redisService) {
        this.videoCategoryService = videoCategoryService;
        this.subscribeChannelService = subscribeChannelService;
        this.redisService = redisService;
    }

    /**
     * 验证用户投稿信息并保存视频数据
     * @param uploadVideoDTO    投稿视频传输层数据对象
     */
    public void verifyAndSaveVideo(UploadVideoDTO uploadVideoDTO) {
        // 七牛云数据库查询
        FileInfo fileInfo = qiniuUtils.getFileInfo(uploadVideoDTO.getUserId(), uploadVideoDTO.getFileKey())
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        if (!fileInfo.hash.equals(uploadVideoDTO.getHash())) throw new WarnException(StatusCodeEnum.VERIFY_FAILED);
        // 文本内容审核
        if(!qiniuUtils.textSensor(uploadVideoDTO.getContent())) throw new WarnException(StatusCodeEnum.JUDGE_FAILED);
        Video video = Video.of(uploadVideoDTO);
        video.setUserId(uploadVideoDTO.getUserId());
        video.setViewCount(0L);
        videoRepository.save(video);
        videoCategoryService.saveVideoCategoryRelation(video.getId(), uploadVideoDTO.getCategoryId());
        // 异步更新视频队列
        subscribeChannelService.notifyFans(uploadVideoDTO.getUserId(), video.getId());
    }

    /**
     * 查询用户投稿作品
     * @param userId    用户ID
     * @return          用户投稿视频信息列表
     */
    public List<VideoVO> findVideoVOsByUserId(Long userId) {
        return buildVideoVOs(findVideosByUserId(userId));
    }
    public List<Video> findVideosByUserId(Long userId) {
        return videoRepository.findAllByUserId(userId);
    }

    /**
     * 查询单个视频信息
     * @param videoId   视频ID
     * @return          视频信息
     */
    public VideoVO findVideoInfo(Long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        return buildVideoVO(video);
    }

    /**
     * 查找视频
     * @param conditionDTO  查询条件
     * @return              符合条件的视频列表
     */
    public List<VideoVO> findVideos(ConditionDTO conditionDTO) {
        Pageable pageable = PageRequest.of(
                conditionDTO.getCurrent(),
                conditionDTO.getSize()
        );

        Page<Video> videos = videoRepository.findAll(pageable);
        return buildVideoVOs(videos.toList());
    }

    /**
     * 批量查找视频信息
     * @param videoIds  视频ID列表
     * @return          视频信息列表
     */
    public List<VideoVO> findVideos(List<Long> videoIds) {
        List<Video> allById = videoRepository.findAllById(videoIds);
        return buildVideoVOs(allById);
    }

    /**
     * 构造视频展示数据对象
     * @param videos    视频数据列表
     * @return          视频展示数据对象列表
     */
    private List<VideoVO> buildVideoVOs(List<Video> videos) {
        return videos.stream().map(this::buildVideoVO).collect(Collectors.toList());
    }
    public VideoVO buildVideoVO(Video video) {
        VideoVO videoVO = VideoVO.of(video);
        videoVO.setUserId(video.getUserId());
        videoVO.setCategory(videoCategoryService.findVideoCategory(video.getId()));
        videoVO.setBookmarkCount(bookmarkRepository.countByVideoId(video.getId()));
        videoVO.setFavoriteCount(favoriteRepository.countByVideoId(video.getId()));
        return videoVO;
    }
    /**
     * TODO 查询用户订阅频道
     * @param userId    用户ID
     * @return          视频列表
     */
//    public List<VideoVO> getSubscribeVideos(Long userId) {
//        List<Long> videoIds = subscribeChannelService.getSubscribeVideoIds(userId);
//        return findVideos(videoIds);
//    }
}
