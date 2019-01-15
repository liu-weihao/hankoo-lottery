package com.dx.ss.hankoo.service.impl;

import com.dx.ss.hankoo.dal.beans.BlackParticipant;
import com.dx.ss.hankoo.dal.beans.Participant;
import com.dx.ss.hankoo.dal.beans.Prize;
import com.dx.ss.hankoo.dal.beans.PrizeRecord;
import com.dx.ss.hankoo.dal.mapper.PrizeMapper;
import com.dx.ss.hankoo.dal.mapper.PrizeRecordMapper;
import com.dx.ss.hankoo.dal.model.PrizeRecordModel;
import com.dx.ss.hankoo.dal.search.biz.PrizeRecordSearch;
import com.dx.ss.hankoo.service.BlackParticipantService;
import com.dx.ss.hankoo.service.ParticipantService;
import com.dx.ss.hankoo.service.PrizeService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    @Autowired
    private PrizeRecordMapper recordMapper;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private BlackParticipantService blackParticipantService;

    @Override
    public List<Prize> getPrizeList() {
        PageHelper.startPage(1, 100, "id ASC");
        return prizeMapper.selectAll();
    }

    @Override
    public Prize getPrizeInfo(Integer prizeId) {
        return prizeMapper.selectByPrimaryKey(prizeId);
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

    @Transactional
    @Override
    public List<Integer> draw(Integer prizeId) {
        //黑名单参与者
        List<Integer> blackParticipantIds = blackParticipantService.getBlackParticipants(prizeId).stream().map(BlackParticipant::getParticipantId).collect(Collectors.toList());
        //尚未未中奖，且未在黑名单中的参与者
        List<Participant> participants = participantService.getParticipants().stream().filter(participant -> !participant.getIsWinner() && !blackParticipantIds.contains(participant.getId())).collect(Collectors.toList());
        //奖项信息
        Prize prize = prizeMapper.selectByPrimaryKey(prizeId);
        //中奖名额
        Integer count = prize.getCount();
        List<Integer> participantIds = new ArrayList<>(count);
        if (participants.size() > count) {
            //随机选count个
            Random random = new Random();
            int index = random.nextInt(participants.size());
            while (count > 0) {
                while (participantIds.contains(participants.get(index).getId())) {
                    index = random.nextInt(participants.size());
                }
                count--;
                participantIds.add(participants.get(index).getId());
            }
        } else {
            participantIds.addAll(participants.stream().map(Participant::getId).collect(Collectors.toList()));
        }
        participantService.win(prizeId, participantIds);
        for (Integer id : participantIds) {
            PrizeRecord record = new PrizeRecord();
            record.setPrizeId(prizeId);
            record.setParticipantId(id);
            record.setCount(1);
            record.setPrizeTime(new Date());
            record.setHasReceived(Boolean.FALSE);
            recordMapper.insertSelective(record);
        }
        //抽奖结束
        prize.setIsOver(Boolean.TRUE);
        prizeMapper.updateByPrimaryKeySelective(prize);
        sunshine();
        return participantIds;
    }

    @Transactional
    @Override
    public int sunshine() {
        List<Prize> prizeList = getPrizeList().stream().filter(Prize::getIsOver).collect(Collectors.toList());
        if (CollectionUtils.size(prizeList) < 5) return 0;
        Prize prize = new Prize();
        prize.setId(6);
        //抽奖结束
        prize.setIsOver(Boolean.TRUE);
        prizeMapper.updateByPrimaryKeySelective(prize);
        return participantService.win(prize.getId(), participantService.getParticipants().stream().map(Participant::getId).collect(Collectors.toList()));
    }

}
