package com.zkyunso.search.stmt;

public class SearchStmt {
	String qPara;
	String fqPara;
	String extraPara;
	String mode=SearchModeEnum.CUSTOM_MODE.getName();
	public String getqPara() {
		return qPara;
	}
	public void setqPara(String qPara) {
		this.qPara = qPara;
	}
	public String getFqPara() {
		return fqPara;
	}
	public void setFqPara(String fqPara) {
		this.fqPara = fqPara;
	}
	public String getExtraPara() {
		return extraPara;
	}
	public void setExtraPara(String extraPara) {
		this.extraPara = extraPara;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
