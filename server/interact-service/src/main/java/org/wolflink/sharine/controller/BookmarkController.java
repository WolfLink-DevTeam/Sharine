package org.wolflink.sharine.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Bookmark;
import org.wolflink.sharine.repository.BookmarkRepository;
import org.wolflink.sharine.service.BookmarkService;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController extends RestfulController<Bookmark,Long> {
    @Resource
    private BookmarkService bookmarkService;
    @Resource
    private SessionAction sessionAction;
    public BookmarkController(BookmarkRepository repository) {
        super(repository);
    }

    /**
     * 收藏视频
     * @param videoId   视频ID
     */
    @PostMapping("/byVideoId/{videoId}")
    public ResultPack postBookmark(@PathVariable Long videoId) {
        bookmarkService.bookmarkVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }

    @GetMapping("/byVideoId/{videoId}")
    public Object getBookmark(@PathVariable Long videoId) {
        return bookmarkService.getBookmark(sessionAction.getSessionUserId(), videoId);
    }

//    /**
//     * 是否已经收藏了视频 TODO 迁移到聚合模块进行封装，这里只提供增删改查
//     * @param videoId   视频ID
//     * @return          订阅状态
//     */
//    @GetMapping("/byVideoId/{videoId}")
//    public ResultPack hasBookmark(@PathVariable Long videoId) {
//        return ok(bookmarkService.hasBookmarkVideo(sessionAction.getSessionUserId(), videoId));
//    }
    /**
     * 取消收藏视频
     * @param videoId   视频ID
     */
    @DeleteMapping("/byVideoId/{videoId}")
    public ResultPack delBookmark(@PathVariable Long videoId) {
        bookmarkService.undoBookmarkVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }
    /**
     * 只允许查询用户自己的收藏视频列表Id数据
     */
    @GetMapping("/byUserId")
    public Object getUserBookmarkVideoIds() {
        // 获取用户喜欢的视频ID列表
        return bookmarkService.findUserBookmarkVideoIds(sessionAction.getSessionUserId());
    }
}
