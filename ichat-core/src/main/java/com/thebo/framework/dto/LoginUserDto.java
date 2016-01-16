package com.thebo.framework.dto;

import com.thebo.framework.constants.UserStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class LoginUserDto implements Serializable {
    private String id;

    private String userName;

    private String realName;

    private String password;

    private UserStatus status;

    private String remark;

    private Integer province;

    private Integer city;

    private String qq;

    private String phone;

    private Integer logintimes;

    private Date loginTime;

    private String loginIp;

    List<ResourcesDto> resourcesList;
    List<RolesDto> rolesList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLogintimes() {
        return logintimes;
    }

    public void setLogintimes(Integer logintimes) {
        this.logintimes = logintimes;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public List<ResourcesDto> getResourcesList() {
        return resourcesList;
    }

    public void setResourcesList(List<ResourcesDto> resourcesList) {
        this.resourcesList = resourcesList;
    }

    public List<RolesDto> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<RolesDto> rolesList) {
        this.rolesList = rolesList;
    }
}

