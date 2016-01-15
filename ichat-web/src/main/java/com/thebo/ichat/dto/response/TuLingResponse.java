package com.thebo.ichat.dto.response;

import com.thebo.ichat.common.utils.json.TLGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 图灵应答消息
 * Created by HB on 16/1/10.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class TuLingResponse implements Serializable {

    private String code;
    private String text;
    private String url;
    private List<Infos> list = new ArrayList<Infos>();

    public String toJson() { return TLGsonBuilder.INSTANCE.create().toJson(this);}

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = true)
    public static class Infos {
        private String article;
        private String source;
        private String icon;
        private String detailurl;
        private String name;
        private String info;
    }
}
