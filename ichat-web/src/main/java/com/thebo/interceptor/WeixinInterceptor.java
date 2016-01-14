package com.thebo.interceptor;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by HB on 16/1/9.
 */
public class WeixinInterceptor implements WxMpMessageInterceptor {
    private static Logger logger = LoggerFactory.getLogger(WeixinInterceptor.class);

    public boolean intercept(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        logger.debug("WeixinInterceptor------------------->intercept()");

        return true;
    }
}
