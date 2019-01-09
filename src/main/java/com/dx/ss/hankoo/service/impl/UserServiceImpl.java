package com.dx.ss.hankoo.service.impl;

import com.dx.ss.hankoo.dal.beans.UserInfo;
import com.dx.ss.hankoo.dal.mapper.UserInfoMapper;
import com.dx.ss.hankoo.dal.model.SessionUser;
import com.dx.ss.hankoo.form.LoginForm;
import com.dx.ss.hankoo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public SessionUser login(LoginForm form) {
        UserInfo user = getUserByUsername(form.getUsername());
        if (user == null) return null;
        // 密码不匹配
        if (!form.getPassword().equals(user.getLoginPassword())) {
            return null;
        }
        return generateSessionUser(user);
    }

    @Override
    public List<UserInfo> getUserList(String username, Integer type, Integer status) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(username)) {
            criteria.andEqualTo("username", username);
        }
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (status != null) {
            criteria.andEqualTo("status", status);
        }
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public UserInfo getUserByUsername(String username) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 生成SessionUser，并缓存之
     *
     * @param userInfo 登录成功的用户信息
     * @return SessionUser
     */
    private SessionUser generateSessionUser(UserInfo userInfo) {
        String userId = userInfo.getUserId();
        String username = userInfo.getUsername();
        SessionUser sessionUser = new SessionUser(userId, username);
        sessionUser.setStatus(userInfo.getStatus());
        sessionUser.setType(userInfo.getType());
        sessionUser.setName(userInfo.getName());
        return sessionUser;
    }

}
