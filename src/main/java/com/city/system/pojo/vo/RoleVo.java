package com.city.system.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author mirror6
 * @description
 * @createTime 2020/8/7 15:10
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleVo extends BaseVo {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序码")
    private Boolean sort;

}
