package org.wolflink.sharine.config;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.cloud.consul.config.ConsulPropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConsulConfig {

    @Bean
    @Primary
    public ConsulPropertySourceLocator consulPropertySourceLocator(ConsulClient consulClient, ConsulConfigProperties consulConfigProperties) {
        return new ConsulPropertySourceLocator(consulClient,consulConfigProperties);
    }
}
