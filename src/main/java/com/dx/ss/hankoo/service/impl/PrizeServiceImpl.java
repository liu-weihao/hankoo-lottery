package com.dx.ss.hankoo.service.impl;

import com.dx.ss.hankoo.dal.beans.Prize;
import com.dx.ss.hankoo.dal.mapper.PrizeMapper;
import com.dx.ss.hankoo.service.PrizeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    @Override
    public List<Prize> getPrizeList() {
        PageHelper.startPage(1, 100, "id ASC");
        return prizeMapper.selectAll();
    }
}
