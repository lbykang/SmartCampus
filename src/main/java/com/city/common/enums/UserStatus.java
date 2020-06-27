package com.city.common.enums;

/**
 * 用户状态
 *
 * @author 17666
 */

public enum UserStatus {

    /**
     * ENABLED 启用
     * DISABLE 禁用
     * DELETED 删除
     */
    ENABLED("0", "启用"),
    DISABLE("1", "禁用"),
    DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
