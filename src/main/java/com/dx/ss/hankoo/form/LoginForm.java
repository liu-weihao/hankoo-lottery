package com.dx.ss.hankoo.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginForm {

    @NotBlank(message = "请输入用户名")
    private String username;

    @NotBlank(message = "请输入登录密码")
    private String password;
}
