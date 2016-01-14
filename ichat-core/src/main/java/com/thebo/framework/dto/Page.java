package com.thebo.framework.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by hebo on 2016-1-14.
 */
@Data
public class Page {

    private int recordsTotal;
    private List<?> data;
    private int currentPage;
    private int pageCount;
    private int pageSize;

    public int getPageCount(){
        return recordsTotal%this.getPageSize()==0? (recordsTotal/this.getPageSize()):(recordsTotal/this.getPageSize()+1);
    }
}
