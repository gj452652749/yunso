package com.zkyunso.db.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.view.easyui.DataFormatter;
import com.zkyunso.db.utils.DBConnection;
import com.zkyunso.db.utils.DBProperty;

public class MysqlInfoHandler implements DbInfoHandler {
	@Override
	public List<TableField> getTableInfo(DBProperty dbProperty, String tableName)
			throws SQLException {
		// TODO Auto-generated method stub
		return DBConnection.getAllColumn(
				DBConnection.getConnection(dbProperty), tableName);
	}

	@Override
	public String info2Json(List<TableField> fields) {
		// TODO Auto-generated method stub
		for (TableField ele : fields) {
			ele.setName(ele.getColumn());
			ele.setType("string");
		}
		String json = DataFormatter.list2mapjson(fields);
		return json.toString();
	}

	@Override
	public String getColJson(String driver, String url, String userName,
			String password, String tableName) {
		List<TableField> list = null;
		// TODO Auto-generated method stub
		DBProperty dbProperty = new DBProperty(driver, url, userName, password);
		try {
			list = getTableInfo(dbProperty, tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == list)
			return null;
		return info2Json(list);
	}

	@Override
	public String getDbs(DBProperty dbProperty) {
		// TODO Auto-generated method stub
		//Connection connection = DBConnection.getConnection(dbProperty);
		String eles="";
		List<String> list = DBConnection.getDBs(DBConnection.getConnection(dbProperty));
		for(String str:list) eles=eles+str+" ";
		return eles;
		//return DataFormatter.list2mapjson(list);
	}

	@Override
	public String getTbs(DBProperty dbProperty, String dbName) {
		// TODO Auto-generated method stub
		String eles="";
		String url = dbProperty.getUrl()+"/"+dbName;
		dbProperty.setUrl(url);
		List<String> list = DBConnection.getTBs(DBConnection.getConnection(dbProperty));
		for(String str:list) eles=eles+str+" ";
		return eles;
		//return DataFormatter.list2mapjson(list);
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
		 "jdbc:mysql://113.10.157.185:3306", "root", "12345678");
		MysqlInfoHandler mysqlInfoHandler = new MysqlInfoHandler();
		System.out.println(mysqlInfoHandler.getDbs(dbProperty));
		System.out.println(mysqlInfoHandler.getTbs(dbProperty, "mydb"));
		/*mysqlInfoHandler.getColJson("com.mysql.jdbc.Driver",
				"jdbc:mysql://113.10.157.185:3306/mydb", "root", "12345678",
				"t_test01");*/
		List<TableField> lTableFields =
		 mysqlInfoHandler.getTableInfo(dbProperty, "t_test01");
		 for(TableField t:lTableFields){
		 System.out.println("列名:"+t.getColumn()+"----是否主键:"+t.isPri());
		 }
	}

}
