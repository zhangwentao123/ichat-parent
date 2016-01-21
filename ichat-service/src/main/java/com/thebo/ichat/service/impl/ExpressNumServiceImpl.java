package com.thebo.ichat.service.impl;

import com.alibaba.fastjson.JSON;
import com.thebo.ichat.base.impl.BaseServiceImpl;
import com.thebo.ichat.entity.ExpressNum;
import com.thebo.ichat.service.ExpressNumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        expressNum = new ExpressNum();
        expressNum.setStatus(1);
        List<ExpressNum> expressNums = select(expressNum);
        for (ExpressNum num : expressNums) {
            redisUtil.sadd("usable_express_num", JSON.toJSONString(num));
        }
    }

    /**
     * 统计快递单数量
     * @param usable 是否可用
     * @return
     */
    public long selectCount(boolean usable) {
        if (usable) {
            long count = redisUtil.scard("usable_express_num");
            if (count == 0){
                ExpressNum expressNum = new ExpressNum();
                expressNum.setStatus(1);
                count = selectCount(expressNum);
            }
            return count;
        } else {
            return selectCount(null);
        }
    }

}
