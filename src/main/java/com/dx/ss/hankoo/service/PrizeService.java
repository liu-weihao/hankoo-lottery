package com.dx.ss.hankoo.service;

import com.dx.ss.hankoo.dal.beans.Prize;
import com.dx.ss.hankoo.dal.model.PrizeRecordModel;
import com.dx.ss.hankoo.dal.search.biz.PrizeRecordSearch;

import java.util.List;

public interface PrizeService {

    List<Prize> getPrizeList();

    Prize getPrizeInfo(Integer prizeId);

    List<PrizeRecordModel> getPrizeRecordList(PrizeRecordSearch search);

    boolean receivePrize(Integer id, boolean hasReceived);

    List<Integer> draw(Integer prizeId);

    Integer redraw(Integer prizeId);

    int sunshine();
}
