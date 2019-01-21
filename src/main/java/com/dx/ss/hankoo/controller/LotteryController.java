package com.dx.ss.hankoo.controller;

import com.dx.ss.hankoo.common.pager.BasePager;
import com.dx.ss.hankoo.common.pager.IPagerFactory;
import com.dx.ss.hankoo.dal.beans.Prize;
import com.dx.ss.hankoo.dal.enums.StatusCode;
import com.dx.ss.hankoo.dal.model.PrizeRecordModel;
import com.dx.ss.hankoo.dal.model.ResponseObj;
import com.dx.ss.hankoo.dal.search.biz.PrizeRecordSearch;
import com.dx.ss.hankoo.service.PrizeService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/prize.do")
    @ResponseBody
    public ResponseObj getPrizeInfo(Integer prizeId) {
        Prize prize = prizeService.getPrizeInfo(prizeId);
        if (prize == null) {
            return ResponseObj.fail(StatusCode.BIZ_FAILED, "奖项不存在");
        }
        return ResponseObj.success(prize);
    }

    @GetMapping(value = "/prize/records.web")
    @ResponseBody
    public BasePager<PrizeRecordModel> getPrizeRecordList(PrizeRecordSearch search) {
        List<PrizeRecordModel> participants = prizeService.getPrizeRecordList(search);
        return pagerFactory.generatePager((Page<PrizeRecordModel>) participants);
    }

    @PostMapping(value = "/prize/records/receive.web")
    @ResponseBody
    public ResponseObj receivePrize(Integer id, boolean hasReceived) {
        if (prizeService.receivePrize(id, hasReceived)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }

    @PostMapping(value = "/draw.do")
    @ResponseBody
    public ResponseObj draw(@RequestParam(name = "prizeId") Integer prizeId) {
        return ResponseObj.success(prizeService.draw(prizeId));
    }

    @PostMapping(value = "/redraw.do")
    @ResponseBody
    public ResponseObj redraw(@RequestParam(name = "prizeId") Integer prizeId) {
        return ResponseObj.success(prizeService.redraw(prizeId));
    }
}
