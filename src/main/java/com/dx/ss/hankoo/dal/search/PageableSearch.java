package com.dx.ss.hankoo.dal.search;

import lombok.Getter;
import lombok.Setter;

/**
 * 可分页的查询条件。
 */
@Getter
@Setter
public class PageableSearch extends BaseSearch {

    /**
     * 页码，从1开始
     */
    private Integer pageNum = 1;

    /**
     * 一页数据容量，默认15条
     */
    private Integer pageSize = 15;

    /**
     * 跳转至指定页码
     */
    @Deprecated
    private Integer skip = 1;

    public PageableSearch() {
    }

    public PageableSearch(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageableSearch(Integer pageNum, Integer pageSize, Integer skip) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.skip = skip;
    }

    /**
     * 对查询条件进行必要的逻辑验证。
     * 简单的验证可以加注解，复杂的验证交给validate()
     * @return true 验证通过，false 验证不通过
     */
    @Override
    public boolean validate() {

        return pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0;
    }
}
