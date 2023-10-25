package org.tcpx.sharine.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.DatabaseConstants;

@Service
@CacheConfig(cacheNames = DatabaseConstants.FOLLOW)
public class FollowService {

}
