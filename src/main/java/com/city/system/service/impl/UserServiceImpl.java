package com.city.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.city.common.response.ResponseFactory;
import com.city.common.response.Result;
import com.city.system.entity.User;
import com.city.system.mapper.UserMapper;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.query.UserQuery;
import com.city.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByAccount(String account) {
        User user = User.builder().account(account).build();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = User.builder().email(email).build();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByTel(String tel) {
        User user = User.builder().tel(tel).build();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Result addUser(UserDto userDto) {
        User user = new User();
        if (null != getUserByAccount("")) {
            return ResponseFactory.build(-1, "新增用户'" + userDto.getName() + "'失败，账号已存在");
        } else if (null != getUserByEmail("")) {
            return ResponseFactory.build(-1, "新增用户'" + userDto.getName() + "'失败，邮箱已存在");
        } else if (null != getUserByTel("")) {
            return ResponseFactory.build(-1, "新增用户'" + userDto.getName() + "'失败，手机号码已存在");
        } else {
            //拷贝
            BeanUtils.copyProperties(userDto, user);
            //盐和密码,权限
            int code = userMapper.insert(user) > 0 ? 200 : -1;
            return ResponseFactory.build(code, 1, "操作成功", user);
        }
    }

    @Override
    public Result deleteUser(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        //eq是什么用途
        wrapper.eq("id", id);
        User user = User.builder().id(id).build();
        int code = userMapper.update(user, wrapper) > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", user);
    }

    @Override
    public Result deleteUserBatch(Long[] ids) {
        return null;
    }

    @Override
    public Result updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getUser(Long id) {
        UserDto userDto = new UserDto();
        User user = userMapper.selectById(id);
        BeanUtils.copyProperties(user, userDto);
        //权限
        return userDto;
    }

    @Override
    public Result getUserList(UserQuery userQuery) {
        return null;
    }

    @Override
    public Result updatePassword(Long id, String oldPassword, String newPassword) {
        return null;
    }
}
