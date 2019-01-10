package com.dx.ss.hankoo.service.impl;

import com.dx.ss.hankoo.dal.beans.Participant;
import com.dx.ss.hankoo.dal.mapper.ParticipantMapper;
import com.dx.ss.hankoo.dal.model.ParticipantStatisticsModel;
import com.dx.ss.hankoo.dal.search.biz.ParticipantSearch;
import com.dx.ss.hankoo.service.BlackParticipantService;
import com.dx.ss.hankoo.service.ParticipantService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantMapper participantMapper;

    @Autowired
    private BlackParticipantService blackParticipantService;

    @Override
    public List<Participant> getParticipants(ParticipantSearch search) {
        Example ex = new Example(Participant.class);
        Example.Criteria criteria = ex.createCriteria();
        if (StringUtils.isNotBlank(search.getName())) {
            criteria.andEqualTo("name", search.getName());
        }
        if (search.getIsWinner() != null) {
            criteria.andEqualTo("isWinner", search.getIsWinner());
        }
        PageHelper.startPage(search.getPageNum(), search.getPageSize());
        return participantMapper.selectByExample(ex);
    }

    @Override
    @Transactional
    public boolean removeParticipant(Integer id) {
        blackParticipantService.removeBlackParticipant(id);
        return id != null && participantMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public ParticipantStatisticsModel getParticipantStatistics() {
        ParticipantStatisticsModel statistics = new ParticipantStatisticsModel();
        List<Participant> participants = participantMapper.selectAll();
        statistics.setTotal(participants.size());
        statistics.setWinnerCount((int) participants.stream().filter(Participant::getIsWinner).count());
        return statistics;
    }
}
