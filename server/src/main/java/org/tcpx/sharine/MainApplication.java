package org.tcpx.sharine;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.tcpx.sharine.utils.IOC;

@SpringBootApplication
public class MainApplication {
    @Getter
    private static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(MainApplication.class, args);
        IOC.init(applicationContext);
    }

}
