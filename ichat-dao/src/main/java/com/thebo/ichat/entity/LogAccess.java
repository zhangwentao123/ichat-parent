package com.thebo.ichat.entity;

import com.thebo.framework.entity.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "log_access")
public class LogAccess extends BaseEntity{
    /**
     * 执行方法
     */
    private String signature;

    /**
     * 执行时间
     */
    @Column(name = "request_time")
    private Date requestTime;

    /**
     * 耗时ms
     */
    private Integer time;

    /**
     * 是否成功
     */
    private Integer status;

    /**
     * 获取执行方法
     *
     * @return signature - 执行方法
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 设置执行方法
     *
     * @param signature 执行方法
     */
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**
     * 获取执行时间
     *
     * @return request_time - 执行时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * 设置执行时间
     *
     * @param requestTime 执行时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 获取耗时ms
     *
     * @return time - 耗时ms
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 设置耗时ms
     *
     * @param time 耗时ms
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 获取是否成功
     *
     * @return status - 是否成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否成功
     *
     * @param status 是否成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}