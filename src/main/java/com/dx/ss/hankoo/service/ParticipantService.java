package com.dx.ss.hankoo.service;

import com.dx.ss.hankoo.dal.beans.Participant;
import com.dx.ss.hankoo.dal.search.biz.ParticipantSearch;

import java.util.List;

public interface ParticipantService {

    List<Participant> getParticipants(ParticipantSearch search);

    boolean removeParticipant(Integer id);
}
