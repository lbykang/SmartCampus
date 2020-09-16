package com.city.system.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mirror6
 * @description
 * @createTime 2020/8/7 15:13
 */
@Data
@NoArgsConstructor
public class BaseVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField(value = "is_enabled")
    @ApiModelProperty(value = "是否启用：1 表示禁用，0 表示启用。默认：0")
    private Integer enabled;
}
