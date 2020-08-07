package com.city.system.controller;


import com.alibaba.fastjson.JSON;
import com.city.common.response.ResponseFactory;
import com.city.common.response.Result;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.query.UserQuery;
import com.city.system.pojo.vo.UserVo;
import com.city.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private IUserService iUserService;


    @PostMapping("addUser")
    @ApiOperation(value = "添加用户信息", notes = "添加用户", httpMethod = "POST")
    public Result addUser(@RequestBody UserVo userVo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return iUserService.addUser(userVo);
    }

    @DeleteMapping("deleteUser")
    @ApiOperation(value = "删除用户信息", notes = "逻辑删除", httpMethod = "DELETE")
    public Result deleteUser(@RequestBody String object) {
        List<Long> array = JSON.parseArray(object, Long.class);
        return iUserService.deleteUserBatch(array.toArray(new Long[0]));
    }

    @PutMapping("updateUser")
    @ApiOperation(value = "修改用户信息", notes = "编辑用户", httpMethod = "PUT")
    public Result updateUser(@RequestBody UserVo userVo) {
        return iUserService.updateUser(userVo);
    }

    @PutMapping("updateStatus")
    @ApiOperation(value = "修改用户状态", notes = "编辑状态", httpMethod = "PUT")
    public Result updateStatus(@RequestBody UserVo userVo) {
        return iUserService.updateUserStatus(userVo);
    }

    @PutMapping("updatePassword")
    @ApiOperation(value = "修改用户密码", notes = "编辑密码", httpMethod = "PUT")
    public Result updatePassword(UserDto userDto) {
        return iUserService.updatePassword(userDto);
    }

    @GetMapping("getUser/{id}")
    @ApiOperation(value = "获取用户信息", notes = "用户详情", httpMethod = "GET")
    public Result getUser(@PathVariable Long id) {
        return ResponseFactory.build(iUserService.getUser(id));
    }

    @GetMapping("getUserList")
    @ApiOperation(value = "获取用户信息列表", notes = "用户列表", httpMethod = "GET")
    public Result getUserList(UserQuery userQuery) {
        userQuery.setPageNum(1);
        userQuery.setPageSize(10);
        return iUserService.getUserList(userQuery);
    }

}

