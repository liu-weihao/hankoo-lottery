package com.dx.ss.hankoo.dal.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParticipantStatisticsModel {

    /**
     * 参与者总数量
     */
    private int total;

    /**
     * 中奖人数
     */
    private int winnerCount;
}
