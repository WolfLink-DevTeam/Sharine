package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.constants.DatabaseConst;

@RestController("/"+ DatabaseConst.BOOKMARK)
public class BookmarkController extends BaseController {
}
