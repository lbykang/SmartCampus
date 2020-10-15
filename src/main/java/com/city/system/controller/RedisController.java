package com.city.system.controller;

import com.city.common.utils.RedisUtils;
import com.city.system.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: mirror6
 * @Date: 2020/10/15 15:51
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Resource
    private RedisUtils redisUtil;

    @RequestMapping("set")
    public void redisSet(String key, String value) {
        User user = new User();
        user.setAccount("jkl");
        redisUtil.set("user", user);
    }

    @RequestMapping("get")
    public Object redisGet(String key) {
        return redisUtil.get(key);
    }

}
