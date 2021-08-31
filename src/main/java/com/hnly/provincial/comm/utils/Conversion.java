package com.hnly.provincial.comm.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 工具类
 * List中的对象转换 返回list
 * </p>
 * @author maqh
 * @since 2019/9/9 10:41
 */
@Slf4j
public class Conversion {
    private Conversion(){}

    /**
     * list的属性对象转换
     * t 为null 返回null
     *
     * @param t   需要转换的list
     * @param b   需要转换的list属性对象
     * @param <T> 转换前属性对象
     * @param <B> 转换后属性对象
     * @return List<B>
     */
    public static <T, B> List<B> changeList(List<T> t, Class<B> b) {
        List<B> result = null;
        if (t != null) {
            result = new ArrayList<>();
            for (T t1 : t) {
                try {
                    B be = b.getDeclaredConstructor().newInstance();
                    BeanUtils.copyProperties(t1, be);
                    result.add(be);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }
        return result;
    }

    /**
     * 单一对象属性对象转换
     * t 为null 返回null
     *
     * @param t   需要转换的list
     * @param b   需要转换的list属性对象
     * @param <T> 转换前属性对象
     * @param <B> 转换后属性对象
     * @return B
     */
    public static <T, B> B changeOne(T t, Class<B> b) {
        B result = null;
        if (t != null) {
            try {
                B be = b.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(t, be);
                result = be;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                log.error(e.getMessage(), e);
            }
        }
        return result;
    }
}
