package com.dx.ss.hankoo.common.pager;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页对象抽象类。
 */
@Getter
@Setter
public abstract class BasePager<E> {

    private int status = 200;

    private String message = "操作成功";

    private int pageNum;

    private int pageSize;

    private List<E> dataList;

    public BasePager() {
    }

    public BasePager(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

}
