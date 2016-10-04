package com.zkyunso.db.handler;

import java.sql.SQLException;
import java.util.List;

import com.zkyunso.db.utils.DBProperty;

public interface DbInfoHandler {
	
	/**
	 * 传入表信息（dburl、表名） 返回表字段数组
	 * @param dbProperty
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<TableField> getTableInfo(DBProperty dbProperty, String tableName)
			throws SQLException;
	
	/**
	 * 将表字段数组转成json
	 * @param fields
	 * @return
	 */
	public String info2Json(List<TableField> fields);

	/**
	 * 获取json数据
	 * @param driver
	 * @param url
	 * @param userName
	 * @param password
	 * @param tableName
	 * @return
	 */
	public String getColJson(String driver, String url, String userName,
			String password, String tableName);

	/**
	 * 返回db集合json
	 * @param dbProperty
	 * @return
	 */
	public String getDbs(DBProperty dbProperty);

	/**
	 * 返回table名集合json
	 * @param dbProperty
	 * @param dbName
	 * @return
	 */
	public String getTbs(DBProperty dbProperty, String dbName);
}
