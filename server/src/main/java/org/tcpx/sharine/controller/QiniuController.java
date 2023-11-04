package org.tcpx.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.QiniuAuthorizationRequest;
import org.tcpx.sharine.dto.QiniuUptokenRequest;
import org.tcpx.sharine.dto.QiniuUptokenResponse;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.service.UserService;
import org.tcpx.sharine.utils.QiniuUtils;

@RestController
@AllArgsConstructor
@RequestMapping("/qiniu")
public class QiniuController extends BaseController {
    @Resource
    QiniuUtils qiniuUtils;
    @Resource
    UserService userService;
    @PostMapping("/generateToken")
    public Object getUpToken(@RequestBody QiniuUptokenRequest qiniuUptokenRequest) {
        StpUtil.checkLogin();
        User user = userService.verifyUserPass(qiniuUptokenRequest.getUserPass());
        QiniuUptokenResponse response = new QiniuUptokenResponse();
        String key = qiniuUtils.concatKey(user.getId(), qiniuUptokenRequest.getRawFileName());
        response.setKey(key);
        response.setToken(qiniuUtils.generateToken(key));
        return ok(response);
    }
    @PostMapping("/authorization")
    public Object getAuthorization(@RequestBody QiniuAuthorizationRequest request) {
        return ok(qiniuUtils.encodeAuthorization(request.getUrl(),request.getJsonBody()));
    }
    @GetMapping("/downloadUrl/{fileKey}")
    public Object getDownloadUrl(@PathVariable String fileKey) {
        return ok(qiniuUtils.getDownloadUrl(fileKey));
    }
}
