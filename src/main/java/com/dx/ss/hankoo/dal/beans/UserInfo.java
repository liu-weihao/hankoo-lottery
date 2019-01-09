package com.dx.ss.hankoo.dal.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名，登陆账号，也作为联系方式
     */
    private String username;

    private String nickname;

    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    @Column(name = "login_password")
    @JsonIgnore
    private String loginPassword;

    /**
     * 用户来源：10-导入，20-APP注册，30-后台添加
     */
    @Column(name = "user_from")
    private Integer userFrom;

    /**
     * 用户类型:0-（super_admin）超级管理员，10（USER_WEB）-后端用户，20-签约长租司机，30-替班司机，40-普通司机
     */
    private Integer type;

    /**
     * 账号状态:10-正常，20-冻结，30-拉黑
     */
    private Integer status;

    @Column(name = "gmt_create")
    private Date gmtCreate;

}