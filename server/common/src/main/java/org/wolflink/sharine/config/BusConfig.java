package org.wolflink.sharine.config;

import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RemoteApplicationEventScan({"org.wolflink.sharine.event"})
public class BusConfig {
}
