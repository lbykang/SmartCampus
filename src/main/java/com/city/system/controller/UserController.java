package com.city.system.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.city.system.entity.User;
import com.city.system.pojo.dto.UserDto;
import com.city.system.pojo.query.UserQuery;
import com.city.system.service.IUserService;
import com.city.common.response.ResponseFactory;
import com.city.common.response.Result;
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
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private IUserService iUserService;


    @PostMapping("addUser")
    @ApiOperation(value = "添加用户信息", notes = "添加用户", httpMethod = "POST")
    public Result addUser(@RequestBody String object) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String data = JSON.parseObject(object).getString("data");
        UserDto userDto = JSON.parseObject(data, UserDto.class);
        return iUserService.addUser(userDto);
    }

    @DeleteMapping("deleteUser/{id}")
    @ApiOperation(value = "删除用户信息", notes = "逻辑删除", httpMethod = "DELETE")
    public Result deleteUser(@PathVariable Long id) {
        return iUserService.deleteUser(id);
    }


    @DeleteMapping("deleteUserBatch")
    @ApiOperation(value = "批量删除用户信息", notes = "逻辑删除", httpMethod = "DELETE")
    public Result deleteUserBatch(@PathVariable Long[] ids) {
        return iUserService.deleteUserBatch(ids);
    }


    @PutMapping("updateUser")
    @ApiOperation(value = "修改用户信息", notes = "编辑用户", httpMethod = "PUT")
    public Result updateUser(UserDto userDto) {
        return iUserService.updateUser(userDto);
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
        return iUserService.getUserList(userQuery);
    }

}

