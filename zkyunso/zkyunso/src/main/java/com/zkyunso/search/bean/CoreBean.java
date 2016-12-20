package com.zkyunso.search.bean;
/**
 * 存储请求的core信息
 * @author Administrator
 *
 */
public class CoreBean {
	String userId;
	String coreName;
	String coreUrl;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCoreName() {
		return coreName;
	}
	public void setCoreName(String coreName) {
		this.coreName = coreName;
	}
	public String getCoreUrl() {
		return coreUrl;
	}
	public void setCoreUrl(String coreUrl) {
		this.coreUrl = coreUrl;
	}

}
