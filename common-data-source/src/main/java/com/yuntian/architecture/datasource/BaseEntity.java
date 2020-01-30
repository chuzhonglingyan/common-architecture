package com.yuntian.architecture.datasource;

import java.util.Date;

import lombok.Data;

/**
 * @Auther: yuntian
 * @Date: 2019/6/18 0018 20:57
 * @Description:
 */
@Data
public class BaseEntity {


    private Long id;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateId;

    /**
     * 更新时间
     */
    private Date updateTime;


}
