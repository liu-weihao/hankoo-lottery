package com.dx.ss.hankoo.dal.search;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 支持时间段查询
 */
@Getter
@Setter
public class PeriodSearch extends BaseSearch {

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public PeriodSearch() {
    }

    public PeriodSearch(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 对查询条件进行必要的逻辑验证。
     * 简单的验证可以加注解，复杂的验证交给validate()
     *
     * @return true 验证通过，false 验证不通过
     */
    @Override
    public boolean validate() {
        return startTime == null || endTime == null || startTime.compareTo(endTime) <= 0;
    }
}
