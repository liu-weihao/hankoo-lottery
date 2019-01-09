package com.dx.ss.hankoo.dal.search;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 排序 + 分页 + 时间段
 */
@Getter
@Setter
public class SuperSearch extends BaseSearch {

    private String sortName;

    private String sortOrder = "ASC";

    /**
     * 页码，从1开始
     */
    private Integer pageNum = 1;

    /**
     * 一页数据容量，默认15条
     */
    private Integer pageSize = 15;

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
     *
     * @return true 验证通过，false 验证不通过
     */
    @Override
    public boolean validate() {
        return pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0 && (startTime == null || endTime == null || startTime.compareTo(endTime) <= 0);
    }
}
