package com.zkyunso.search.bean;

import com.fasterxml.jackson.annotation.JsonRootName;

public class SchemaBean {
	public String name;// solr对应列名
	public String type;// 分词器类型
	public boolean stored = true;
	public SchemaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SchemaBean(String name, String type, boolean stored) {
		super();
		this.name = name;
		this.type = type;
		this.stored = stored;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isStored() {
		return stored;
	}

	public void setStored(boolean stored) {
		this.stored = stored;
	}
	/**
	 * 转成solr restful 请求的json格式
	 */
	public String toJson() {
		return "{\"name\":\""+this.name+"\",\"stored\":"+this.isStored()+",\"type\":\""+this.type+"\"}";
	}
}
