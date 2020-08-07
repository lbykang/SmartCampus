package com.city.system.pojo.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author mirror6
 * @description
 * @createTime 2020/8/7 15:19
 */
public class RoleDto {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序码")
    private Boolean sort;
}
