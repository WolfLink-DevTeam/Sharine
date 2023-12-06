package org.tcpx.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.service.BookmarkService;
import org.tcpx.sharine.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController {

    final UserService userService;
    final BookmarkService bookmarkService;

    /**
     * 收藏视频
     * @param videoId   视频ID
     */
    @PostMapping("/{videoId}")
    public Object bookmark(@PathVariable Long videoId) {
        bookmarkService.bookmarkVideo(userService.getSessionUserId(), videoId);
        return ok();
    }

    /**
     * 是否已经订阅了视频
     * @param videoId   视频ID
     * @return          订阅状态
     */
    @GetMapping("/{videoId}")
    public Object hasBookmark(@PathVariable Long videoId) {
        return ok(bookmarkService.hasBookmarkVideo(userService.getSessionUserId(), videoId));
    }
    /**
     * 取消收藏视频
     * @param videoId   视频ID
     */
    @DeleteMapping("/{videoId}")
    public Object undoBookmark(@PathVariable Long videoId) {
        bookmarkService.undoBookmarkVideo(userService.getSessionUserId(), videoId);
        return ok();
    }
}
