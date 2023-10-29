package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.service.BookmarkService;

@RestController("/"+ DatabaseConst.BOOKMARK)
public class BookmarkController extends BaseController {

    BookmarkService bookmarkService;
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/toggle/{userId}/{videoId}")
    public Object toggleVideoBookmark(@PathVariable Long userId, @PathVariable Long videoId) {
        return ok(bookmarkService.toggleBookmark(userId, videoId));
    }

}
