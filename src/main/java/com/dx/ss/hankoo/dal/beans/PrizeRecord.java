package com.dx.ss.hankoo.dal.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Setter
@Getter
@Table(name = "prize_record")
public class PrizeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 奖项id
     */
    @Column(name = "prize_id")
    private Integer prizeId;

    /**
     * 参与者id
     */
    @Column(name = "participant_id")
    private Integer participantId;

    /**
     * 中奖数量
     */
    private Integer count;

    /**
     * 中奖时间
     */
    @Column(name = "prize_time")
    private Date prizeTime;

    @Column(name = "has_received")
    private Boolean hasReceived;
}