package com.dx.ss.hankoo.common.helper;

import com.dx.ss.hankoo.common.constants.ViewConstants;
import com.dx.ss.hankoo.dal.enums.HttpChannelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TicketHelper {


    /**
    * 功能：更新cookie时间
    */
    public void setCookie(HttpServletRequest request, HttpServletResponse response, String ticket) {
        // 信任保存
        Cookie cookie = new Cookie(ViewConstants.LOGIN_TICKET_KEY, ticket);
        // 要使此处有效 则访问地址必须与此处的域名一致
        cookie.setDomain(request.getServerName());
        // 设置父path
        cookie.setPath("/");
        // 设置有效期存放至客户端硬盘否则为浏览器内存
        cookie.setMaxAge(ViewConstants.TRUST_COOKIE_TIME); //存活期为秒 
        response.addCookie(cookie);
    }

    /**
    * 功能：三种方式归纳取原文ticket
    */
    public String getTicket(HttpServletRequest request, HttpChannelType httpChannelType) {

        Cookie cookies[] = request.getCookies();

        String ticket = null;

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(ViewConstants.LOGIN_TICKET_KEY, cookie.getName())) {

                    ticket = StringUtils.trim(cookie.getValue());

                    String builder = httpChannelType.name() + "：获取到cookie的ticket=" + ticket +
                            "：客户端请求地址=" + request.getServerName() + "====" +
                            cookie.getDomain();
                    log.info(builder);

                    break;
                }
            }
        }

        // java后台设置的cookie存在则header部分肯定也存在即可获取,
        // 测试非后台塞入cookie的取值 如：跨域情况下、安卓情况
        if (StringUtils.isBlank(ticket)) {
            ticket = customHeadTicket(request, httpChannelType);
        }

        // 通过get参数传入ticket
        if (StringUtils.isBlank(ticket)) {
            ticket = StringUtils.trim(request.getParameter(ViewConstants.LOGIN_TICKET_KEY));
        }
        return ticket;
    }

    /**
    * 功能：清楚取到的cookie
    */
    public void deleteCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
    * 功能：自定义的header ticket
    */
    private String customHeadTicket(HttpServletRequest request, HttpChannelType httpChannelType) {

        String ticket = request.getHeader(ViewConstants.LOGIN_TICKET_KEY);

        return StringUtils.trim(ticket);
    }

}
