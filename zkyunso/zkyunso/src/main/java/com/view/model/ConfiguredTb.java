package com.view.model;

public class ConfiguredTb {
	int usrId=0;
	int dsId;
	String tbName;
	
	public ConfiguredTb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConfiguredTb(int usrId, int dsId, String tbName) {
		super();
		this.usrId = usrId;
		this.dsId = dsId;
		this.tbName = tbName;
	}
	public int getUsrId() {
		return usrId;
	}
	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}
	public int getDsId() {
		return dsId;
	}
	public void setDsId(int dsId) {
		this.dsId = dsId;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}

}
