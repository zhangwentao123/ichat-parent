package com.thebo.ichat.entity;

import com.thebo.framework.entity.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "express_num")
public class ExpressNum extends BaseEntity{
    private String no;

    private Boolean usable;

    private String company;

    private Date createtime;

    private Date updatetime;

    /**
     * @return no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * @return usable
     */
    public Boolean getUsable() {
        return usable;
    }

    /**
     * @param usable
     */
    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    /**
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}