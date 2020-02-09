package com.yuntian.architecture.util;


import org.springframework.cglib.beans.BeanCopier;

/**
 * @Auther: yuntian
 * @Date: 2020/2/1 0001 12:04
 * @Description:
 */
public class BeanCopyUtil {


    public static <F, T> void copyProperties(F source, T target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source,target,null);
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
