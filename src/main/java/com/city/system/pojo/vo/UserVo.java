package com.city.system.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mirror6
 * @description
 * @createTime 2020/8/6 14:06
 */
@Data
@NoArgsConstructor
public class UserVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "名称（教师姓名、学生姓名）")
    private String name;

    @ApiModelProperty(value = "性别")
    private Long gender;

    @ApiModelProperty(value = "联系电话")
    private String tel;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "角色")
    private Long[] roleIds;

    @TableField(value = "is_enabled")
    @ApiModelProperty(value = "是否启用：1 表示禁用，0 表示启用。默认：0")
    private Integer enabled;
}
