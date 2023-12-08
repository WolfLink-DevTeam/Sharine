package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.service.FavoriteService;
import org.wolflink.sharine.action.SessionAction;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController extends BaseController {

    final FavoriteService favoriteService;
    final SessionAction sessionAction;

    @PostMapping("/{videoId}")
    public ResultPack favorite(@PathVariable Long videoId) {
        favoriteService.favoriteVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }
    @GetMapping("/{videoId}")
    public ResultPack hasFavorite(@PathVariable Long videoId) {
        return ok(favoriteService.hasFavoriteVideo(sessionAction.getSessionUserId(), videoId));
    }
    @DeleteMapping("/{videoId}")
    public ResultPack undoFavorite(@PathVariable Long videoId) {
        favoriteService.undoFavoriteVideo(sessionAction.getSessionUserId(), videoId);
        return ok();
    }
    /**
     * 只允许查询用户自己的点赞视频列表数据
     */
    @GetMapping
    public ResultPack getUserFavoriteVideoIds() {
        // 获取用户喜欢的视频ID列表
        List<Long> favoriteVideoIds = favoriteService.findUserFavoriteVideoIds(sessionAction.getSessionUserId());
        // 转 VideoVO 列表
        return ok(favoriteVideoIds);
    }
}
