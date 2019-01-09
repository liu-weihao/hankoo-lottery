package com.dx.ss.hankoo.controller;

import com.dx.ss.hankoo.common.constants.ViewConstants;
import com.dx.ss.hankoo.common.pager.IPagerFactory;
import com.dx.ss.hankoo.dal.enums.StatusCode;
import com.dx.ss.hankoo.dal.model.ResponseObj;
import com.dx.ss.hankoo.dal.model.SessionUser;
import com.dx.ss.hankoo.form.LoginForm;
import com.dx.ss.hankoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/hankoo/user")
public class UserController extends BaseController {


    @Autowired
    private IPagerFactory pagerFactory;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/i/login.do", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj doLogin(HttpServletRequest request, @Valid LoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        SessionUser user = userService.login(form);
        if (user == null) {
            return ResponseObj.fail(StatusCode.BIZ_FAILED, "登录失败，请核对用户名和密码");
        }
        request.getSession().setAttribute(ViewConstants.LOGIN_TICKET_USER, user);
        return ResponseObj.success(user);

    }

    @RequestMapping(value = "/logout.do", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj logout(HttpServletRequest request) {
        destory(request);
        return ResponseObj.success();
    }
}
