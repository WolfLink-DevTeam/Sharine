package org.wolflink.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.service.BookmarkService;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController extends BaseController {
    @Resource
    private BookmarkService bookmarkService;
    @Resource
    private SessionAction sessionAction;

    /**
     * 收藏视频
     * @param videoId   视频ID
     */
    @PostMapping
    public ResultPack postBookmark(@RequestParam Long videoId) {
        bookmarkService.bookmarkVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }

    @GetMapping
    public Object getBookmark(@RequestParam Long videoId) {
        return ok(bookmarkService.getBookmark(sessionAction.getSessionUserId(), videoId));
        // TODO 放到 bookmark-action 里面，查询用户自己订阅的所有视频ID，或者不设计该接口，功能委托给聚合模块
//        else return ok(bookmarkService.findUserBookmarkVideoIds(sessionAction.getSessionUserId()));
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
    @DeleteMapping
    public ResultPack delBookmark(@RequestParam Long videoId) {
        bookmarkService.undoBookmarkVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }
}
