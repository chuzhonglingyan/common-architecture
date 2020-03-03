package com.yuntian.architecture.data;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/6/30 0030 17:57
 * @Description:
 */
public interface IBaseService<T> extends IService<T> {

    boolean deleteByIdWithFill(T entity);


    boolean deleteByIdsWithFill(Collection<T> entityList);

}
