package com.city.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.city.common.response.ResponseFactory;
import com.city.common.response.Result;
import com.city.system.pojo.dto.RoleDto;
import com.city.system.pojo.entity.Role;
import com.city.system.mapper.RoleMapper;
import com.city.system.pojo.entity.User;
import com.city.system.pojo.query.RoleQuery;
import com.city.system.pojo.vo.RoleVo;
import com.city.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result addRole(RoleVo roleVo) {
        Role role = new Role();
        role.setCreateUserId(1L);
        BeanUtils.copyProperties(roleVo, role);
        int code = roleMapper.insert(role) > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", role);
    }

    @Override
    public Result deleteRole(Long id) {
        int code = roleMapper.deleteById(id) > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", null);
    }

    @Override
    public Result deleteRoleBatch(Long[] ids) {
        int code = roleMapper.deleteBatchIds(Arrays.asList(ids)) > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", null);
    }

    @Override
    public Result updateRole(RoleVo roleVo) {
        //角色-权限 管理
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        int count = roleMapper.updateById(role);
        int code = count > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", role);
    }

    @Override
    public RoleDto getRole(Long id) {
        return null;
    }

    @Override
    public Result getRoleList(RoleQuery roleQuery) {
        List<Role> roles = roleMapper.selectList(null);
        List<Map> res = new ArrayList<>();
        for (Role role : roles) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", role.getName());
            map.put("key", role.getId());
            map.put("value", role.getId());
            map.put("children", null);
            res.add(map);
        }
        return ResponseFactory.build(res);
    }
}
