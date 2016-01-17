package com.thebo.ichat.service;

import com.thebo.ichat.base.BaseService;
import com.thebo.ichat.entity.LogAccess;

import java.util.Date;
import java.util.List;

/**
 * Created by HB on 16/1/16.
 */
public interface LogAccessService extends BaseService<LogAccess> {

    public List<LogAccess> selectByTimes(Date start, Date end, int page, int rows);
}
