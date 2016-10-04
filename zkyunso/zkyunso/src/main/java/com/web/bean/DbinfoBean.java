package com.web.bean;

/**
 * 页面传递过来的数据库配置实体 进而转换成DBProoerty这个实体类，进行数据库操作。
 * 
 * @author gaojun
 *
 */
public class DbinfoBean {
	String ip; // 数据库IP地址
	int port; // 数据库端口号
	String username; // 数据库用户名
	String password; // 数据库密码
	String db; // 数据库
	String coding; // 数据库字符编码

	public DbinfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DbinfoBean(String ip, int port, String username, String password,
			String db, String coding) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.db = db;
		this.coding = coding;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

}
