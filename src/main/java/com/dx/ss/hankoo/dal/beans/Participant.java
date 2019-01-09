package com.dx.ss.hankoo.dal.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
public class Participant {
    /**
     * 参与者id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 附加信息
     */
    private String info;

    /**
     * 是否中奖
     */
    @Column(name = "is_winner")
    private Boolean isWinner;

}