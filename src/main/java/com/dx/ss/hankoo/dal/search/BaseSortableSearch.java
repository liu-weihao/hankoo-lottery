package com.dx.ss.hankoo.dal.search;


import lombok.Getter;
import lombok.Setter;

/**
 * 支持结果集排序的查询条件。
 */
@Setter
@Getter
public abstract class BaseSortableSearch extends BaseSearch {

    private String sortName;

    private String sortOrder;
}
