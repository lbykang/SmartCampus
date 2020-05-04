package com.city.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private int isEnabled;

    private Long creator;

    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;

    private Long updater;

    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 默认0
     */
    @TableLogic
    private int isDeleted;

}
