package com.city.system.service;

import com.city.common.response.Result;
import com.city.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.query.UserQuery;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
public interface IUserService extends IService<User> {


    /**
     * 通过账号获取用户信息 校验唯一
     *
     * @param account 参数
     * @return User
     */
    User getUserByAccount(String account);


    /**
     * 通过邮箱获取用户信息 校验唯一
     *
     * @param email 参数
     * @return User
     */
    User getUserByEmail(String email);


    /**
     * 通过手机号码获取用户信息 校验唯一
     *
     * @param tel 参数
     * @return User
     */
    User getUserByTel(String tel);

    /**
     * 添加用户
     *
     * @param userDto 参数
     * @return result
     */
    Result addUser(UserDto userDto);

    /**
     * 删除用户
     *
     * @param id 用户主键
     * @return result
     */
    Result deleteUser(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 用户主键数组
     * @return result
     */
    Result deleteUserBatch(Long[] ids);

    /**
     * 修改用户
     *
     * @param userDto 参数
     * @return result
     */
    Result updateUser(UserDto userDto);

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return User
     */
    UserDto getUser(Long id);

    /**
     * 用户列表
     *
     * @param userQuery 参数
     * @return result
     */
    Result getUserList(UserQuery userQuery);

    /**
     * 修改密码
     *
     * @param id          用户主键
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return result
     */
    Result updatePassword(Long id, String oldPassword, String newPassword);

}
