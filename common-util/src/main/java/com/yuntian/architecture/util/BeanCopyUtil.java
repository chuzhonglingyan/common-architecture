package com.yuntian.architecture.util;


import org.springframework.beans.BeanUtils;

/**
 * @Auther: yuntian
 * @Date: 2020/2/1 0001 12:04
 * @Description:
 */
public class BeanCopyUtil {


    public static <F, T> void copyProperties(F source, T target) {
        BeanUtils.copyProperties(source,target);
    }

    public static <F, T> T copyProperties(F source, Class<T> targetClass) {
        T target = null;
        try {
            target = targetClass.newInstance();
            copyProperties(source, target);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return target;
    }
}
