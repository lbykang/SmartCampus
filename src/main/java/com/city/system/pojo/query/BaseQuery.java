package com.city.system.pojo.query;

import lombok.Data;

/**
 * @author mirror6
 * @description
 * @createTime 2020/5/9 14:05
 */
@Data
public class BaseQuery {

    /**
     * 每页的数据数量
     */
    Integer pageSize;

    /**
     * 第几页
     */
    Integer pageNum;
}
