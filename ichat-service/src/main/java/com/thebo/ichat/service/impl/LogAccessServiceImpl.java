package com.thebo.ichat.service.impl;


import com.github.pagehelper.PageHelper;
import com.thebo.ichat.base.impl.BaseServiceImpl;
import com.thebo.ichat.entity.Country;
import com.thebo.ichat.entity.LogAccess;
import com.thebo.ichat.service.LogAccessService;
import org.apache.ibatis.type.DateTypeHandler;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by HB on 16/1/16.
 */
@Service
public class LogAccessServiceImpl extends BaseServiceImpl<LogAccess> implements LogAccessService {

    public List<LogAccess> selectByTimes(Date start, Date end, int page, int rows) {
        Example example = new Example(Country.class);
        Example.Criteria criteria = example.createCriteria();
        if (start != null) {
            criteria.andCondition("request_time >", start);
        }
        if (end != null) {
            criteria.andCondition("request_time <", end);
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }
}
