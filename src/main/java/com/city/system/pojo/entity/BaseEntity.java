package com.city.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * @author mirror6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @TableId()
    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField(value = "is_enabled")
    @ApiModelProperty(value = "是否启用：1 表示禁用，0 表示启用。默认：0")
    private Integer enabled;

    @ApiModelProperty(value = "创建人主键")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改人主键")
    private Long updateUserId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableLogic
    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除：1 表示删除，0 表示未删除。默认：0")
    private Integer deleted;

}
