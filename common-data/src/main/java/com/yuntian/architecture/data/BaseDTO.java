package com.yuntian.architecture.data;

import java.io.Serializable;

import lombok.Data;

/**
 * @Auther: yuntian
 * @Date: 2019/6/25 0025 22:42
 * @Description:
 */
@Data
public class BaseDTO implements Serializable {

    /**
     * 每页显示条数，默认 10
     */
    private Long size = 10L;
    /**
     * 当前页
     */
    private Long current;

    private Long page;


    /**
     * 创建人
     */
    private Long createId;

    /**
     * 更新人
     */
    private Long updateId;

    public Long getCurrent() {
        if (current == null) {
            if (page == null) {
                return 1L;
            }
            return page;
        }
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}
