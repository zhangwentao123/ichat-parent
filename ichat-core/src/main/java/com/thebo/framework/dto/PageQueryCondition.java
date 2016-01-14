package com.thebo.framework.dto;

import lombok.Data;

/**
 * Created by hebo on 2016-1-14.
 */
@Data
public class PageQueryCondition {
    private int sEcho;
    private int columns;
    /** 每页开始数标 */
    private int pageStart;
    /** 每页显示条数 */
    private int pageSize;
    /** 当前请求页 */
    private int currentPage = 1;

    public int getPageStart(){
        return (this.getCurrentPage()-1) <= 0 ? 0:(this.getCurrentPage()-1)*this.getPageSize();
    }

    public Page getPage(){
        Page page = new Page();
        page.setCurrentPage(this.getCurrentPage());
        page.setPageSize(this.getPageSize());
        return page;
    }
}
