package com.yuntian.architecture.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author yuntian
 * @date 2019/6/18 0018 20:57
 * @description
 */
@Data
public class BaseEntity implements Serializable {


    private Long id;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateId;

    /**
     * 更新时间
     */
    private Date updateTime;


}
