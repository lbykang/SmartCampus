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
import java.util.Optional;

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
        BeanUtils.copyProperties(roleVo, role);
        int code = roleMapper.insert(role) > 0 ? 200 : -1;
        return ResponseFactory.build(code, 1, "操作成功", role);
    }

    @Override
    public Result deleteRole(Long id) {
        return null;
    }

    @Override
    public Result deleteRoleBatch(Long[] ids) {
        return null;
    }

    @Override
    public Result updateRole(RoleVo roleVo) {
        return null;
    }

    @Override
    public RoleDto getRole(Long id) {
        return null;
    }

    @Override
    public Result getRoleList(RoleQuery roleQuery) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("id", "name", "description", "sort", "is_enabled as enabled", "gmt_create");
        wrapper.orderByDesc("gmt_create");
        Page<Role> page = new Page<>(Optional.ofNullable(roleQuery.getPageNum()).orElse(1),
                Optional.ofNullable(roleQuery.getPageSize()).orElse(10));
        IPage<Role> rolePage = roleMapper.selectPage(page, wrapper);
        return ResponseFactory.build(Optional.ofNullable(rolePage).orElse(new Page<>()));
    }
}
