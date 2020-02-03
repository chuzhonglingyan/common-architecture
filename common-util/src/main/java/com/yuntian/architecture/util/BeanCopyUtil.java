package com.yuntian.architecture.util;


import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentMap;

/**
 * @Auther: yuntian
 * @Date: 2020/2/1 0001 12:04
 * @Description:
 */
public class BeanCopyUtil {


    private static final ConcurrentMap<Class, MethodAccess> LOCAL_CACHE = Maps.newConcurrentMap();

    public static MethodAccess get(Class clazz) {
        if (LOCAL_CACHE.containsKey(clazz)) {
            return LOCAL_CACHE.get(clazz);
        }
        MethodAccess methodAccess = MethodAccess.get(clazz);
        LOCAL_CACHE.putIfAbsent(clazz, methodAccess);
        return methodAccess;
    }

    public static <F, T> void copyProperties(F source, T target) {
        MethodAccess fromMethodAccess = get(source.getClass());
        MethodAccess toMethodAccess = get(target.getClass());
        Field[] fromDeclaredFields = source.getClass().getDeclaredFields();
        for (Field field : fromDeclaredFields) {
            String name = field.getName();
            try {
                Object value = fromMethodAccess.invoke(source, "get" + StringUtils.capitalize(name), null);
                toMethodAccess.invoke(target, "set" + StringUtils.capitalize(name), value);
            } catch (Exception e) {
                // 设置异常，可能会没有对应字段，忽略
            }
        }
    }

    public static <F, T> T copyProperties(F source, Class<T> targetClass) {
        ConstructorAccess<T> constructorAccess = ConstructorAccess.get(targetClass);
        T target = constructorAccess.newInstance();
        copyProperties(source, target);
        return target;
    }
}
