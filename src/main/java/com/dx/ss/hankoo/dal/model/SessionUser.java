package com.dx.ss.hankoo.dal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * 登录用户信息
 *
 * @author Eric Lau
 * @Date 2017/9/6.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionUser implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 登录成功后，系统生成的授权码，用以获取Token
     */
    private String grantCode;

    /**
     * 最近一次登录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    /**
     * 用户状态，1：正常，0：被禁用
     */
    private Integer status;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 系统颁发的token，使用授权码获取
     */
    private String token;

    public SessionUser() {
        this.loginTime = new Date();
    }

    public SessionUser(String userId) {
        this();
        this.userId = userId;
    }

    public SessionUser(String userId, String username) {
        this(userId);
        this.username = username;
    }
}
