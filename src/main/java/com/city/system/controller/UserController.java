package com.city.system.controller;


import com.city.system.entity.User;
import com.city.system.service.IUserService;
import com.city.common.util.ResponseFactory;
import com.city.common.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private IUserService iUserService;


    @PostMapping("addUser")
    @ApiOperation(value = "添加用户信息", notes = "添加用户", httpMethod = "POST")
    public Result addUser(User user) {
        return ResponseFactory.build(iUserService.save(user));
    }

}

