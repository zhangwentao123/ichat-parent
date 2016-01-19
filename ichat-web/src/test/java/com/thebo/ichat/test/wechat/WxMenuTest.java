package com.thebo.ichat.test.wechat;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class WxMenuTest {
    private static Logger log = LoggerFactory.getLogger(WxMenuTest.class);

    @Resource
    protected WxMpService wxMpService;


    @Test
    public void testMenuCrete() throws WxErrorException {
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType("click");
        button1.setName("菜单A");
        button1.setKey("V1001_ABOUT_US");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType("click");
        button2.setName("菜单B");
        button2.setKey("V1001_TODAY_SINGER");

        WxMenuButton button3 = new WxMenuButton();
        button3.setType("click");
        button3.setName("菜单C");
        button3.setKey("V1001_BAT_WEBSITE");

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        WxMenuButton button11 = new WxMenuButton();
        button11.setType("click");
        button11.setName("点击推荐事件");
        button11.setKey("V1001_GOOD");

        WxMenuButton button12 = new WxMenuButton();
        button12.setType("view");
        button12.setName("跳转URL");
        button12.setKey("V1002_GOOD");
        button12.setUrl("http://v.qq.com/");

        WxMenuButton button13 = new WxMenuButton();
        button13.setType("scancode_push");
        button13.setName("扫码推事件");
        button13.setKey("V1003_GOOD");

        button1.getSubButtons().add(button11);
        button1.getSubButtons().add(button12);
        button1.getSubButtons().add(button13);

        WxMenuButton button21 = new WxMenuButton();
        button21.setType("scancode_waitmsg");
        button21.setName("扫码推事件提示框");
        button21.setKey("V2001_GOOD");

        WxMenuButton button22 = new WxMenuButton();
        button22.setType("pic_sysphoto");
        button22.setName("系统拍照发图");
        button22.setKey("V2002_GOOD");

        WxMenuButton button23 = new WxMenuButton();
        button23.setType("pic_photo_or_album");
        button23.setName("拍照或者相册发图");
        button23.setKey("V2003_GOOD");


        button2.getSubButtons().add(button21);
        button2.getSubButtons().add(button22);
        button2.getSubButtons().add(button23);


        WxMenuButton button31 = new WxMenuButton();
        button31.setType("pic_weixin");
        button31.setName("微信相册发图器");
        button31.setKey("V3001_GOOD");

        WxMenuButton button32 = new WxMenuButton();
        button32.setType("location_select");
        button32.setName("地理位置选择器");
        button32.setKey("V3002_GOOD");

//        WxMenuButton button33 = new WxMenuButton();
//        button33.setType("media_id");
//        button33.setName("下发消息");
//        button33.setKey("V3003_GOOD");
//
//        WxMenuButton button34 = new WxMenuButton();
//        button34.setType("view_limited");
//        button34.setName("图文消息URL");
//        button34.setKey("V3004_GOOD");


        button3.getSubButtons().add(button31);
        button3.getSubButtons().add(button32);
//        button3.getSubButtons().add(button33);
//        button3.getSubButtons().add(button34);

        log.debug(menu.toJson().toString());
        wxMpService.menuCreate(menu);
    }


}
