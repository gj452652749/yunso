package com.zkyunso.db.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;
import com.zkyunso.db.handler.TableField;

public class DBConnection {

	/**
	 * 创建连接
	 * 
	 * @param dbProperty
	 * @return
	 */
	public static Connection getConnection(DBProperty dbProperty) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName(dbProperty.getDriver());
			conn = DriverManager.getConnection(dbProperty.getUrl(),
					dbProperty.getUserName(), dbProperty.getPassword());
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取表的字段信息 组成list
	 * 
	 * @param conn
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static List<TableField> getAllColumn(Connection conn,
			String tableName) throws SQLException {
		// TODO Auto-generated method stub
		List<TableField> lTableFields = new ArrayList<TableField>();
		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet rs = metaData.getColumns(null, null, tableName, null);
		while (rs.next()) {
			TableField tableField = new TableField();
			tableField.setColumn(rs.getString("COLUMN_NAME"));
			// 判断是否主键
			if (rs.getBoolean("IS_AUTOINCREMENT")) {
				tableField.setPri(true);
			}
			lTableFields.add(tableField);
		}
		return lTableFields;
	}
	
	/**
	 * 根据连接获取所有的数据库(仅支持mysql)
	 * @param conn
	 * @return
	 */
	public static List<String> getDBs(Connection conn){
		List<String> list = new ArrayList<String>();
		try {
	        Statement st=conn.createStatement();
	        ResultSet rs=st.executeQuery("show databases" );
	        while(rs.next()) {   
	           list.add(rs.getString(1));
	        }  
	        st.close();
	        conn.close();
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		return list;
	}

	/**
	 * 根据连接获取表名(此方法通用)
	 * @param connection
	 * @return
	 * @throws SQLException 
	 */
	public static List<String> getTBs(Connection conn){
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		DatabaseMetaData metaData;
		try {
			metaData = conn.getMetaData();
			ResultSet tableRet = metaData.getTables(null, "%","%",new String[]{"TABLE","VIEW"});
			while(tableRet.next()) {
				list.add(tableRet.getString("TABLE_NAME"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
