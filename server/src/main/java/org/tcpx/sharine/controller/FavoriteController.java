package org.tcpx.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.service.FavoriteService;
import org.tcpx.sharine.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController extends BaseController {

    final UserService userService;
    final FavoriteService favoriteService;

    @PostMapping("/{videoId}")
    public Object favorite(@PathVariable Long videoId) {
        favoriteService.favoriteVideo(userService.getSessionUserId(), videoId);
        return ok();
    }
    @GetMapping("/{videoId}")
    public Object hasFavorite(@PathVariable Long videoId) {
        return ok(favoriteService.hasFavoriteVideo(userService.getSessionUserId(), videoId));
    }
    @DeleteMapping("/{videoId}")
    public Object undoFavorite(@PathVariable Long videoId) {
        favoriteService.undoFavoriteVideo(userService.getSessionUserId(), videoId);
        return ok();
    }
}
