package com.thebo.framework.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 实体类 - 基类
 * ============================================================================
 */
@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6718838800112233445L;
	
	public static final String CREATE_DATE_PROPERTY_NAME = "createTime";// "创建日期"属性名称
	public static final String MODIFY_DATE_PROPERTY_NAME = "updateTime";// "修改日期"属性名称

	@Id
	@GeneratedValue(generator = "UUID")
	protected String id;// ID

	protected Date createTime;// 创建日期
	protected String createUser;// 创建人
	protected Date updateTime;// 修改日期
	protected String updateUser;// 修改人

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (id == null || other.getId() == null) {
			return false;
		} else {
			return (id.equals(other.getId()));
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}