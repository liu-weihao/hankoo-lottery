package com.dx.ss.hankoo.service;

import com.dx.ss.hankoo.dal.beans.Participant;
import com.dx.ss.hankoo.dal.model.ParticipantStatisticsModel;
import com.dx.ss.hankoo.dal.search.biz.ParticipantSearch;

import java.util.List;

public interface ParticipantService {

    List<Participant> getParticipants();

    List<Participant> getParticipants(ParticipantSearch search);

    boolean removeParticipant(Integer id);

    boolean addParticipant(Participant participant);

    ParticipantStatisticsModel getParticipantStatistics();

    int empty();

    int addParticipants(List<Participant> participants);

    int win(Integer prizeId, List<Integer> participantIds);
}
