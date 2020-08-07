package com.city.system.service;

import com.city.common.response.Result;
import com.city.system.pojo.dto.RoleDto;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.city.system.pojo.query.RoleQuery;
import com.city.system.pojo.query.UserQuery;
import com.city.system.pojo.vo.RoleVo;
import com.city.system.pojo.vo.UserVo;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
public interface IRoleService extends IService<Role> {


    /**
     * 添加角色
     *
     * @param roleVo 参数
     * @return result 结果
     */
    Result addRole(RoleVo roleVo);


    /**
     * 删除角色
     *
     * @param id 主键
     * @return result
     */
    Result deleteRole(Long id);

    /**
     * 批量删除角色
     *
     * @param ids 主键数组
     * @return result
     */
    Result deleteRoleBatch(Long[] ids);

    /**
     * 修改角色
     *
     * @param roleVo 参数
     * @return result
     */
    Result updateRole(RoleVo roleVo);

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return Role
     */
    RoleDto getRole(Long id);

    /**
     * 用户列表
     *
     * @param roleQuery 参数
     * @return result
     */
    Result getRoleList(RoleQuery roleQuery);
}
