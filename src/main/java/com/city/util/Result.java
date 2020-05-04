package com.city.util;

import lombok.Data;

/**
 * @author mirror6
 * @description service层开始返回Result
 * @date 2019/8/26 11:19
 */
@Data
public class Result {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 需要返回的数据对象
     */
    private Object data;

    private int count = 0;

}
