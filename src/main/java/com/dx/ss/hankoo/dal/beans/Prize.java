package com.dx.ss.hankoo.dal.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Setter
@Getter
public class Prize {
    /**
     * 奖项id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 奖项名称
     */
    private String name;

    /**
     * 奖品名称
     */
    private String award;

    /**
     * 奖品数量
     */
    private Integer count;

    /**
     * 是否已经结束
     */
    @Column(name = "is_over")
    private Boolean isOver;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

}