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
        button1.setName("关于我们");
        button1.setKey("V1001_ABOUT_US");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType("click");
        button2.setName("歌手简介");
        button2.setKey("V1001_TODAY_SINGER");

        WxMenuButton button3 = new WxMenuButton();
        button3.setType("click");
        button3.setName("友情链接");
        button3.setKey("V1001_BAT_WEBSITE");

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        WxMenuButton button11 = new WxMenuButton();
        button11.setType("click");
        button11.setName("赞一下我们");
        button11.setKey("V1001_GOOD");

        button1.getSubButtons().add(button11);

        WxMenuButton button21 = new WxMenuButton();
        button21.setType("view");
        button21.setName("百度");
        button21.setUrl("http://www.baidu.com/");

        WxMenuButton button22 = new WxMenuButton();
        button22.setType("view");
        button22.setName("腾讯");
        button22.setUrl("http://v.qq.com/");


        button2.getSubButtons().add(button21);
        button2.getSubButtons().add(button22);


        WxMenuButton button31 = new WxMenuButton();
        button31.setType("view");
        button31.setName("百度");
        button31.setUrl("http://www.baidu.com/");

        WxMenuButton button32 = new WxMenuButton();
        button32.setType("view");
        button32.setName("腾讯");
        button32.setUrl("http://v.qq.com/");

        WxMenuButton button33 = new WxMenuButton();
        button33.setType("view");
        button33.setName("淘宝");
        button33.setUrl("http://www.taobao.com/");

        button3.getSubButtons().add(button31);
        button3.getSubButtons().add(button32);
        button3.getSubButtons().add(button33);

        log.debug(menu.toJson().toString());
        wxMpService.menuCreate(menu);
    }


}
