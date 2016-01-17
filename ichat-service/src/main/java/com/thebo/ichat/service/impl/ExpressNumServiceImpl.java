package com.thebo.ichat.service.impl;

import com.thebo.ichat.base.impl.BaseServiceImpl;
import com.thebo.ichat.entity.ExpressNum;
import com.thebo.ichat.service.ExpressNumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by HB on 15/11/8.
 * 快递单维护查询
 */
@Service
@Transactional
public class ExpressNumServiceImpl extends BaseServiceImpl<ExpressNum> implements ExpressNumService {

    private static Logger log = LoggerFactory.getLogger(ExpressNumServiceImpl.class);

    /**
     * 新增快递单
     * @param startNo 起始单号
     * @param count 数量
     */
    public void add(String startNo, int count, String company) {
        long tmp = Long.valueOf(startNo);
        ExpressNum expressNum = new ExpressNum();
        expressNum.setCompany(company);
        do {
            expressNum.setNo(String.valueOf(tmp));
            save(expressNum);
            tmp++;
            count--;
        } while (count > 0);
    }

}
