package com.city.system.pojo.dto;

import com.city.system.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mirror6
 * @description
 * @createTime 2020/5/7 13:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseEntity {

    @ApiModelProperty(value = "账号（学号、工号）")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐加密")
    private String salt;

    @ApiModelProperty(value = "登录名称（教师姓名、学生姓名）")
    private String name;

    @ApiModelProperty(value = "性别")
    private Long gender;

    @ApiModelProperty(value = "联系电话")
    private String tel;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "角色")
    private Long[] roleIds;
}
