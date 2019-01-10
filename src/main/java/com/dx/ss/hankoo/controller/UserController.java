package com.dx.ss.hankoo.controller;

import com.dx.ss.hankoo.common.constants.ViewConstants;
import com.dx.ss.hankoo.common.pager.BasePager;
import com.dx.ss.hankoo.common.pager.IPagerFactory;
import com.dx.ss.hankoo.dal.beans.Participant;
import com.dx.ss.hankoo.dal.enums.StatusCode;
import com.dx.ss.hankoo.dal.model.ResponseObj;
import com.dx.ss.hankoo.dal.model.SessionUser;
import com.dx.ss.hankoo.dal.search.biz.ParticipantSearch;
import com.dx.ss.hankoo.form.LoginForm;
import com.dx.ss.hankoo.service.ParticipantService;
import com.dx.ss.hankoo.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hankoo/user")
public class UserController extends BaseController {


    @Autowired
    private IPagerFactory pagerFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipantService participantService;


    @PostMapping(value = "/i/login.do")
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

    @PostMapping(value = "/logout.do")
    @ResponseBody
    public ResponseObj logout(HttpServletRequest request) {
        destory(request);
        return ResponseObj.success();
    }

    @GetMapping(value = "/participants.web")
    @ResponseBody
    public BasePager<Participant> getParticipants(ParticipantSearch search) {
        List<Participant> participants = participantService.getParticipants(search);
        return pagerFactory.generatePager((Page<Participant>) participants);
    }

    @DeleteMapping(value = "/participants.web")
    @ResponseBody
    public ResponseObj removeParticipant(@RequestParam Integer id) {
        if (participantService.removeParticipant(id)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }
}
