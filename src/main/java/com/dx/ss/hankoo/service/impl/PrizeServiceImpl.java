package com.dx.ss.hankoo.service.impl;

import com.dx.ss.hankoo.dal.beans.Prize;
import com.dx.ss.hankoo.dal.beans.PrizeRecord;
import com.dx.ss.hankoo.dal.mapper.PrizeMapper;
import com.dx.ss.hankoo.dal.mapper.PrizeRecordMapper;
import com.dx.ss.hankoo.dal.model.PrizeRecordModel;
import com.dx.ss.hankoo.dal.search.biz.PrizeRecordSearch;
import com.dx.ss.hankoo.service.PrizeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    @Autowired
    private PrizeRecordMapper recordMapper;

    @Override
    public List<Prize> getPrizeList() {
        PageHelper.startPage(1, 100, "id ASC");
        return prizeMapper.selectAll();
    }

    @Override
    public List<PrizeRecordModel> getPrizeRecordList(PrizeRecordSearch search) {
        PageHelper.startPage(search.getPageNum(), search.getPageSize());
        return recordMapper.getPrizeRecordList(search);
    }

    @Override
    public boolean receivePrize(Integer id, boolean hasReceived) {
        PrizeRecord record = recordMapper.selectByPrimaryKey(id);
        if (record == null) return false;
        record.setHasReceived(hasReceived);
        return recordMapper.updateByPrimaryKeySelective(record) == 1;
    }

}
