package org.tcpx.sharine.backend.config;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/sharine")
                .username("testaccount")
                .password("testpassword")
                .build();
    }
    @Bean
    public JpaProperties jpaProperties() {
        JpaProperties properties = new JpaProperties();
        properties.getProperties().put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}
