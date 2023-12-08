package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.service.BookmarkService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController {

    final BookmarkService bookmarkService;
    final SessionAction sessionAction;

    /**
     * 收藏视频
     * @param videoId   视频ID
     */
    @PostMapping("/{videoId}")
    public ResultPack bookmark(@PathVariable Long videoId) {
        bookmarkService.bookmarkVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }

    /**
     * 是否已经收藏了视频
     * @param videoId   视频ID
     * @return          订阅状态
     */
    @GetMapping("/{videoId}")
    public ResultPack hasBookmark(@PathVariable Long videoId) {
        return ok(bookmarkService.hasBookmarkVideo(sessionAction.getSessionUserId(), videoId));
    }
    /**
     * 取消收藏视频
     * @param videoId   视频ID
     */
    @DeleteMapping("/{videoId}")
    public ResultPack undoBookmark(@PathVariable Long videoId) {
        bookmarkService.undoBookmarkVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }
    /**
     * 只允许查询用户自己的收藏视频列表Id数据
     */
    @GetMapping
    public ResultPack getUserBookmarkVideoIds() {
        // 获取用户喜欢的视频ID列表
        List<Long> bookmarkVideoIds = bookmarkService.findUserBookmarkVideoIds(sessionAction.getSessionUserId());
        // 转 VideoVO 列表
        return ok(bookmarkVideoIds);
    }
}
