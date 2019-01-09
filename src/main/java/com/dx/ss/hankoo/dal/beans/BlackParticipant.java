package com.dx.ss.hankoo.dal.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Table(name = "black_participant")
public class BlackParticipant {

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

}