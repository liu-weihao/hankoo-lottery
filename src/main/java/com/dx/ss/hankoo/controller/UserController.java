package com.dx.ss.hankoo.controller;

import com.dx.ss.data.enums.WorkerTypeEnums;
import com.dx.ss.data.holder.ExcelDocumentDataHolder;
import com.dx.ss.data.main.DocumentParseDelegate;
import com.dx.ss.data.parse.ExcelDocumentParser;
import com.dx.ss.hankoo.common.constants.ViewConstants;
import com.dx.ss.hankoo.common.pager.BasePager;
import com.dx.ss.hankoo.common.pager.IPagerFactory;
import com.dx.ss.hankoo.dal.beans.BlackParticipant;
import com.dx.ss.hankoo.dal.beans.Participant;
import com.dx.ss.hankoo.dal.enums.StatusCode;
import com.dx.ss.hankoo.dal.model.BlackParticipantModel;
import com.dx.ss.hankoo.dal.model.ParticipantModel;
import com.dx.ss.hankoo.dal.model.ResponseObj;
import com.dx.ss.hankoo.dal.model.SessionUser;
import com.dx.ss.hankoo.dal.search.biz.BlackParticipantSearch;
import com.dx.ss.hankoo.dal.search.biz.ParticipantSearch;
import com.dx.ss.hankoo.form.LoginForm;
import com.dx.ss.hankoo.service.BizExcelDocumentDataImportResolver;
import com.dx.ss.hankoo.service.BlackParticipantService;
import com.dx.ss.hankoo.service.ParticipantService;
import com.dx.ss.hankoo.service.UserService;
import com.github.pagehelper.Page;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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

    @Autowired
    private BlackParticipantService blackParticipantService;


    @PostMapping(value = "/login.do")
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

    @GetMapping(value = "/participants.do")
    @ResponseBody
    public ResponseObj getParticipants() {
        return ResponseObj.success(participantService.getParticipants());
    }

    @GetMapping(value = "/participants.web")
    @ResponseBody
    public BasePager<Participant> getParticipants(ParticipantSearch search) {
        List<Participant> participants = participantService.getParticipants(search);
        return pagerFactory.generatePager((Page<Participant>) participants);
    }

    @GetMapping(value = "/participants/statistics.web")
    @ResponseBody
    public ResponseObj getParticipantStatistics() {
        return ResponseObj.success(participantService.getParticipantStatistics());
    }

    @DeleteMapping(value = "/participants.web")
    @ResponseBody
    public ResponseObj removeParticipant(@RequestParam Integer id) {
        if (participantService.removeParticipant(id)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }

    @GetMapping(value = "/participants/black.web")
    @ResponseBody
    public BasePager<BlackParticipantModel> getBlackParticipants(BlackParticipantSearch search) {
        List<BlackParticipantModel> participants = blackParticipantService.getBlackParticipants(search);
        return pagerFactory.generatePager((Page<BlackParticipantModel>) participants);
    }

    @GetMapping(value = "/participants/black/statistics.web")
    @ResponseBody
    public ResponseObj getBlackParticipantStatistics() {
        return ResponseObj.success(blackParticipantService.getBlackParticipantStatistics());
    }

    @DeleteMapping(value = "/participants/black.web")
    @ResponseBody
    public ResponseObj removeBlackParticipants(@RequestParam Integer id) {
        if (blackParticipantService.removeBlackParticipantById(id)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }

    @Transactional
    @PostMapping(value = "/participants/import.do")
    @ResponseBody
    public ResponseObj importParticipants(HttpServletRequest request, @RequestParam(name = "file") MultipartFile file, boolean overwrite) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("/") + "file" + File.separator + "data.xlsx";
        File dest = new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        if (overwrite) {
            blackParticipantService.empty();
            participantService.empty();
        }
        IOUtils.write(file.getBytes(), new FileOutputStream(dest));
        DocumentParseDelegate delegate = new DocumentParseDelegate.Builder(WorkerTypeEnums.IMPORT_WORKER)
                .importResolver(new BizExcelDocumentDataImportResolver())
                .parser(ExcelDocumentParser.getParserInstance())
                .locations(new String[]{ path })
                .build();
        List<?> resultList = delegate.parse();
        ExcelDocumentDataHolder holder = (ExcelDocumentDataHolder) resultList.get(0);
        List<ParticipantModel> dataList = (List<ParticipantModel>) holder.getDataList();
        List<Participant> participants = new ArrayList<>(dataList.size());
        List<BlackParticipant> blackParticipants = new ArrayList<>();
        for (ParticipantModel p : dataList) {
            Participant participant = new Participant();
            participant.setName(p.getName());
            participant.setInfo(p.getInfo());
            participant.setIsWinner(Boolean.FALSE);
            participants.add(participant);
            participantService.addParticipant(participant);
            Integer participantId = participant.getId();
            if ("Y".equalsIgnoreCase(p.getSurprise())) {
                BlackParticipant blackParticipant = new BlackParticipant();
                blackParticipant.setPrizeId(0);
                blackParticipant.setParticipantId(participantId);
                blackParticipants.add(blackParticipant);
            }
            if ("Y".equalsIgnoreCase(p.getFirst())) {
                BlackParticipant blackParticipant = new BlackParticipant();
                blackParticipant.setPrizeId(1);
                blackParticipant.setParticipantId(participantId);
                blackParticipants.add(blackParticipant);
            }
            if ("Y".equalsIgnoreCase(p.getSecond())) {
                BlackParticipant blackParticipant = new BlackParticipant();
                blackParticipant.setPrizeId(2);
                blackParticipant.setParticipantId(participantId);
                blackParticipants.add(blackParticipant);
            }
            if ("Y".equalsIgnoreCase(p.getThird())) {
                BlackParticipant blackParticipant = new BlackParticipant();
                blackParticipant.setPrizeId(3);
                blackParticipant.setParticipantId(participantId);
                blackParticipants.add(blackParticipant);
            }
            if ("Y".equalsIgnoreCase(p.getForth())) {
                BlackParticipant blackParticipant = new BlackParticipant();
                blackParticipant.setPrizeId(4);
                blackParticipant.setParticipantId(participantId);
                blackParticipants.add(blackParticipant);
            }
        }
        blackParticipantService.addBlackParticipants(blackParticipants);
        return ResponseObj.success(participants.size());
    }
}
