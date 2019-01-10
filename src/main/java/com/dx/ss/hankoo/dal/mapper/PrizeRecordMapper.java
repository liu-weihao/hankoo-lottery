package com.dx.ss.hankoo.dal.mapper;

import com.dx.ss.hankoo.dal.beans.PrizeRecord;
import com.dx.ss.hankoo.dal.model.PrizeRecordModel;
import com.dx.ss.hankoo.dal.search.biz.PrizeRecordSearch;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PrizeRecordMapper extends Mapper<PrizeRecord> {

    List<PrizeRecordModel> getPrizeRecordList(@Param("search") PrizeRecordSearch search);
}