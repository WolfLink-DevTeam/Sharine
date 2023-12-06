package org.wolflink.sharine.api.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wolflink.sharine.api.RequestLimitInterceptor;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    final RequestLimitInterceptor requestLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLimitInterceptor).addPathPatterns("/**");
    }
}
