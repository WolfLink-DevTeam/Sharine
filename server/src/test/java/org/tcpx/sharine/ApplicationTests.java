package org.tcpx.sharine;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.tcpx.sharine.entity.Category;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.entity.Video;
import org.tcpx.sharine.entity.VideoCategoryRelation;
import org.tcpx.sharine.enums.VideoTypeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.repository.*;
import org.tcpx.sharine.service.CategoryService;
import org.tcpx.sharine.service.SubscribeChannelService;
import org.tcpx.sharine.service.VideoService;
import org.tcpx.sharine.utils.IOC;
import org.tcpx.sharine.utils.QiniuUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class ApplicationTests {
    @Autowired
    ApplicationContext context;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubscribeChannelService subscribeChannelService;
    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    VideoService videoService;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    QiniuUtils qiniuUtils;
    @Autowired
    VideoCategoryRepository videoCategoryRepository;
    /**
     * 测试环境初始化
     */
    @Test
    void testEnvironmentInit() {
        categoryRepository.save(new Category("综合","@/assets/category-icon/alltype-category.png"));
        categoryRepository.save(new Category("科技","@/assets/category-icon/tech-category.png"));
        categoryRepository.save(new Category("生活","@/assets/category-icon/live-category.png"));
        categoryRepository.save(new Category("娱乐","@/assets/category-icon/funny-category.png"));
        categoryRepository.save(new Category("音乐","@/assets/category-icon/music-category.png"));
        categoryRepository.save(new Category("游戏","@/assets/category-icon/game-category.png"));
    }
    @Test
    void initVideos() {
        Video video = new Video();
        video.setContent("这是第一个视频的视频简介");
        video.setTitle("首次上传的视频");
        video.setCoverUrl("https://ts3.cn.mm.bing.net/th?id=OIP-C.g9UbVfyVZX-SfD09JcYr5QHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&dpr=1.6&pid=3.1&rm=2");
        video.setUrl("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4");
        video.setUserId(1L);
        videoRepository.save(video);
    }
    @Test
    void tempTest() {
        System.out.println( qiniuUtils.verifyFile(null,"1-2023-06-17 16-40-56.mkv"));
    }

    private static final List<String> coverUrls;
    static {
        coverUrls = List.of("""
                https://img.zcool.cn/community/011aad554be56f000001bf72c38864.jpg@1280w_1l_2o_100sh.jpg
                https://tse1-mm.cn.bing.net/th/id/OIP-C.YKoZzgmubNBxQ8j-mmoTKAHaEK?pid=ImgDet&rs=1
                https://sc.68design.net/photofiles/201509/x2MjhGpkoQ.jpg
                https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/01/0E/ChMkJ1bKwhGIJFUZAAVHFzXgsW8AALGiQGvaxcABUcv637.jpg
                https://img.zcool.cn/community/0186025d143ecaa8012051cd9c2eb7.jpg@1280w_1l_2o_100sh.jpg
                https://img95.699pic.com/photo/50165/7667.jpg_wh860.jpg
                https://bpic.588ku.com/back_origin_min_pic/20/04/19/88e011e13fc534253be9b867b157a124.jpg
                https://img95.699pic.com/photo/50085/6393.jpg_wh860.jpg
                https://img.zcool.cn/community/01ab8e56c54f0032f875520f0e0002.jpg@1280w_1l_2o_100sh.jpg
                https://img.zcool.cn/community/019d515e3907caa8012165183430ad.png@2o.png
                https://img.zcool.cn/community/0191f55ae937bca801207fa1d5ede0.jpg@2o.jpg
                https://img.zcool.cn/community/019a055f99568e11013ee04de5190a.jpg@1280w_1l_2o_100sh.jpg
                https://img95.699pic.com/desgin_photo/40190/7280_detail.jpg
                https://img95.699pic.com/desgin_photo/40190/7280_detail.jpg
                https://img.zcool.cn/community/0109115e390762a80121651873bace.png@1280w_1l_2o_100sh.png
                https://static-cse.canva.cn/blob/62239/%E7%9B%B4%E6%92%AD%E8%A7%86%E9%A2%91%E5%B0%81%E9%9D%A2-7.jpg
                https://img95.699pic.com/desgin_photo/40191/2236_detail.jpg
                https://static-cse.canva.cn/blob/92563/IMG2020091822-7.png
                https://static-cse.canva.cn/blob/265809/1600w-MA_UvdW4Ye4.jpg
                https://img95.699pic.com/desgin_photo/40192/4823_detail.jpg
                https://static-cse.canva.cn/blob/265812/1600w-_PRVhFyXQ6o.jpg
                https://img.zcool.cn/community/016fa65fdf386a11013fdcc7b29cca.jpg@2o.jpg
                https://pic3.zhimg.com/v2-872837da2501f26a1216c8e7a618e44c_r.jpg
                https://static-cse.canva.cn/blob/265790/1600w-7TUIL85zfhY.jpg
                https://pic1.zhimg.com/v2-e7eb272c8a1a32896901437cdd1eda9e_r.jpg
                https://pic4.zhimg.com/v2-afa1d375b873e84426d5f3ad7fc3fc30_r.jpg
                """.split("\n"));
    }

    private static final List<String> videoUrls;
    static {
        videoUrls = List.of("""
                https://klxxcdn.oss-cn-hangzhou.aliyuncs.com/histudy/hrm/media/bg1.mp4
                https://klxxcdn.oss-cn-hangzhou.aliyuncs.com/histudy/hrm/media/bg2.mp4
                https://klxxcdn.oss-cn-hangzhou.aliyuncs.com/histudy/hrm/media/bg3.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4
                https://stream7.iqilu.com/10339/article/202002/18/2fca1c77730e54c7b500573c2437003f.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218025702PSiVKDB5ap.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/18/202002181038474liyNnnSzz.mp4
                https://stream7.iqilu.com/10339/article/202002/18/02319a81c80afed90d9a2b9dc47f85b9.mp4
                http://stream4.iqilu.com/ksd/video/2020/02/17/c5e02420426d58521a8783e754e9f4e6.mp4
                http://stream4.iqilu.com/ksd/video/2020/02/17/87d03387a05a0e8aa87370fb4c903133.mp4
                https://stream7.iqilu.com/10339/article/202002/17/c292033ef110de9f42d7d539fe0423cf.mp4
                http://stream4.iqilu.com/ksd/video/2020/02/17/97e3c56e283a10546f22204963086f65.mp4
                https://stream7.iqilu.com/10339/article/202002/17/778c5884fa97f460dac8d90493c451de.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217021133Eggh6zdlAO.mp4
                https://stream7.iqilu.com/10339/article/202002/17/4417a27b1a656f4779eaa005ecd1a1a0.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217104524H4D6lmByOe.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217101826WjyFCbUXQ2.mp4
                http://stream.iqilu.com/vod_bag_2016//2020/02/16/903BE158056C44fcA9524B118A5BF230/903BE158056C44fcA9524B118A5BF230_H264_mp4_500K.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/16/20200216050645YIMfjPq5Nw.mp4
                https://stream7.iqilu.com/10339/article/202002/16/3be2e4ef4aa21bfe7493064a7415c34d.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209105011F0zPoYzHry.mp4
                https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209104902N3v5Vpxuvb.mp4
                https://v-cdn.zjol.com.cn/280443.mp4
                https://v-cdn.zjol.com.cn/276982.mp4
                https://v-cdn.zjol.com.cn/276984.mp4
                https://v-cdn.zjol.com.cn/276985.mp4
                https://v-cdn.zjol.com.cn/276986.mp4
                https://v-cdn.zjol.com.cn/276987.mp4
                https://v-cdn.zjol.com.cn/276988.mp4
                https://v-cdn.zjol.com.cn/276989.mp4
                https://v-cdn.zjol.com.cn/276990.mp4
                https://v-cdn.zjol.com.cn/276991.mp4
                https://v-cdn.zjol.com.cn/276992.mp4
                https://v-cdn.zjol.com.cn/276993.mp4
                https://v-cdn.zjol.com.cn/276994.mp4
                https://v-cdn.zjol.com.cn/276996.mp4
                https://v-cdn.zjol.com.cn/276998.mp4
                https://v-cdn.zjol.com.cn/277000.mp4
                https://v-cdn.zjol.com.cn/277001.mp4
                https://v-cdn.zjol.com.cn/277002.mp4
                https://v-cdn.zjol.com.cn/277003.mp4
                https://v-cdn.zjol.com.cn/277004.mp4
                http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
                http://www.w3school.com.cn/example/html5/mov_bbb.mp4
                https://www.w3schools.com/html/movie.mp4
                http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
                https://media.w3.org/2010/05/sintel/trailer.mp4
                http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4
                http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4
                http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4
                http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4
                http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4
                http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4
                http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4
                http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4
                http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4
                http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4
                http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4
                http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4
                http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4
                http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4
                http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4
                https://vfx.mtime.cn/Video/2019/01/15/mp4/190115161611510728_480.mp4
                https://vfx.mtime.cn/Video/2019/08/24/mp4/190824113155647173.mp4
                """.split("\n"));
    }
    private static final List<String> avatarUrls;
    static {
        avatarUrls = List.of("""
                https://pic4.zhimg.com/v2-2b7a8469a00f7fa5d0b362659b17547c_r.jpg
                https://pic3.zhimg.com/v2-606185c511ac0a396e988b6dbd2ee3c5_r.jpg?source=1940ef5c
                https://p.qqan.com/up/2020-8/15982538692827405.jpg
                https://p.qqan.com/up/2020-8/15982538695311689.jpg
                https://p.qqan.com/up/2020-2/2020022708453463508.jpg
                https://www.77shw.com/wp-content/uploads/2019/02/201902261833052192.jpg
                https://img.zcool.cn/community/0165cb5d14565ca8012155290a6d86.png@2o.png
                https://c-ssl.duitang.com/uploads/item/201708/01/20170801151835_scFm2.jpeg
                https://tupian.qqw21.com/article/UploadPic/2019-1/201912319273090791.jpg
                https://p.qqan.com/up/2020-8/15979703191215711.jpg
                https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@2o.png
                https://tupian.qqw21.com/article/UploadPic/2020-8/20208522181014944.jpg
                https://c-ssl.duitang.com/uploads/item/202006/27/20200627105622_tbqti.jpeg
                https://tupian.qqw21.com/article/UploadPic/2020-8/20208522181570993.jpg
                https://p.qqan.com/up/2020-7/2020070908330717953.jpg
                https://img.zcool.cn/community/016ec15c13455ca80121ab5d81bc72.png@1280w_1l_2o_100sh.png
                https://pic3.zhimg.com/v2-c2ccc35a3139cf1f4c194dcc7711eb8d_r.jpg
                https://p.qqan.com/up/2021-6/16234652484532758.jpg
                https://p.qqan.com/up/2020-7/2020070908330768694.jpg
                https://tupian.qqw21.com/article/UploadPic/2020-5/20205622141367241.jpg
                https://pic4.zhimg.com/v2-50215b1bebb0ad1e7adfe7bbd0daec4d_r.jpg
                https://gss0.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/bf096b63f6246b60f99bc242e5f81a4c510fa26b.jpg
                https://tupian.qqw21.com/article/UploadPic/2021-8/202181521522971378.jpg
                """.split("\n"));
    }
    OkHttpClient client = new OkHttpClient();
    public JsonObject getHitokoto(){
        try {
            Request request = new Request.Builder().url("https://v1.hitokoto.cn/").get().build();
            JsonObject jo = JsonParser.parseString(client.newCall(request).execute().body().string()).getAsJsonObject();
            return jo;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
    public String selectRandom(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }
    ThreadLocalRandom random = ThreadLocalRandom.current();
    public User generateRandomUser() {
        JsonObject jo = getHitokoto();
        return User.builder()
                .nickname(jo.get("creator").getAsString())
                .content(jo.get("hitokoto").getAsString())
                .account(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .avatar(selectRandom(avatarUrls))
                .build();
    }
    public Video generateRandomVideo() {
        JsonObject jo = getHitokoto();
        return Video.builder()
                .title(jo.get("hitokoto").getAsString())
                .coverUrl(selectRandom(coverUrls))
                .content(jo.get("hitokoto").getAsString())
                .viewCount(random.nextLong(10000))
                .userId(random.nextLong(100))
                .url(selectRandom(videoUrls))
                .build();
    }

    @Test
    void mock() throws IOException {
        int userCount = 500;
        int categoryCount = 6;
        int videoCount = 100;
        long videoId = 1;
        for (int i = 0; i < videoCount; i++) {
            Video video = generateRandomVideo();
            video.setId(videoId++);
            VideoCategoryRelation relation = new VideoCategoryRelation();
            relation.setVideoId(video.getId());
            relation.setCategoryId(random.nextLong(categoryCount)+1);
            videoRepository.save(video);
            videoCategoryRepository.save(relation);
        }
    }
}
