package com.thebo.ichat.process;

import com.alibaba.fastjson.JSON;
import com.thebo.ichat.common.utils.HttpClientUtils;
import com.thebo.ichat.dto.response.TuLingResponse;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用图灵机器人API，获取智能回复
 * Created by HB on 16/1/10.
 */

@Component
public class TulingApiProcess {

    private final Logger logger = LoggerFactory.getLogger(TulingApiProcess.class);
    private final String logPrefix = "[图灵机器人API]------";

    @Resource
    protected WxMpService wxMpService;

    /**
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
     *
     * @param wxMessage 微信消息
     */
    public WxMpXmlOutMessage getTulingAnswer(WxMpXmlMessage wxMessage) {

        WxMpXmlOutMessage outMessage = null;
        /** 此处为图灵api接口 */
        String apiUrl = "http://www.tuling123.com/openapi/api";

        Map<String, String> params = new HashMap<String,String>();
        params.put("key", "526d8246aa153e1355c613eebce6116f");
        params.put("info", wxMessage.getContent());

        try {
            String responseText = HttpClientUtils.doGet(apiUrl, params);
            TuLingResponse response = JSON.parseObject(responseText, TuLingResponse.class);
            logger.debug(ReflectionToStringBuilder.toString(response));
            outMessage = WxMpXmlOutMessage.TEXT().content(response.getText())
                    .fromUser(wxMessage.getToUserName())
                    .toUser(wxMessage.getFromUserName())
                    .build();
        } catch (Exception e) {
            logger.error("{}接口调用出错:{}" + logPrefix, e.getMessage());
        }
        return outMessage;
    }
}
