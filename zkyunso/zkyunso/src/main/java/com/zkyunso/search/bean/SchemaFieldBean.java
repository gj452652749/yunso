package com.zkyunso.search.bean;

public class SchemaFieldBean {
	public String name;// solr对应列名
	public String type;// 分词器类型
	public boolean stored = true;
	public String url="http://localhost:8090/solr/core1/schema?commit=true";
	public SchemaFieldBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SchemaFieldBean(String name, String type, boolean stored) {
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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toAddFieldjson() {
		return "";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return super.toString();
	}
	
}
