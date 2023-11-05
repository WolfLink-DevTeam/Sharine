package org.tcpx.sharine;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.tcpx.sharine.entity.Category;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.entity.Video;
import org.tcpx.sharine.enums.VideoTypeEnum;
import org.tcpx.sharine.repository.BookmarkRepository;
import org.tcpx.sharine.repository.CategoryRepository;
import org.tcpx.sharine.repository.UserRepository;
import org.tcpx.sharine.repository.VideoRepository;
import org.tcpx.sharine.service.CategoryService;
import org.tcpx.sharine.service.SubscribeChannelService;
import org.tcpx.sharine.service.VideoService;
import org.tcpx.sharine.utils.IOC;
import org.tcpx.sharine.utils.QiniuUtils;

import java.util.List;

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
        video.setType(VideoTypeEnum.ORIGINAL);
        video.setTitle("首次上传的视频");
        video.setCoverUrl("https://ts3.cn.mm.bing.net/th?id=OIP-C.g9UbVfyVZX-SfD09JcYr5QHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&dpr=1.6&pid=3.1&rm=2");
        video.setUrl("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4");
        video.setUserId(1L);
        videoRepository.save(video);
    }
    @Test
    void tempTest() {
        System.out.println( qiniuUtils.verifyFile(null,"1-2023-06-17 16-40-56.mkv"));
//        User user = userRepository.findByAccount("3401286177@qq.com").orElseThrow();
    }
}
