package org.wolflink.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wolflink.sharine.action.QiniuAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.vo.OSSDownloadInfo;
import org.wolflink.sharine.vo.OSSUploadInfo;

@RestController
@AllArgsConstructor
@RequestMapping("/oss")
public class OSSController extends BaseController {
    private final QiniuAction qiniuAction;
    @GetMapping("/uploadInfo")
    public ResultPack getUploadInfo(@RequestParam String fileName) {
        StpUtil.checkLogin();
        String fileKey = qiniuAction.concatKey(StpUtil.getLoginIdAsInt(),fileName);
        OSSUploadInfo uploadInfo = new OSSUploadInfo(fileKey,qiniuAction.generateToken(fileKey));
        return ok(uploadInfo);
    }
    @GetMapping("/downloadInfo")
    public ResultPack getDownloadInfo(@RequestParam String fileKey) {
        OSSDownloadInfo downloadInfo = new OSSDownloadInfo(qiniuAction.getDownloadUrl(fileKey));
        return ok(downloadInfo);
    }
}
