package com.city.system.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author mirror6
 * @description
 * @createTime 2020/8/6 14:06
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserVo extends BaseVo {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "名称（教师姓名）")
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
