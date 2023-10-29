package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.service.FavoriteService;

@RestController("/favorite")
public class FavoriteController extends BaseController {

    final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/toggle/{userId}/{videoId}")
    public Object toggleFavorite(@PathVariable Long userId, @PathVariable Long videoId) {
        return ok(favoriteService.toggleVideoFavorite(userId, videoId));
    }
}
