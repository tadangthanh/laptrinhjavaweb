package com.laptrinhjavaweb.model;

import java.sql.Timestamp;

public class RoleModel extends AbstractModel<RoleModel> {
	private String code;
	private String name;

	public RoleModel() {

	}

	public RoleModel(Long id, String code, String name, Timestamp createdDate, Timestamp modifiedDate, String createdBy,
			String modifiedBy) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
