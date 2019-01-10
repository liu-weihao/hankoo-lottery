package com.dx.ss.hankoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

    @RequestMapping(value = "/index")
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/welcome")
    public String welcome() {
        return "/welcome";
    }

    @RequestMapping(value = "/toParticipants.web")
    public String toParticipants() {
        return "/user/participant_list";
    }

    @RequestMapping(value = "/toBlackParticipants.web")
    public String toBlackParticipants() {
        return "/user/black_participant_list";
    }

    @RequestMapping(value = "/toPrizeList.web")
    public String toPrizeList() {
        return "/lottery/prize_list";
    }

    @RequestMapping(value = "/toPrizeRecord.web")
    public String toPrizeRecord() {
        return "/lottery/prize_record";
    }
}
