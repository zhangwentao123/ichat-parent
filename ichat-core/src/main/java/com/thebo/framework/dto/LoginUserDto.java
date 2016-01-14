package com.thebo.framework.dto;

import com.thebo.framework.constants.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
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
}

