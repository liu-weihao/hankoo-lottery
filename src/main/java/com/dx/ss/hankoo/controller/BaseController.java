package com.dx.ss.hankoo.controller;

import com.dx.ss.hankoo.common.constants.ViewConstants;
import com.dx.ss.hankoo.dal.enums.StatusCode;
import com.dx.ss.hankoo.dal.model.ResponseObj;
import com.dx.ss.hankoo.dal.model.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * 公共Controller，所有Controller继承于此
 */
@Slf4j
public class BaseController {

    /**
     * 功能：获取登录用户id
     */
    public String getUserId(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getUserId();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 功能：获取username
     */
    public String getUsername(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getUsername();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 功能：获取用户上次登录时间
     */
    public Date getLoginTime(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getLoginTime();
        }
        return null;
    }

    /**
     * 功能：获取登录用户类型。
     */
    public Integer getUserType(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getType();
        }
        return null;
    }

    public void destory(HttpServletRequest request){
        request.getSession().setAttribute(ViewConstants.LOGIN_TICKET_USER, null);
    }

    private SessionUser getSessionUser(HttpServletRequest request){
        Object obj = request.getSession().getAttribute(ViewConstants.LOGIN_TICKET_USER);
        if(obj != null){	//已登录
            return (SessionUser) obj;
        }
        return null;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseObj exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        log.error("error: " + request.getRequestURI());
        log.error("捕获到异常：", e);
        return ResponseObj.fail(StatusCode.SYS_ERROR, StatusCode.SYS_ERROR.getDetail(), new HashMap<String, Object>(2){{
            put("error", e.toString());
            put("params", request.getParameterMap());
        }});
    }
}
