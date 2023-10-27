package org.tcpx.sharine.utils;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Optional;

/**
 * 七牛云相关服务封装
 */
@Component
public class QiniuUtils {
    @Value("${qiniu.access-key}")
    String accessKey;
    @Value("${qiniu.secret-key}")
    String secretKey;
    @Value("${qiniu.bucket}")
    String bucket;

    private String generateToken(String fileKey) {
        return Auth.create(accessKey, secretKey).uploadToken(bucket, fileKey);
    }

    /**
     * 上传文件到七牛云存储
     *
     * @param inputStream 输入流
     * @param fileKey     文件名
     * @return 结果
     */
    public Optional<DefaultPutRet> upload(InputStream inputStream, String fileKey) {
        String token = generateToken(fileKey);
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Response response = uploadManager.put(inputStream, fileKey, token, null, null);
            return Optional.of(new Gson().fromJson(response.bodyString(), DefaultPutRet.class));
        } catch (Exception ignore) {
            System.err.println("在进行文件上传时出现问题，文件名：" + fileKey);
        }
        return Optional.empty();
    }
}
