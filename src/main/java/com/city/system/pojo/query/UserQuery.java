package com.city.system.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author mirror6
 * @description
 * @createTime 2020/5/7 15:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery extends BaseQuery {

    private String name;

    private String tel;

    private Integer enabled;

    private Date[] gmtCreate;
}
