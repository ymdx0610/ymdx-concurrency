package com.ymdx.concurrency06.demo;

/**
 * @ClassName: LongEvent
 * @Description: 需要传递的数据
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-25 09:23
 * @Version: 1.0
 **/
public class LongEvent {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
