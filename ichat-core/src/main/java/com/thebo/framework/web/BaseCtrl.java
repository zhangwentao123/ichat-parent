package com.thebo.framework.web;

import com.thebo.framework.cache.RedisUtil;
import com.thebo.framework.constants.SysConstants;
import com.thebo.framework.util.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by HB on 16/1/19.
 */
@Controller
public class BaseCtrl {

    @Resource
    protected RedisUtil redisUtil;

}
