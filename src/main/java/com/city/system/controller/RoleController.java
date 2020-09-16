package com.city.system.controller;


import com.city.common.response.Result;
import com.city.system.pojo.query.RoleQuery;
import com.city.system.pojo.query.UserQuery;
import com.city.system.pojo.vo.RoleVo;
import com.city.system.pojo.vo.UserVo;
import com.city.system.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private IRoleService iRoleService;

    @PostMapping("addRole")
    @ApiOperation(value = "添加角色信息", notes = "添加角色", httpMethod = "POST")
    public Result addRole(@RequestBody RoleVo roleVo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return iRoleService.addRole(roleVo);
    }

    @GetMapping("getRoleList")
    @ApiOperation(value = "获取角色信息列表", notes = "添加用户时使用", httpMethod = "GET")
    public Result getRoleList(RoleQuery roleQuery) {
        roleQuery.setPageNum(1);
        roleQuery.setPageSize(10);
        return iRoleService.getRoleList(roleQuery);
    }

}

