package com.zkyunso.search.stmt;

public enum SearchModeEnum {
	CUSTOM_MODE("custom"),
	POSITIVE_SEQ_MODE("positive_seq"),
	OFFSET_MODE("offset");
	private String name;

	private SearchModeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
