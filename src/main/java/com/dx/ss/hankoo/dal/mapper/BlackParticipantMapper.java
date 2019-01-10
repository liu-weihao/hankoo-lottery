package com.dx.ss.hankoo.dal.mapper;

import com.dx.ss.hankoo.dal.beans.BlackParticipant;
import com.dx.ss.hankoo.dal.model.BlackParticipantModel;
import com.dx.ss.hankoo.dal.search.biz.BlackParticipantSearch;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface BlackParticipantMapper extends Mapper<BlackParticipant>, MySqlMapper<BlackParticipant> {

    List<BlackParticipantModel> getBlackParticipants(@Param("search") BlackParticipantSearch search);
}