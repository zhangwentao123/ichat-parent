package com.thebo.ichat.entity;

import com.thebo.framework.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "express_num")
public class ExpressNum extends BaseEntity{
    /**
     * 快递单号
     */
    private String no;

    /**
     * 快递公司
     */
    private String company;

    /**
     * 是否可用
     */
    private Integer status;

    /**
     * 获取快递单号
     *
     * @return no - 快递单号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置快递单号
     *
     * @param no 快递单号
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * 获取快递公司
     *
     * @return company - 快递公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置快递公司
     *
     * @param company 快递公司
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 获取是否可用
     *
     * @return status - 是否可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否可用
     *
     * @param status 是否可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}