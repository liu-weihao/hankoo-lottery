package com.dx.ss.hankoo.service.impl;

import com.dx.ss.hankoo.dal.beans.BlackParticipant;
import com.dx.ss.hankoo.dal.beans.Prize;
import com.dx.ss.hankoo.dal.mapper.BlackParticipantMapper;
import com.dx.ss.hankoo.dal.model.BlackParticipantModel;
import com.dx.ss.hankoo.dal.search.biz.BlackParticipantSearch;
import com.dx.ss.hankoo.service.BlackParticipantService;
import com.dx.ss.hankoo.service.PrizeService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlackParticipantServiceImpl implements BlackParticipantService {

    @Autowired
    private BlackParticipantMapper blackParticipantMapper;

    @Autowired
    private PrizeService prizeService;

    @Override
    public List<BlackParticipantModel> getBlackParticipants(BlackParticipantSearch search) {
        PageHelper.startPage(search.getPageNum(), search.getPageSize());
        return blackParticipantMapper.getBlackParticipants(search);
    }

    @Override
    public boolean removeBlackParticipantById(Integer id) {
        return id != null && blackParticipantMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public int removeBlackParticipant(Integer participantId) {
        if (participantId == null) return 0;
        Example ex = new Example(BlackParticipant.class);
        ex.createCriteria().andEqualTo("participantId", participantId);
        return blackParticipantMapper.deleteByExample(ex);
    }

    @Override
    public Map<String, Integer> getBlackParticipantStatistics() {
        Map<String, Integer> result = new HashMap<>();
        List<Prize> prizeList = prizeService.getPrizeList();
        List<BlackParticipant> blackParticipants = blackParticipantMapper.selectAll();
        Map<Integer, List<BlackParticipant>> prizeMap = blackParticipants.stream().collect(Collectors.groupingBy(BlackParticipant::getPrizeId));
        for (Prize prize : prizeList) {
            List<BlackParticipant> blackList = prizeMap.get(prize.getId());
            if (CollectionUtils.isEmpty(blackList)) {
                result.put(prize.getName(), 0);
            } else {
                result.put(prize.getName(), blackList.size());
            }
        }
        return result;
    }
}
