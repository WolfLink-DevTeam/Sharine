package org.tcpx.sharine.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 复制对象或集合属性
 *
 * @author bin
 * @date 2021/08/10
 */
@Component
public class BeanCopyUtils {

    /**
     * 复制对象
     *
     * @param source 源
     * @param target 目标
     * @return {@link T}
     */
    public <T> T copyObject(Object source, Class<T> target) {
        T temp = null;
        try {
            temp = target.getDeclaredConstructor().newInstance();
            if (null != source) {
                org.springframework.beans.BeanUtils.copyProperties(source, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 拷贝集合
     *
     * @param source 源
     * @param target 目标
     * @return {@link List<T>} 集合
     */
    public <T, S> List<T> copyList(List<S> source, Class<T> target) {
        List<T> list = new ArrayList<>();
        if (null != source && !source.isEmpty()) {
            for (Object obj : source) {
                list.add(copyObject(obj, target));
            }
        }
        return list;
    }
}