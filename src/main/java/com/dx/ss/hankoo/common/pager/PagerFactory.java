package com.dx.ss.hankoo.common.pager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

/**
 * 分页对象的工厂类
 */
@Component
public class PagerFactory implements IPagerFactory {

    @Override
    public <E> BasePager<E> generatePager(Page<E> page) {
        PageInfo<E> pageInfo = new PageInfo<>(page);
        return new Pager<>(pageInfo);
    }
}
