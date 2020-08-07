package com.city.system.service;

import com.city.common.response.Result;
import com.city.system.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.query.UserQuery;
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
public interface IUserService extends IService<User> {

    /**
     * 判断是不是超级管理员
     *
     * @param account 账号
     */
    void isAdmin(String account);


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
     * @param userVo 参数
     * @return result
     * @throws NoSuchAlgorithmException 没有这样的算法
     * @throws InvalidKeySpecException  密钥无效
     */
    Result addUser(UserVo userVo) throws NoSuchAlgorithmException, InvalidKeySpecException;


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
     * @param userVo 参数
     * @return result
     */
    Result updateUser(UserVo userVo);


    /**
     * 修改用户状态
     *
     * @param userVo 参数
     * @return result
     */
    Result updateUserStatus(UserVo userVo);

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
     * @param userDto 参数
     * @return result
     */
    Result updatePassword(UserDto userDto);

}
