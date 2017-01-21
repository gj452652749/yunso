package com.zkyunso.search.bean;

import com.fasterxml.jackson.annotation.JsonRootName;

public class SchemaBean {
	public String name;// solr对应列名
	public String type;// 分词器类型
	public String stored = "true";
	public String indexed = "true";
	boolean isStore=true;
	boolean isIndex=true;
	public SchemaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SchemaBean(String name, String type, String stored,String indexed) {
		super();
		this.name = name;
		this.type = type;
		this.stored = stored;
		this.indexed=indexed;
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

	

	public String getStored() {
		return stored;
	}

	public void setStored(String stored) {
		this.stored = stored;
	}

	public String getIndexed() {
		return indexed;
	}

	public void setIndexed(String indexed) {
		this.indexed = indexed;
	}
	
	public boolean isStore() {
		return this.stored.equals("true")?true:false;
	}

	public boolean isIndex() {
		return this.indexed.equals("true")?true:false;
	}

	/**
	 * 转成solr restful 请求的json格式
	 */
	public String toJson() {
		return "{\"name\":\""+this.name+"\",\"stored\":"+this.isStore()+",\"indexed\":"+this.isIndex()+",\"type\":\""+this.type+"\"}";
	}
}
