package com.zkyunso.db.handler;

import com.zkyunso.search.bean.SchemaBean;

public class TableField extends SchemaBean {
	boolean isPri = false;// 是否为主键
	String column;// db列名

	public TableField() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TableField(boolean isPri, String column, String name, String type) {
		super();
		this.isPri = isPri;
		this.column = column;
		this.name = name;
		this.type = type;
	}

	public boolean isPri() {
		return isPri;
	}

	public void setPri(boolean isPri) {
		this.isPri = isPri;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "TableField [isPri=" + isPri + ", column=" + column + ", name="
				+ name + ", type=" + type + "]";
	}

}
