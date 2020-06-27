package com.city.framework.security;

import com.city.common.utils.StringUtils;
import com.city.system.entity.User;
import com.city.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 自定义用户验证
 *
 * @author 17666
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByAccount(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
//        else if (UserStatus.DISABLE.getCode().equals(user.getEnabled())) {
//            log.info("登录用户：{} 已被停用.", username);
//            throw new Exception("对不起，您的账号：" + username + " 已停用");
//        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user) {
        return new LoginUser(user);
    }
}
