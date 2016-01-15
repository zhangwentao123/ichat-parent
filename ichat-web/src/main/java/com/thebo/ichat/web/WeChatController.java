package com.thebo.ichat.web;

import com.thebo.ichat.common.enums.EventType;
import com.thebo.ichat.common.enums.MessageType;
import com.thebo.ichat.process.TulingApiProcess;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 微信消息请求处理
 * Created by HB on 16/1/9.
 */

@Controller
@RequestMapping("/wechat")
public class WeChatController {
    private static Logger log = LoggerFactory.getLogger(WeChatController.class);

    @Resource
    protected WxMpConfigStorage wxMpConfigStorage;

    @Resource
    protected WxMpService wxMpService;

    @Resource
    protected WxMpMessageRouter wxMpMessageRouter;

    @Resource
    protected TulingApiProcess tulingApiProcess;

    @PostConstruct
    public void init() {
        WxMpMessageHandler handler = new WxMpMessageHandler() {
            public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                            Map<String, Object> context,
                                            WxMpService wxMpService,
                                            WxSessionManager sessionManager) throws WxErrorException {
                /*WxMpXmlOutMessage m = WxMpXmlOutMessage.TEXT().content("测试加密消息!")
                        .fromUser(wxMessage.getToUserName())
                        .toUser(wxMessage.getFromUserName()).build();*/
                return handleMsg(wxMessage);
            }
        };
        wxMpMessageRouter.rule()
                .async(false)
//                .content("哈哈")
                .handler(handler)
                .end();
    }


    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息合法
            PrintWriter out = response.getWriter();
            out.print(echostr);
        }

    }

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");

        WxMpXmlMessage inMessage;

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
        } else if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
        } else {
            response.getWriter().println("不可识别的加密类型");
            return;
        }



        WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);

        if (outMessage != null) {
            if ("raw".equals(encryptType)) {
                response.getWriter().write(outMessage.toXml());
            } else if ("aes".equals(encryptType)) {
                response.getWriter().write(outMessage.toEncryptedXml(wxMpConfigStorage));
            }
            return;
        }

        response.getWriter().println("能不能好好说话！");
    }

    /**
     * 微信消息处理
     *
     * @return 回复消息
     */
    protected WxMpXmlOutMessage handleMsg(WxMpXmlMessage wxMessage) {
        String msgtype = wxMessage.getMsgType();
        if (WxConsts.XML_MSG_EVENT.equals(msgtype)) {
            return handleEventMsg(wxMessage);
        }
        else {
            return handleNormalMsg(wxMessage);
        }
    }

    /**
     * 处理普通消息
     *
     * @return 回复消息
     */
    protected WxMpXmlOutMessage handleNormalMsg(WxMpXmlMessage wxMessage) {
        WxMpXmlOutMessage msg = null;
        MessageType mt;
        try {
            mt = MessageType.valueOf(wxMessage.getMsgType());
        }
        catch (Exception e) {
            log.error("There are have found new meessage type in wechat.");
            mt = MessageType.def;
        }
        switch (mt) {
            case text:
                msg = tulingApiProcess.getTulingAnswer(wxMessage);
//                TextMsg tm = new TextMsg(msgHandler.getValues());
//                msg = handler.text(tm);
                break;
            case image:
//                ImageMsg im = new ImageMsg(msgHandler.getValues());
//                msg = handler.image(im);
                break;
            case voice:
//                VoiceMsg vom = new VoiceMsg(msgHandler.getValues());
//                msg = handler.voice(vom);
                break;
            case video:
//                VideoMsg vim = new VideoMsg(msgHandler.getValues());
//                msg = handler.video(vim);
                break;
            case shortvideo:
//                VideoMsg shortvim = new VideoMsg(msgHandler.getValues());
//                msg = handler.shortVideo(shortvim);
                break;
            case location:
//                LocationMsg locm = new LocationMsg(msgHandler.getValues());
//                msg = handler.location(locm);
                break;
            case link:
//                LinkMsg lm = new LinkMsg(msgHandler.getValues());
//                msg = handler.link(lm);
                break;
            default:
//                BasicMsg bm = new BasicMsg(msgHandler.getValues());
//                msg = handler.defMsg(bm);
                break;
        }
        return msg;
    }

    /**
     * 处理事件消息
     *
     * @return 回复消息
     */
    protected WxMpXmlOutMessage handleEventMsg(WxMpXmlMessage wxMessage) {
        WxMpXmlOutMessage msg = null;
        EventType et;
        try {
            et = EventType.valueOf(wxMessage.getEvent());
        }
        catch (Exception e) {
            log.error("There are have found new event type from wechat.");
            et = EventType.def;
        }
        switch (et) {
            case subscribe:
                msg = WxMpXmlOutMessage.TEXT().content("测试关注消息推送!")
                        .fromUser(wxMessage.getToUserName())
                        .toUser(wxMessage.getFromUserName()).build();
//                BasicEvent sube = new BasicEvent(msgHandler.getValues());
//                msg = handler.eSub(sube);
                break;
            case unsubscribe:
//                BasicEvent unsube = new BasicEvent(msgHandler.getValues());
//                handler.eUnSub(unsube);
                break;
            case SCAN:
//                ScanEvent se = new ScanEvent(msgHandler.getValues());
//                msg = handler.eScan(se);
                break;
            case LOCATION:
//                LocationEvent le = new LocationEvent(msgHandler.getValues());
//                handler.eLocation(le);
                break;
            case CLICK:
//                MenuEvent cme = new MenuEvent(msgHandler.getValues());
//                msg = handler.eClick(cme);
                break;
            case VIEW:
//                MenuEvent vme = new MenuEvent(msgHandler.getValues());
//                handler.eView(vme);
                break;
            case scancode_push:
//                ScanCodeEvent sce = new ScanCodeEvent(msgHandler.getValues());
//                msg = handler.eScanCodePush(sce);
                break;
            case scancode_waitmsg:
//                ScanCodeEvent scemsg = new ScanCodeEvent(msgHandler.getValues());
//                msg = handler.eScanCodeWait(scemsg);
                break;
            case pic_sysphoto:
//                SendPhotosEvent spesys = new SendPhotosEvent(msgHandler.getValues());
//                msg = handler.ePicSysPhoto(spesys);
                break;
            case pic_photo_or_album:
//                SendPhotosEvent spealb = new SendPhotosEvent(msgHandler.getValues());
//                msg = handler.ePicPhotoOrAlbum(spealb);
                break;
            case pic_weixin:
//                SendPhotosEvent spewx = new SendPhotosEvent(msgHandler.getValues());
//                msg = handler.ePicWeixin(spewx);
                break;
            case location_select:
//                SendLocationInfoEvent lse = new SendLocationInfoEvent(msgHandler.getValues());
//                msg = handler.eLocationSelect(lse);
                break;
            // TODO 暂不清楚微信的推送
            /*
             * case media_id:
             * case view_limited:
             * BasicEvent mvbe = new BasicEvent(msgHandler.getValues());
             * msg = handler.defEvent(mvbe);
             * break;
             */
            case TEMPLATESENDJOBFINISH:
//                SentTmlJobEvent stje = new SentTmlJobEvent(msgHandler.getValues());
//                handler.eSentTmplJobFinish(stje);
                break;
            case MASSSENDJOBFINISH:
//                SentAllJobEvent saje = new SentAllJobEvent(msgHandler.getValues());
//                handler.eSentAllJobFinish(saje);
                break;
            default:
//                BasicEvent be = new BasicEvent(msgHandler.getValues());
//                msg = handler.defEvent(be);
                break;
        }
        return msg;
    }
}
