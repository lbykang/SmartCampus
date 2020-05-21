package com.city.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.common.response.ResponseFactory;
import com.city.common.response.Result;
import com.city.common.security.PasswordEncryption;
import com.city.system.entity.User;
import com.city.system.entity.UserRole;
import com.city.system.mapper.UserMapper;
import com.city.system.mapper.UserRoleMapper;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.query.UserQuery;
import com.city.system.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private UserRoleMapper userRoleMapper;

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
    @Transactional(rollbackFor = Exception.class)
    public Result addUser(UserDto userDto) {
        User user = new User();
        if (null != getUserByAccount(userDto.getAccount())) {
            return ResponseFactory.build(-1, "新增用户'" + userDto.getName() + "'失败，账号已存在");
        } else if (null != userDto.getEmail() && null != getUserByEmail(userDto.getEmail())) {
            return ResponseFactory.build(-1, "新增用户'" + userDto.getName() + "'失败，邮箱已存在");
        } else if (null != userDto.getTel() && null != getUserByTel(userDto.getTel())) {
            return ResponseFactory.build(-1, "新增用户'" + userDto.getName() + "'失败，手机号码已存在");
        } else {
            //拷贝
            userDto.setRoleIds(new Long[]{1L});
            BeanUtils.copyProperties(userDto, user);
            //盐和密码
            try {
                String salt = PasswordEncryption.generateSalt();
                String password = PasswordEncryption.getEncryptedPassword(user.getAccount(), salt);
                user.setSalt(salt);
                //32位密文
                user.setPassword(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setCreateUserId(1L);
            int userCount = userMapper.insert(user);
            int relationship = insertUserRoleBatch(user, userDto.getRoleIds());
            int code = userCount > 0 && relationship > 0 ? 200 : -1;
            return ResponseFactory.build(code, 1, "操作成功", user);
        }
    }


    /**
     * 批量插入 用户-角色关联关系
     *
     * @param user    用户
     * @param roleIds 角色主键
     * @return int
     */
    private int insertUserRoleBatch(User user, Long[] roleIds) {
        List<UserRole> userRoles = new LinkedList<>();
        for (Long roleId : roleIds) {
            UserRole userRole = UserRole.builder().userId(user.getId()).roleId(roleId).build();
            userRoles.add(userRole);
        }
        return userRoleMapper.insertBatch(userRoles);
    }

    @Override
    public Result deleteUser(Long id) {
        int code = userMapper.deleteById(id) > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", null);
    }

    @Override
    public Result deleteUserBatch(Long[] ids) {
        int code = userMapper.deleteBatchIds(Arrays.asList(ids)) > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        //判断是否修改角色
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("user_id", userDto.getId());
        List oldRoleIds = userRoleMapper.selectByMap(columnMap).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        int code;
        int userCount;
        if (!Arrays.asList(userDto.getRoleIds()).equals(oldRoleIds)) {
            // 修改关系表
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id", user.getId());
            // 删除全部关联表
            userRoleMapper.delete(wrapper);
            // 插入关系表
            userDto.setRoleIds(new Long[]{1L, 3L});
            int relationCount = insertUserRoleBatch(user, userDto.getRoleIds());
            userCount = userMapper.updateById(user);
            code = userCount > 0 && relationCount > 0 ? 200 : -1;
        } else {
            userCount = userMapper.updateById(user);
            code = userCount > 0 ? 200 : -1;
        }
        return ResponseFactory.build(code, 1, "操作成功", user);
    }

    @Override
    public UserDto getUser(Long id) {
        UserDto userDto = new UserDto();
        User user = userMapper.selectById(id);
        BeanUtils.copyProperties(user, userDto);
        //权限
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
        List<Long> roleIdList = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        Long[] roleIds = new Long[roleIdList.size()];
        for (int i = 0; i < roleIdList.size(); i++) {
            roleIds[i] = roleIdList.get(i);
        }
        userDto.setRoleIds(roleIds);
        return userDto;
    }

    @Override
    public Result getUserList(UserQuery userQuery) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("id", "account", "name", "gender", "tel", "email", "is_enabled", "gmt_create");
        wrapper.orderByDesc("gmt_create");
        Page<User> page = new Page<>(Optional.ofNullable(userQuery.getPageNum()).orElse(1),
                Optional.ofNullable(userQuery.getPageSize()).orElse(10));
        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        return ResponseFactory.build(Optional.ofNullable(userIPage).orElse(new Page<>()));
    }

    @Override
    public Result updatePassword(UserDto userDto) {
        User user = User.builder().id(userDto.getId()).password(userDto.getPassword()).build();
        int code = userMapper.updateById(user);
        return ResponseFactory.build(code, 1, "操作成功", userDto);
    }
}
