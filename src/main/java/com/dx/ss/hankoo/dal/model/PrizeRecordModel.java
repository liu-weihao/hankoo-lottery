package com.dx.ss.hankoo.dal.model;

import com.dx.ss.hankoo.dal.beans.PrizeRecord;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrizeRecordModel extends PrizeRecord {

    private String name;

    private String prizeName;

    private String award;
}
