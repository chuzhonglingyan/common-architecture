package com.yuntian.architecture.datasource;

import lombok.Data;

/**
 * @Auther: yuntian
 * @Date: 2019/6/25 0025 22:42
 * @Description:
 */
@Data
public class BaseDTO {

    /**
     * 每页显示条数，默认 10
     */
    private Long size = 10L;
    /**
     * 当前页
     */
    private Long current = 1L;


    /**
     * 创建人
     */
    private Long createId;

    /**
     * 更新人
     */
    private Long updateId;

}
