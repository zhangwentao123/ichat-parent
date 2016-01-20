package com.thebo.ichat.web;


import com.alibaba.fastjson.JSON;
import com.thebo.framework.web.BaseCtrl;
import com.thebo.ichat.entity.ExpressNum;
import com.thebo.ichat.service.ExpressNumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 快递单维护
 */
@Controller
@RequestMapping("/express")
public class ExpressCtroller extends BaseCtrl{

    private static Logger log = LoggerFactory.getLogger(ExpressCtroller.class);

    @Resource
    private ExpressNumService expressNumService;

    @RequestMapping("addPre")
    public String addPre() {
        return "express/addPre";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String doAdd(HttpServletRequest request) {
        String company = request.getParameter("company");
        String startNo = request.getParameter("startNo");
        int count = Integer.valueOf(request.getParameter("count"));
        expressNumService.add(startNo, count, company);
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String showList(Model model) {
        model.addAttribute(expressNumService.select(null));
        return "express/list";
    }

    @RequestMapping(value="/json/{no}")
    @ResponseBody
    public Object getJson(@PathVariable("no") String no){
        ExpressNum expressNum = new ExpressNum();
        expressNum.setNo(no);
        return expressNumService.select(expressNum);
    }

    @RequestMapping(value="/count")
    @ResponseBody
    public Object count(){
//        ResponseEntity response = new ResponseEntity();
        Map map = new HashMap();
        Object count = null;
        if (redisUtil.exists("express_count")){
            count = redisUtil.get("express_count");
        } else {
            count = expressNumService.selectCount(null);
            redisUtil.set("express_count", count+"");
        }
        map.put("count", count);
        return JSON.toJSON(map);
    }
}
