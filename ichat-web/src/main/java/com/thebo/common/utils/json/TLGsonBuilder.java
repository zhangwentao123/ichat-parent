package com.thebo.common.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thebo.bean.TulingCustomMessage;

/**
 * Created by HB on 16/1/10.
 */
public class TLGsonBuilder {

    public static final GsonBuilder INSTANCE = new GsonBuilder();

    static {
        INSTANCE.disableHtmlEscaping();
        INSTANCE.registerTypeAdapter(TulingCustomMessage.class, new TulingMessageGsonAdapter());
    }

    public static Gson create() {return INSTANCE.create();}
}
