package com.yuntian.architecture.data;

import java.util.List;

/**
 * @author yuntian
 * @date 2020/3/4 0004 22:41
 * @description
 */
public interface ITree<T> {

    Long getId();

    Long getPid();

    List<T> getChildren();

    void setChildren(List<T> list);
}
