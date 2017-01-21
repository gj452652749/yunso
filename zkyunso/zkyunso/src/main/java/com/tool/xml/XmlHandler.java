package com.tool.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.view.model.DsDetails;
import com.web.bean.DbinfoBean;
import com.zkyunso.db.handler.TableField;
import com.zkyunso.db.utils.DBConnection;
import com.zkyunso.db.utils.DBProperty;

public class XmlHandler {
	/*
	 * 载入现有的配置文件
	 */
	static public Element readXml(String src) throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(src);
		Document document = reader.read(file);
		// 获得root元素
		Element root = document.getRootElement();
		// dataSource
		List<Element> dbSourceElements = root.elements("dataSource");
		for (Element child : dbSourceElements) {
			System.out.println("type" + child.attributeValue("type"));
			System.out.println("name" + child.attributeValue("name"));
		}
		// entity
		List<Element> docElements = root.element("document").elements("entity");
		for (Element child : docElements) {
			System.out.println("name" + child.attributeValue("name"));
			System.out.println("dataSource"
					+ child.attributeValue("dataSource"));
		}
		return root;
	}
	/*
	 * 将编辑过的配置文件写回
	 */
	static public void writeXml(Element root, String path) {
		// 实例化输出格式对象
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置输出编码
		format.setEncoding("UTF-8");
		// 创建需要写入的File对象
		//File file = new File("E:" + File.separator + "\\play\\books.xml");
		File file = new File(path);
		try {
			// 生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
			// 开始写入，write方法中包含上面创建的Document对象
			writer.write(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void deleteDataSourceOnExists(Element root,String name) {
		Node node=root.selectSingleNode("/dataConfig/dataSource[@name='"+name+"']");	
		if(null==node) return;
		System.out.println(node.asXML());
		root.remove(node); 
	}
	static public void deleteEntityOnExists(Element root,String name) {
		Node node=root.selectSingleNode("/dataConfig/document/entity[@name='"+name+"']");
		if(null==node) return;
		System.out.println(node.asXML());
		root.element("document").remove(node); 
	}
	/*
	 * 增加Entity，对应数据表
	 */
	static public Element addEntity(Element root, String dataSource,
			String tableName, List<TableField> fields) {
		deleteEntityOnExists(root, dataSource+"_"+tableName);
		Element doc = root.element("document");
		Element entity = doc.addElement("entity");
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		for (TableField ele : fields) {
			Element field = entity.addElement("field");
			field.addAttribute("column", ele.getColumn());
			field.addAttribute("name", ele.getName());
			sql.append(ele.getColumn()).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" from ").append(tableName);
		System.out.println(sql.toString());
		//dataSource_tablename唯一，所以能表征为entity的name
		entity.addAttribute("name", dataSource+"_"+tableName);
		entity.addAttribute("dataSource", dataSource);
		entity.addAttribute("pk", "ID");
		entity.addAttribute("query", sql.toString());
		// writeXml(root,"");
		return root;
	}
	/*
	 * 增加数据源，对应数据库
	 */
	static public Element addDataSource(String src,String dsName, DBProperty dbProperty,
			String tableName) {
		try {
			Element root = readXml(src);
			deleteDataSourceOnExists(root,dsName);
			Element ds = root.addElement("dataSource");
			ds.addAttribute("type", "JdbcDataSource");
			ds.addAttribute("name", dsName);
			ds.addAttribute("driver", dbProperty.getDriver());
			ds.addAttribute("url", dbProperty.getUrl());
			ds.addAttribute("user", dbProperty.getUserName());
			ds.addAttribute("password", dbProperty.getPassword());
			return root;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 添加表信息到dataconf
	 */
	@Deprecated
	public void addTb(String src,List<TableField> list,DbinfoBean dbinfo, String tbName) {
		String url="jdbc:mysql://"+dbinfo.getIp()+":"+dbinfo.getPort();
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
				url, dbinfo.getUsername(), dbinfo.getPassword());
		Element root = addDataSource(src,"ds1", dbProperty, tbName);
		root = addEntity(root,  "db1", tbName, list);
	}
	/*
	 * 添加表信息到dataconf
	 */
	public void addTb(String src,List<TableField> list,DsDetails ds) {
		String url="jdbc:mysql://"+ds.getServerIp()+":"+ds.getServerPort()+"/"+ds.getDbName()
				+"?useUnicode=true&characterEncoding=utf-8";
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
				url, ds.getServerUsrname(), ds.getServerPsword());
		Element root = addDataSource(src, ds.getName(),dbProperty, ds.getDefaultTb());
		root = addEntity(root, ds.getName(), ds.getDefaultTb(), list);
		writeXml(root, src);
	}
	/*
	 * 接口测试函数
	 */
	public void addTest(String src, String driver, String url, String userName,
			String password, String tableName) {
		DBProperty dbProperty = new DBProperty(driver, url, userName, password);
		Element root = addDataSource(src,"ds1", dbProperty, tableName);
		List<TableField> fields = null;
		try {
			fields = DBConnection.getAllColumn(
					DBConnection.getConnection(dbProperty), tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fields.size());
		root = addEntity(root,"db1", tableName, fields);
		writeXml(root, src);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XmlHandler xmlHandler = new XmlHandler();
//		xmlHandler.addTest(
//				"E:\\workplace\\svn\\zkyunso\\docs\\data-config1.xml",
//				"com.mysql.jdbc.Driver",
//				"jdbc:mysql://113.10.157.185:3306/mydb", "root", "12345678",
//				"t_test01");
		try {
			Element root=xmlHandler
					.readXml("E:\\workplace\\svn\\zkyunso\\docs\\data-config1.xml");
//			xmlHandler.deleteDataSourceOnExists(root);
//			xmlHandler.deleteDataSourceOnExists(root);
			xmlHandler.deleteEntityOnExists(root,"node1");
			//xmlHandler.deleteEntityOnExists(root,"node1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
