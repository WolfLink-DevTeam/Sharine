package org.tcpx.sharine.utils;

import org.tcpx.sharine.MainApplication;

public class IOC {
    public static <T> T getBean(Class<T> tClass) {
        return MainApplication.getApplicationContext().getBean(tClass);
    }
}
