package com.dx.ss.hankoo.dal.search;

import lombok.Getter;
import lombok.Setter;

/**
 * 支持结果集排序和分页的查询条件。
 */
@Setter
@Getter
public class SortWithPageableSearch extends PageableSearch {

    private String sortName;

    private String sortOrder = "ASC";

    public SortWithPageableSearch() {
    }

    public SortWithPageableSearch(String sortName, String sortOrder) {
        this.sortName = sortName;
        this.sortOrder = sortOrder;
    }
}
