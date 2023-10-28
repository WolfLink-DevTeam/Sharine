package org.tcpx.sharine.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.tcpx.sharine.dto.QiniuBasePack;
import org.tcpx.sharine.dto.QiniuCensorPack;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.enums.QiniuFileType;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    @Value("${qiniu.domain-of-bucket}")
    String domainOfBucket;

    private String generateToken(String fileKey) {
        return Auth.create(accessKey, secretKey).uploadToken(bucket, fileKey);
    }

    /**
     * 上传文件到七牛云存储
     *
     * @param inputStream 输入流
     * @param fileName    文件名(不带有后缀)
     * @return 结果
     */
    @SuppressWarnings("仅供测试使用，文件上传走前端，后端只负责校验")
    public Optional<DefaultPutRet> upload(InputStream inputStream, String fileName, QiniuFileType fileType) {
        String fileKey = "0-" + fileName + "." + fileType.name().toLowerCase();
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

    /**
     * 获取七牛云文件下载URL
     *
     * @param fileName 文件名(不带有后缀)
     * @param fileType 文件类型
     * @return URL
     */
    public String getDownloadUrl(long uploaderId, String fileName, QiniuFileType fileType) {
        try {
            String fileKey = concatKey(uploaderId, fileName, fileType);
            String encodedFileName = URLEncoder.encode(fileKey, StandardCharsets.UTF_8).replace("+", "%20");
            DownloadUrl url = new DownloadUrl(domainOfBucket, false, fileKey);
            long expireInSeconds = 3600;
            long deadline = System.currentTimeMillis() / 1000 + expireInSeconds;
            Auth auth = Auth.create(accessKey, secretKey);
            return url.buildURL(auth, deadline);
        } catch (Exception ignore) {
        }
        return "";
    }

    private String concatKey(long uniqueId, String fileName, QiniuFileType fileType) {
        return uniqueId + "-" + fileName + "." + fileType.name().toLowerCase();
    }

    /**
     * 校验文件是否存在于数据库中，并且由正确的用户上传
     *
     * @param uploader 上传者(测试可传null)
     * @param fileName 文件名(不包括后缀)
     * @param fileType 文件类型
     * @return 校验结果
     */
    public boolean verifyFile(@Nullable User uploader, String fileName, QiniuFileType fileType) {
        try {
            long uniqueId = 0;
            if (uploader != null) uniqueId = uploader.getId();
            String url = getDownloadUrl(uniqueId, fileName, fileType);
            BucketManager bucketManager = new BucketManager(Auth.create(accessKey, secretKey), (Configuration) null);
            bucketManager.stat(bucket, concatKey(uniqueId, fileName, fileType));
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    /**
     * 签文算法
     *
     * @param url      请求的URL链接
     * @param bodyJson Json格式的字符串
     * @return 加密签文
     */
    public String encodeAuthorization(String url, String bodyJson) {
        return "Qiniu " + Auth.create(accessKey, secretKey).signQiniuAuthorization(url, HttpMethod.POST.name(), bodyJson.getBytes(StandardCharsets.UTF_8), MediaType.APPLICATION_JSON_VALUE);
    }

    public Call qiniuCall(String url, QiniuBasePack pack) {
        return qiniuCall(url, pack.toJsonObject());
    }

    /**
     * 七牛云Http请求
     *
     * @param url  请求URL
     * @param body 请求体
     * @return Okhttp.Call
     */
    public Call qiniuCall(String url, JsonObject body) {
        OkHttpClient client = new OkHttpClient();
        String bodyStr = body.toString();
        RequestBody requestBody = RequestBody.create(bodyStr.getBytes(), okhttp3.MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", encodeAuthorization(url, bodyStr))
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 图片审核
     *
     * @param imgUrl 图片URL
     * @return 图片审核结果，通过返回true，不通过返回false
     */
    public boolean imageSensor(String imgUrl) {
        QiniuCensorPack pack = new QiniuCensorPack();
        pack.getData().addProperty("uri", imgUrl);
        pack.getScenes().add("politician");
        pack.getScenes().add("terror");
        pack.getScenes().add("pulp");
        Call call = qiniuCall("http://ai.qiniuapi.com/v3/image/censor", pack);
        try {
            JsonObject jo = JsonParser.parseString(call.execute().body().string()).getAsJsonObject();
            if (jo.getAsJsonObject("result").get("suggestion").getAsString().equals("pass")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 视频审核接口支持的视频大小不超过1G，需要分片处理，暂时不接
     */
    @Deprecated
    public boolean videoSensor() {
        return false;
    }

    /**
     * 文本审核
     *
     * @param text 文字内容
     * @return 文本审核结果，通过返回true，不通过返回false
     */
    public boolean textSensor(String text) {
        QiniuCensorPack pack = new QiniuCensorPack();
        pack.getData().addProperty("text", text);
        pack.getScenes().add("antispam");
        Call call = qiniuCall("http://ai.qiniuapi.com/v3/text/censor", pack);
        try {
            JsonObject jo = JsonParser.parseString(call.execute().body().string()).getAsJsonObject();
            if (jo.getAsJsonObject("result").get("suggestion").getAsString().equals("pass")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
