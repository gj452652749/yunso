package com.view.model;

public class DsDetails {
	int id;
	String name;
	String remark;
	int dbType;
	String dbUrl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDbType() {
		return dbType;
	}
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
}
