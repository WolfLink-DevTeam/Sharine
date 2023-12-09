package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.IpAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.service.AggregatedService;

@RestController
@AllArgsConstructor
@RequestMapping("/aggregated")
public class AggregatedController extends BaseController {

    private final AggregatedService aggregatedService;

    @GetMapping("/detail-video/{videoId}")
    public ResultPack getDetailVideo(@PathVariable Long videoId) {
        // TODO 未实现
        ResultPack resultPack = new ResultPack();
        return resultPack;
    }

    @GetMapping("/subscribe-videos/{userId}")
    public ResultPack getSubscribeVideos(@PathVariable Long userId) {
        return ok(aggregatedService.getSubscribeVideos(userId));
    }

//    @PostMapping("/{videoId}/addViewCount")
//    public ResultPack addViewCount(HttpServletRequest request, @PathVariable Long videoId) {
//        String ip = ipAction.getIpAddress(request);
//        viewCountService.addViewCount(ip,videoId);
//        return ok();
//    }

}
