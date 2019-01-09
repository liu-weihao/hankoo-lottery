package com.dx.ss.hankoo.common.interceptor.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Map;

@Slf4j
public abstract class ExceptionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            doException(request, response, handler, ex);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    public void doException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getServletPath();
        if (request.getMethod().equals("GET") && !StringUtils.isBlank(request.getQueryString())) {
            url += "?";
            url += request.getQueryString();
            log.error("URL:  " + url);    //打印请求路径
        } else {    //不是get请求，输出参数
            log.error("URL:  " + url);    //打印请求路径
            Map<String, String[]> parameterMap = request.getParameterMap();
            try {
                ObjectMapper m = new ObjectMapper();
                m.setDateFormat(DateFormat.getDateTimeInstance());
                log.error(m.writeValueAsString(parameterMap));
            } catch (JsonProcessingException e) {
                log.error("JSON序列化异常,{}", e);
            }
        }
        log.error(ex.getMessage());    //打印错误堆栈
    }
}
