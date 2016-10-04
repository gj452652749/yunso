package com.zkyunso.db.utils;

/**
 * 数据库连接属性实体
 * 
 * @author nanhai
 *
 */
public class DBProperty {

	private String driver; // 驱动名
	private String url; // 数据库连接地址
	private String userName; // 数据库用户名
	private String password; // 数据库密码

	public DBProperty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DBProperty(String driver, String url, String userName,
			String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DBProperty [driver=" + driver + ", url=" + url + ", userName="
				+ userName + ", password=" + password + "]";
	};

}
