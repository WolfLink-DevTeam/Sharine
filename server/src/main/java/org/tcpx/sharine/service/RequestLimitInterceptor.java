package org.tcpx.sharine.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tcpx.sharine.utils.IpUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RequestLimitInterceptor implements HandlerInterceptor {

    @Value("${application.query-per-minute-limit}")
    Integer queryPerMinuteLimit;
    final Map<String,Integer> limitMap = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 60000)
    public void startRefreshTimer() {
        // 简单清洗下，时间紧任务重
        limitMap.clear();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtils.getIpAddress(request);
        limitMap.put(ip,limitMap.getOrDefault(ip,0) + 1);
        if(limitMap.get(ip) > queryPerMinuteLimit) {
            System.out.println("IP:"+ip+" 用户达到QPM限制，暂时拦截其请求。");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
