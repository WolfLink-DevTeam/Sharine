package org.tcpx.sharine.service;

import com.qiniu.storage.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.entity.Video;
import org.tcpx.sharine.enums.QiniuFileType;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.UserRepository;
import org.tcpx.sharine.utils.IOC;
import org.tcpx.sharine.utils.QiniuUtils;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = DatabaseConst.VIDEO)
public class VideoService {

    private UserService userService;
    private QiniuUtils qiniuUtils;
    @Autowired
    public void setUserService(@Lazy UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setQiniuUtils(QiniuUtils qiniuUtils) { this.qiniuUtils = qiniuUtils; }
    public List<VideoVO> findAll(List<Long> videoIds) {
        // todo 继续完善
        return null;
    }

    public List<Long> findAllUserVideoIds(Long userId) {
        return null;
    }

    public List<VideoVO> findUserVideos(ConditionDTO conditionDTO) {
        return null;
    }
    public void verifyAndSaveVideo(String userAccount,String fileName,String md5) {
        // 用户数据查询
        User user = IOC.getBean(UserRepository.class).findByAccount(userAccount)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        // 七牛云数据库查询
        FileInfo fileInfo = qiniuUtils.getFileInfo(user,fileName, QiniuFileType.MP4)
                .orElseThrow(() -> new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
        // MD5 比对
        if(!fileInfo.md5.equals(md5)) throw new WarnException(StatusCodeEnum.VERIFY_FAILED);

        //TODO 视频数据存入数据库
    }

}
