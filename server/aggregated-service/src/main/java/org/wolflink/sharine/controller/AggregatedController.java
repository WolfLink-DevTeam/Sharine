package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.IpAction;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.service.AggregatedService;
import org.wolflink.sharine.vo.VideoVO;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/aggregated")
public class AggregatedController extends BaseController {

    private final SessionAction sessionAction;
    private final AggregatedService aggregatedService;

    @GetMapping("/detail-video/{videoId}")
    public ResultPack getDetailVideo(@PathVariable Long videoId) {
        // TODO 未实现
        ResultPack resultPack = new ResultPack();
        return resultPack;
    }

    @GetMapping("/subscribe-videos/{userId}")
    public ResultPack getSubscribeVideos(@PathVariable Long userId) {
        if(!userId.equals(sessionAction.getSessionUserId())) throw new WarnException(StatusCodeEnum.UNAUTHORIZED);
        return ok(aggregatedService.getSubscribeVideos(userId));
    }

    @GetMapping("/favorite-videos/{userId}")
    public ResultPack getUserFavoriteVideos(@PathVariable Long userId) {
        // 获取 VideoVO 列表
        return ok(aggregatedService.getFavoriteVideos(userId));
    }
    @GetMapping("/bookmark-videos/{userId}")
    public ResultPack getUserBookmarkVideos(@PathVariable Long userId) {
        return ok(aggregatedService.getBookmarkVideos(userId));
    }

//    @PostMapping("/{videoId}/addViewCount")
//    public ResultPack addViewCount(HttpServletRequest request, @PathVariable Long videoId) {
//        String ip = ipAction.getIpAddress(request);
//        viewCountService.addViewCount(ip,videoId);
//        return ok();
//    }

}
