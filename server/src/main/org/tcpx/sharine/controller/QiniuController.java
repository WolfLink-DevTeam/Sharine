package org.tcpx.sharine.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.QiniuUptokenResponse;
import org.tcpx.sharine.entity.User;
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
    @GetMapping("/generateToken/{rawFileName}")
    public Object getUpToken(@PathVariable String rawFileName) {
        User user = userService.getSessionUser();
        QiniuUptokenResponse response = new QiniuUptokenResponse();
        String key = qiniuUtils.concatKey(user.getId(), rawFileName);
        response.setKey(key);
        response.setToken(qiniuUtils.generateToken(key));
        return ok(response);
    }
    @GetMapping("/downloadUrl/{fileKey}")
    public Object getDownloadUrl(@PathVariable String fileKey) {
        return ok(qiniuUtils.getDownloadUrl(fileKey));
    }
}
