package org.tcpx.sharine.utils;

import org.springframework.context.ApplicationContext;
import org.tcpx.sharine.MainApplication;

public class IOC {

    private static ApplicationContext context;

    public static void init(ApplicationContext context) {
        IOC.context = context;
    }
    public static <T> T getBean(Class<T> tClass) {
        return context.getBean(tClass);
    }
}
