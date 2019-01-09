package com.dx.ss.hankoo.dal.enums;

import lombok.Getter;

@Getter
public enum UserType {
    /**
     * 超级管理员
     */
    SUPER_ADMIN(0, "超级管理员"),
    /**
     * 后端用户
     */
    USER_WEB(10, "后端用户"),
    /**
     * 长租司机
     */
    LONG_DRIVER(20, "后台导入司机"),
    /**
     * 替班司机
     */
    RELAY_DRIVER(30, "替班司机"),
    /**
     * 普通司机
     */
    COMMON_DRIVER(40, "app注册司机"),

    CONTRACT_DRIVER(50, "签约司机"),;

    private Integer code;

    private String name;

    public static UserType getEnumsByCode(Integer code) {
        if (code != null) {
            for (UserType enums : UserType.values()) {
                if (code.equals(enums.getCode())) {
                    return enums;
                }
            }
        }
        return null;
    }

    public static UserType getEnumByType(Integer type) {

        if (null != type) {
            for (UserType userType : UserType.values()) {
                if (type.equals(userType.getCode())) {

                    return userType;
                }
            }
        }
        return null;
    }

    /**
     * 判断是否app用户
     *
     * @return true 是
     */
    public boolean isAppUser() {
        switch (this) {
            case LONG_DRIVER:
            case RELAY_DRIVER:
            case COMMON_DRIVER:
                return true;
            default:
                return false;
        }
    }

    public Boolean isWebUser() {
        switch (this) {
            case SUPER_ADMIN:
            case USER_WEB:
                return true;
            default:
                return false;
        }
    }

    UserType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
