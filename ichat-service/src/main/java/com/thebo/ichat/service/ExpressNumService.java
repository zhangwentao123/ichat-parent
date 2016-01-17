package com.thebo.ichat.service;

import com.thebo.ichat.base.BaseService;
import com.thebo.ichat.entity.ExpressNum;

/**
 * Created by HB on 15/11/8.
 * 快递单查询及维护
 */
public interface ExpressNumService extends BaseService<ExpressNum> {

    void add(String startNo, int count, String company);

}
