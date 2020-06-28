package com.city.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author 17666
 */
@Configuration
@EnableWebSecurity()
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 授权
     *
     * @param http 请求
     * @throws Exception 异常
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll();
////                .anyRequest().permitAll()
////                .and().csrf().disable();
//
//    }

    /**
     * 验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new Pbkdf2PasswordEncoder());
    }

}
