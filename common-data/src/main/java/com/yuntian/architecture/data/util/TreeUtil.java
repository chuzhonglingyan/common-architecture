package com.yuntian.architecture.data.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yuntian.architecture.data.BaseTreeEntity;
import com.yuntian.architecture.data.ITree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuntian
 * @date 2020/3/4 0004 00:30
 * @description
 */
public class TreeUtil {


    public static <T extends BaseTreeEntity, V extends ITree<V>> List<V> buildTree(List<T> list, Class<V> targetClass) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<V> vList = convertList(list, targetClass);
        return buildTree(vList);
    }

    public static <T extends ITree<T>> List<T> buildTree(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<T> treeList = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (T treeVO : list) {
            if (treeVO.getPid() == 0) {
                treeList.add(treeVO);
            }
            for (T it : list) {
                if (it.getPid().equals(treeVO.getId())) {
                    if (treeVO.getChildren() == null) {
                        treeVO.setChildren(new ArrayList<>());
                    }
                    treeVO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (treeList.size() == 0) {
            treeList = list.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return treeList;
    }

    public static <T extends BaseTreeEntity, V extends ITree<V>> List<V> convertList(List<T> list, Class<V> targetClass) {
        return BeanCopyUtil.copyListProperties(list, targetClass);
    }


}
