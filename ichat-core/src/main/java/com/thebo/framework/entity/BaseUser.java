package com.thebo.framework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BaseUser extends BaseEntity {
	private static final long serialVersionUID = -4789371342509466498L;
	
	protected String username;
	protected String password;

}
