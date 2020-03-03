package com.yuntian.architecture.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 * @date: 2020/3/3 0003 23:10
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTreeVO extends BaseTreeEntity {

    private List<BaseTreeVO> children;


}
