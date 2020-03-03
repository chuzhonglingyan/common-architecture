package com.yuntian.architecture.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: yuntian
 * @Date: 2020/3/3 0003 23:10
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTreeEntity extends BaseEntity {

    /**
     * 父级id
     */
    private Long pid;

    /**
     * 级别-深度
     */
    private Integer level;

    /**
     * 本级排序
     */
    private Integer sort;


}
