package com.thebo.common.utils.json;


import com.google.gson.*;
import com.thebo.bean.TulingCustomMessage;
import com.thebo.common.TLConsts;

import java.lang.reflect.Type;

/**
 * Created by HB on 16/1/10.
 */
public class TulingMessageGsonAdapter implements JsonSerializer<TulingCustomMessage>{

    public JsonElement serialize(TulingCustomMessage message, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("code", message.getCode());
        messageJson.addProperty("text", message.getText());

        if(TLConsts.CUSTOM_MSG_LINK.equals(message.getCode())) {
            messageJson.addProperty("url", message.getUrl());
        }

        if(TLConsts.CUSTOM_MSG_NEWS.equals(message.getCode())) {
            JsonArray infosJsonArray = new JsonArray();
            for (TulingCustomMessage.Infos info : message.getList()) {
                JsonObject infoJson = new JsonObject();
                infoJson.addProperty("article", info.getArticle());
                infoJson.addProperty("source", info.getSource());
                infoJson.addProperty("icon", info.getIcon());
                infoJson.addProperty("detailurl", info.getDetailurl());
                infosJsonArray.add(infoJson);
            }
            messageJson.add("list", infosJsonArray);
        }

        if (TLConsts.CUSTOM_MSG_MENUS.equals(message.getCode())) {
            JsonArray infosJsonArray = new JsonArray();
            for (TulingCustomMessage.Infos info : message.getList()) {
                JsonObject infoJson = new JsonObject();
                infoJson.addProperty("name", info.getName());
                infoJson.addProperty("icon", info.getIcon());
                infoJson.addProperty("info", info.getInfo());
                infoJson.addProperty("detailurl", info.getDetailurl());
                infosJsonArray.add(infoJson);
            }
            messageJson.add("list", infosJsonArray);
        }

        return messageJson;
    }
}
