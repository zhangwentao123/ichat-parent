package com.thebo.entity;

import java.util.Date;

public class ActivityConf {
    private String code;

    private String name;

    private Date beginTime;

    private Date endTime;

    private String fromApp;

    private String activityUrl;

    private String activityImg;

    private String status;

    private String obtainCoupon;

    private String obtainPrivilege;

    private String obtainExchange;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getFromApp() {
        return fromApp;
    }

    public void setFromApp(String fromApp) {
        this.fromApp = fromApp == null ? null : fromApp.trim();
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl == null ? null : activityUrl.trim();
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg == null ? null : activityImg.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getObtainCoupon() {
        return obtainCoupon;
    }

    public void setObtainCoupon(String obtainCoupon) {
        this.obtainCoupon = obtainCoupon == null ? null : obtainCoupon.trim();
    }

    public String getObtainPrivilege() {
        return obtainPrivilege;
    }

    public void setObtainPrivilege(String obtainPrivilege) {
        this.obtainPrivilege = obtainPrivilege == null ? null : obtainPrivilege.trim();
    }

    public String getObtainExchange() {
        return obtainExchange;
    }

    public void setObtainExchange(String obtainExchange) {
        this.obtainExchange = obtainExchange == null ? null : obtainExchange.trim();
    }
}