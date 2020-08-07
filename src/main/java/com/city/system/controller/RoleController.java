package com.city.system.controller;


import com.city.common.response.Result;
import com.city.system.pojo.query.RoleQuery;
import com.city.system.pojo.query.UserQuery;
import com.city.system.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping("getRoleList")
    @ApiOperation(value = "获取角色信息列表", notes = "角色列表", httpMethod = "GET")
    public Result getRoleList(RoleQuery roleQuery) {
        roleQuery.setPageNum(1);
        roleQuery.setPageSize(10);
        return iRoleService.getRoleList(roleQuery);
    }

}

