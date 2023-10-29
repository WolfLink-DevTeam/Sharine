package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.service.BookmarkService;

@RestController("/"+ DatabaseConst.BOOKMARK)
public class BookmarkController extends BaseController {

    BookmarkService bookmarkService;
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/do-bookmark")
    public Object doFavorite(@RequestParam Long userId, @RequestParam Long videoId) {
        bookmarkService.bookmarkVideo(userId, videoId);
        return ok();
    }
    @DeleteMapping("/undo-bookmark")
    public Object undoFavorite(@RequestParam Long userId, @RequestParam Long videoId) {
        bookmarkService.undoBookmarkVideo(userId, videoId);
        return ok();
    }

}
