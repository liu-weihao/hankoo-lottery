package com.dx.ss.hankoo.service;


import com.dx.ss.hankoo.dal.beans.UserInfo;
import com.dx.ss.hankoo.dal.model.SessionUser;
import com.dx.ss.hankoo.form.LoginForm;

import java.util.List;

public interface UserService {

    SessionUser login(LoginForm form);

    List<UserInfo> getUserList(String username, Integer type, Integer status);

    UserInfo getUserByUsername(String username);
}
