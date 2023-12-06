package org.wolflink.sharine;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class UserApplication {
    @Getter
    private static ApplicationContext applicationContext = null;
    @SuppressWarnings("仅供测试")
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(UserApplication.class, args);
    }
}