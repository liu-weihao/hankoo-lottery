package com.dx.ss.hankoo.controller;

import com.dx.ss.hankoo.common.pager.BasePager;
import com.dx.ss.hankoo.common.pager.IPagerFactory;
import com.dx.ss.hankoo.dal.beans.Prize;
import com.dx.ss.hankoo.service.PrizeService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hankoo/lottery")
public class LotteryController {

    @Autowired
    private IPagerFactory pagerFactory;

    @Autowired
    private PrizeService prizeService;

    @GetMapping(value = "/prizes.web")
    @ResponseBody
    public BasePager<Prize> getPrizeList() {
        List<Prize> participants = prizeService.getPrizeList();
        return pagerFactory.generatePager((Page<Prize>) participants);
    }
}
