package com.city.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.city.system.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@TableName("system_user")
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    @Builder
    public User(Long id, int enabled, Long createUserId, Date gmtCreate, Long updateUserId, Date gmtModified, int deleted, String account, String password, String salt, String name, Long gender, String tel, String email) {
        super(id, enabled, createUserId, gmtCreate, updateUserId, gmtModified, deleted);
        this.account = account;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
    }
}
