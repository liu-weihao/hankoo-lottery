package com.dx.ss.hankoo.service;

import com.dx.ss.hankoo.dal.beans.BlackParticipant;
import com.dx.ss.hankoo.dal.model.BlackParticipantModel;
import com.dx.ss.hankoo.dal.search.biz.BlackParticipantSearch;

import java.util.List;
import java.util.Map;

public interface BlackParticipantService {

    List<BlackParticipantModel> getBlackParticipants(BlackParticipantSearch search);

    List<BlackParticipant> getBlackParticipants(Integer prizeId);

    boolean removeBlackParticipantById(Integer id);

    int addBlackParticipants(List<BlackParticipant> blackParticipants);

    int empty();

    int removeBlackParticipant(Integer participantId);

    Map<String, Integer> getBlackParticipantStatistics();
}
