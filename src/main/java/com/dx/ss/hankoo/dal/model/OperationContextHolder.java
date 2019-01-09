package com.dx.ss.hankoo.dal.model;

import java.util.Date;


/**
 * 会话上下文环境
 *
 * @author 卡内齐 (kaneiqi@dajiaok.com)
 * @version $Id: OperationContextHolder.java, v 0.1 2014-6-24 下午03:44:20 kaneiqi
 * Exp $
 */
public class OperationContextHolder {

    public static void setIsLoggerUser(SessionUser sessionUser) {

        ContextHolder.setConcurrentObject(sessionUser);
    }

    /**
     * 获取当前会话
     *
     * @return
     */
    public static SessionUser getSessionUser() {

        return (SessionUser) ContextHolder.getConcurrentObject();
    }

    /**
     * 获取上下文登录userId
     *
     * @return
     */
    public static String getUserId() {

        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getUserId() : "";
    }

    /**
     * 获取上下文登录loginId
     *
     * @return
     */
    public static String getUserName() {

        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getUsername() : "";
    }

    /**
     * 登录时间
     *
     * @return
     */
    public static Date getLoginTime() {

        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getLoginTime() : null;

    }

    /**
     * 用户类型
     *
     * @return
     */
    public static Integer getType() {
        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getType() : 0;
    }

    /**
     * 清除当前会话
     */
    public static void clearUser() {

        ContextHolder.clearContext();
    }
}
