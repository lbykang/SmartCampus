package com.city.common.utils;

import com.city.framework.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @author 17666
 */
public class SecurityUtils {

    /**
     * 获取用户账户
     **/
    public static String getUsername() throws Exception {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new Exception("获取用户账户异常");
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() throws Exception {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new Exception("获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Pbkdf2PasswordEncoder
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        return pbkdf2PasswordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        return pbkdf2PasswordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
