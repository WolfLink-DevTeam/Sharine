package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.service.FavoriteService;

@RestController("/"+ DatabaseConst.FAVOURITE)
public class FavoriteController extends BaseController {

    final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/do-favorite")
    public Object doFavorite(@RequestParam Long userId, @RequestParam Long videoId) {
        favoriteService.favoriteVideo(userId, videoId);
        return ok();
    }
    @DeleteMapping("/undo-favorite")
    public Object undoFavorite(@RequestParam Long userId, @RequestParam Long videoId) {
        favoriteService.undoFavoriteVideo(userId, videoId);
        return ok();
    }
}
