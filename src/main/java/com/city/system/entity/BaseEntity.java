package com.city.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author mirror6
 */
@Data
public class BaseEntity {

    @TableId()
    private Long id;

    /**
     * 默认0
     */
    @TableField(value = "is_enabled")
    private int enabled;

    private Long createUserId;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    private Long updateUserId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 默认0
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private int deleted;

}
