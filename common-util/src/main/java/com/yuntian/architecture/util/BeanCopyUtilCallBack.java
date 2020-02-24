package com.yuntian.architecture.util;

/**
 * @Auther: yuntian
 * @Date: 2020/2/23 0023 10:48
 * @Description:
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack <S, T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}