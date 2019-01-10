package com.dx.ss.hankoo.service;

import com.dx.ss.hankoo.dal.model.BlackParticipantModel;
import com.dx.ss.hankoo.dal.search.biz.BlackParticipantSearch;

import java.util.List;
import java.util.Map;

public interface BlackParticipantService {

    List<BlackParticipantModel> getBlackParticipants(BlackParticipantSearch search);

    boolean removeBlackParticipantById(Integer id);

    int removeBlackParticipant(Integer participantId);

    Map<String, Integer> getBlackParticipantStatistics();
}
