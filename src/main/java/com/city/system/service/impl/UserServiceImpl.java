package com.city.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.common.constant.Constant;
import com.city.common.constant.UserConstant;
import com.city.common.exception.CustomException;
import com.city.common.response.ResponseFactory;
import com.city.common.response.Result;
import com.city.common.security.PasswordEncryption;
import com.city.common.utils.SecurityUtils;
import com.city.common.utils.StringUtils;
import com.city.system.pojo.entity.User;
import com.city.system.pojo.entity.UserRole;
import com.city.system.mapper.UserMapper;
import com.city.system.mapper.UserRoleMapper;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.query.UserQuery;
import com.city.system.pojo.vo.UserVo;
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
    public void isAdmin(String account) {
        if (UserConstant.IS_ADMIN.equals(account)) {
            throw new CustomException(-1, "不允许操作超级管理员用户");
        }
    }

    @Override
    public User getUserByAccount(String account) {
        User user = User.builder().account(account).deleted(0).build();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = User.builder().email(email).deleted(0).build();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByTel(String tel) {
        User user = User.builder().tel(tel).deleted(0).build();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        return userMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addUser(UserVo userVo) {
        User user = new User();
        if (null != getUserByAccount(userVo.getAccount())) {
            return ResponseFactory.build(-1, "新增用户'" + userVo.getName() + "'失败，账号已存在");
        } else if (null != userVo.getEmail() && null != getUserByEmail(userVo.getEmail())) {
            return ResponseFactory.build(-1, "新增用户'" + userVo.getName() + "'失败，邮箱已存在");
        } else if (null != userVo.getTel() && null != getUserByTel(userVo.getTel())) {
            return ResponseFactory.build(-1, "新增用户'" + userVo.getName() + "'失败，手机号码已存在");
        } else {
            //拷贝
            userVo.setRoleIds(new Long[]{1L});
            BeanUtils.copyProperties(userVo, user);
            //盐和密码
            try {
                String salt = PasswordEncryption.generateSalt();
                String password = SecurityUtils.encryptPassword(user.getAccount());
//                PasswordEncryption.getEncryptedPassword(user.getAccount(), salt);
                user.setSalt(salt);
                //80位密文
                user.setPassword(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setCreateUserId(1L);
            int userCount = userMapper.insert(user);
            int relationship = insertUserRoleBatch(user, userVo.getRoleIds());
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
    public Result updateUser(UserVo userVo) {
        //判断是不是超级管理员
        isAdmin(userVo.getAccount());
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        //判断是否修改角色
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("user_id", userVo.getId());
        List oldRoleIds = userRoleMapper.selectByMap(columnMap).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        int code;
        int userCount;
        if (StringUtils.isNotEmpty(userVo.getRoleIds()) && !Arrays.asList(userVo.getRoleIds()).equals(oldRoleIds)) {
            // 修改关系表
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id", user.getId());
            // 删除全部关联表
            userRoleMapper.delete(wrapper);
            // 插入关系表
            userVo.setRoleIds(new Long[]{1L, 3L});
            int relationCount = insertUserRoleBatch(user, userVo.getRoleIds());
            userCount = userMapper.updateById(user);
            code = userCount > 0 && relationCount > 0 ? 200 : -1;
        } else {
            userCount = userMapper.updateById(user);
            code = userCount > 0 ? 200 : -1;
        }
        return ResponseFactory.build(code, 1, "操作成功", user);
    }

    @Override
    public Result updateUserStatus(UserVo userVo) {
        //判断是不是超级管理员
        isAdmin(userVo.getAccount());
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        int userCount = userMapper.updateById(user);
        int code = userCount > 0 ? 200 : -1;
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
        wrapper.select("id", "account", "name", "gender", "tel", "email", "is_enabled as enabled", "gmt_create");
        if (null != userQuery.getName()) {
            wrapper.like("name", userQuery.getName());
        }
        if (null != userQuery.getTel()) {
            wrapper.like("tel", userQuery.getTel());
        }
        if (null != userQuery.getEnabled()) {
            wrapper.eq("is_enabled", userQuery.getEnabled());
        }
        if (null != userQuery.getGmtCreate()) {
            wrapper.between("gmt_create", 1, 2);
        }
        wrapper.orderByDesc("gmt_create");
        Page<User> page = new Page<>(Optional.ofNullable(userQuery.getPageNum()).orElse(Constant.INIT_PAGE_NUM),
                Optional.ofNullable(userQuery.getPageSize()).orElse(Constant.INIT_PAGE_SIZE));
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
