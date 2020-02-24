package com.yuntian.architecture.util;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }


    public static <F, T> List<T> copyListProperties(List<F> sources,Class<T> targetClass) {
        List<T> list = new ArrayList<>(sources.size());
        for (F source : sources) {
            list.add(copyProperties(source,targetClass));
        }
        return list;
    }


}
