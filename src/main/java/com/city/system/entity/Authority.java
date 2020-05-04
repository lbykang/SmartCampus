package com.city.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.city.system.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_authority")
@ApiModel(value="Authority对象", description="")
public class Authority extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限标识")
    private String mark;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序码")
    private Boolean sort;


}
