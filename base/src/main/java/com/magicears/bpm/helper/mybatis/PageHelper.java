package com.magicears.bpm.helper.mybatis;

/**
 * Created by wangfei on 2016/7/1.
 */
public class PageHelper {
    private Integer page;
    private Integer size;
    private Integer offset;
    private String sortBy;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public PageHelper(Integer page, Integer size, String sortBy) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.offset = (page - 1) * size;
    }

    public PageHelper(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
