package com.thebo.ichat.model;

import lombok.Data;

/**
 * @author liuzh_3nofxnp
 * @since 2015-11-09 22:25
 */
@Data
public class QueryModel {
    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;
    private Boolean countSql;
    private Boolean pageSizeZero;
    private Boolean reasonable;
}
