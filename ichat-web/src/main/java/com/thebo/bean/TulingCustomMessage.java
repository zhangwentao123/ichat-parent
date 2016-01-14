package com.thebo.bean;

import com.thebo.common.utils.json.TLGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 图灵应答消息
 * Created by HB on 16/1/10.
 */
public class TulingCustomMessage implements Serializable {

    private String code;
    private String text;
    private String url;
    private List<Infos> list = new ArrayList<Infos>();

    public String toJson() { return TLGsonBuilder.INSTANCE.create().toJson(this);}

    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
    public List<Infos> getList() {return list;}
    public void setList(List<Infos> list) {this.list = list;}

    public static class Infos {
        private String article;
        private String source;
        private String icon;
        private String detailurl;
        private String name;
        private String info;

        public String getArticle() { return article;}
        public void setArticle(String article) {this.article = article;}
        public String getSource() {return source;}
        public void setSource(String source) {this.source = source;}
        public String getIcon() {return icon;}
        public void setIcon(String icon) {this.icon = icon;}
        public String getDetailurl() {return detailurl;}
        public void setDetailurl(String detailurl) {this.detailurl = detailurl;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getInfo() {return info;}
        public void setInfo(String info) {this.info = info;}
    }
}
