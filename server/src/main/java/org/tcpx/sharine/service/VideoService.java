package org.tcpx.sharine.service;

import cn.dev33.satoken.stp.StpUtil;
import com.qiniu.storage.model.FileInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.dto.UploadVideoDTO;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.entity.Video;
import org.tcpx.sharine.enums.QiniuFileType;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.UserRepository;
import org.tcpx.sharine.repository.VideoRepository;
import org.tcpx.sharine.utils.IOC;
import org.tcpx.sharine.utils.QiniuUtils;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;

@Service
public class VideoService {

    @Resource
    private VideoRepository videoRepository;
    private UserService userService;
    @Resource
    private QiniuUtils qiniuUtils;
    @Autowired
    public void setUserService(@Lazy UserService userService) {
        this.userService = userService;
    }

    public void verifyAndSaveVideo(UploadVideoDTO uploadVideoDTO) {
        if(!StpUtil.isLogin())throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        // 用户数据查询
        User user = IOC.getBean(UserRepository.class).findById(uploadVideoDTO.getUserId())
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        // 七牛云数据库查询
        FileInfo fileInfo = qiniuUtils.getFileInfo(user, uploadVideoDTO.getFileName(), QiniuFileType.MP4)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        // MD5 比对
        if(!fileInfo.md5.equals(uploadVideoDTO.getMd5())) throw new WarnException(StatusCodeEnum.VERIFY_FAILED);
        Video video = Video.of(uploadVideoDTO);
        videoRepository.save(video);
    }
    public VideoVO findVideoInfo(Long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(()->new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        return VideoVO.of(video);
    }

}
