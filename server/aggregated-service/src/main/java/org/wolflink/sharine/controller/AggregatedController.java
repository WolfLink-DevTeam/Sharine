package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.IpAction;
import org.wolflink.sharine.dto.ResultPack;

@RestController
@AllArgsConstructor
@RequestMapping("/aggregated")
public class AggregatedController extends BaseController {

    private final IpAction ipAction;

    @GetMapping("/detail-video/{videoId}")
    public ResultPack getDetailVideo(@PathVariable Long videoId) {
        // TODO 未实现
        ResultPack resultPack = new ResultPack();
        resultPack.setCode(0);
        resultPack.setMsg("你好");
        System.out.println("收到请求");
        return resultPack;
    }

//    @PostMapping("/{videoId}/addViewCount")
//    public ResultPack addViewCount(HttpServletRequest request, @PathVariable Long videoId) {
//        String ip = ipAction.getIpAddress(request);
//        viewCountService.addViewCount(ip,videoId);
//        return ok();
//    }

}
