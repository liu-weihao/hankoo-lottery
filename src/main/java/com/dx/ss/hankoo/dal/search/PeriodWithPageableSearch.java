package com.dx.ss.hankoo.dal.search;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 支持分页+时段查询
 */
@Getter
@Setter
public class PeriodWithPageableSearch extends PageableSearch {

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

    /**
     * 对查询条件进行必要的逻辑验证。
     * 简单的验证可以加注解，复杂的验证交给validate()
     *5
     * @return true 验证通过，false 验证不通过
     */
    @Override
    public boolean validate() {

        return startTime == null || endTime == null || startTime.compareTo(endTime) <= 0 && super.validate();
    }
}
