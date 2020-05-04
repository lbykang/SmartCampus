package com.city.system.controller;


import com.city.system.entity.User;
import com.city.system.service.IUserService;
import com.city.util.ResponseFactory;
import com.city.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
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



    @ApiOperation(value = "添加信息", notes = "添加", httpMethod = "POST")
    @GetMapping("addUser")
    public Result addUser() {
        User user = new User("1","1","1","1",1L,"1","1");
        user.setAccount("123");
        user.setCreator(1L);
        
        return ResponseFactory.build(iUserService.save(user));
    }

}

