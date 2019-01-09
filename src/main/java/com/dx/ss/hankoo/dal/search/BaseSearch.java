package com.dx.ss.hankoo.dal.search;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * 支持关键字检索的基本查询条件，不可直接使用。
 * 所有查询条件Dto都要直接或者间接的继承 BaseCondition。
 * 基于文本的单条件查询，统一用keywords接收。
 * @author Eric Lau
 * @Date 2017/8/2.
 */
@Setter
@Getter
public abstract class BaseSearch {

    /**
     * 搜索关键字
     */
    private String keywords;

    public BaseSearch() {
    }

    public BaseSearch(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 对查询条件进行必要的逻辑验证。
     * 简单的验证可以加注解，复杂的验证交给validate()
     * @return true 验证通过，false 验证不通过
     */
    public abstract boolean validate();

    /**
     * 根据关键字构造模糊查询条件，默认是全模糊匹配。
     * @return 在keywords两侧加上%
     */
    public String likes() {
        if(StringUtils.isBlank(this.keywords)) {
            return StringUtils.EMPTY;
        }
        return "%" + this.keywords + "%";
    }

    /**
     * 根据关键字构造模糊查询条件
     * @param matchHead true 表示用前一部分匹配，即在keywords右侧追加%，否则在左侧追加。
     * @return 含有通配符的LIKE语句
     */
    public String likes(boolean matchHead) {
        if(StringUtils.isBlank(this.keywords)) {
            return StringUtils.EMPTY;
        }
        return matchHead ? (this.keywords + "%") : ("%" + this.keywords);
    }

    /**
     * 构造含有占位符的LIKE语句，形如：LIKE '__Eric'。
     * 一个下划线(_)代表一个字符
     * @param matchHead true 表示用前一部分匹配，即在keywords右侧追加占位符，否则在左侧追加。
     * @param placeCount 占位符的数量
     * @return 含有占位符的LIKE语句
     */
    public String likes(boolean matchHead, int placeCount) {
        if(StringUtils.isBlank(this.keywords)) {
            return StringUtils.EMPTY;
        }
        if(placeCount == 0) {
            return likes(matchHead);
        }
        StringBuilder placeBuilder = new StringBuilder(placeCount);
        for (int i=0; i < placeCount; i++) {
            placeBuilder.append("_");
        }
        return matchHead ? (this.keywords + placeBuilder) : (placeBuilder + this.keywords);
    }
}
