package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.service.UserRelationService;

@RestController
@AllArgsConstructor
@RequestMapping("/user-relation")
public class UserRelationController extends BaseController {

    private final SessionAction sessionAction;
    private final UserRelationService userRelationService;
    /**
     * 关注用户
     * @param anotherUserId 另一位用户的ID
     */
    @PostMapping("/follow/{anotherUserId}")
    public Object follow(@PathVariable Long anotherUserId) {
        userRelationService.follow(sessionAction.getSessionUserId(),anotherUserId);
        return ok();
    }
    @GetMapping("/follow/{anotherUserId}")
    public Object hasFollow(@PathVariable Long anotherUserId) {
        return ok(userRelationService.hasFollow(sessionAction.getSessionUserId(),anotherUserId));
    }
    @DeleteMapping("/follow/{anotherUserId}")
    public Object undoFollow(@PathVariable Long anotherUserId) {
        userRelationService.undoFollow(sessionAction.getSessionUserId(),anotherUserId);
        return ok();
    }
}
