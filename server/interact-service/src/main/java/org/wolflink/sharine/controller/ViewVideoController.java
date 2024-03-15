package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.service.ViewVideoService;

@RestController
@AllArgsConstructor
@RequestMapping("/view-video")
public class ViewVideoController extends BaseController {
    private final SessionAction sessionAction;
    private final ViewVideoService videoService;
    @PostMapping
    public ResultPack viewVideo(@RequestParam Long videoId) {
        videoService.viewVideo(sessionAction.getSessionUserId(),videoId);
        return ok();
    }
}
