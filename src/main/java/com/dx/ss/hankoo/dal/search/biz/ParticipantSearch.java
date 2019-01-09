package com.dx.ss.hankoo.dal.search.biz;

import com.dx.ss.hankoo.dal.search.PageableSearch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantSearch extends PageableSearch {

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否已中奖
     */
    private Boolean isWinner;
}
