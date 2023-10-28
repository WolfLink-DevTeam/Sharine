package org.tcpx.sharine.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.DatabaseConst;

@Service
@CacheConfig(cacheNames = DatabaseConst.COMMENT)
public class CommentService {
}
