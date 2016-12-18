package com.zkyunso.search.handler;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tool.xml.XmlHandler;
import com.view.model.DsDetails;
import com.zkyunso.db.handler.TableField;
import com.zkyunso.search.bean.SchemaBean;
import com.zkyunso.search.handler.ConfHandler.DataConfHandler;
import com.zkyunso.search.handler.ConfHandler.SchemaHandler;

public class ConfHandlerTest {
	ConfHandler confhandler=new ConfHandler();
	SchemaHandler schemaHandler =confhandler.new SchemaHandler();
	DataConfHandler dataConfHandler=confhandler.new DataConfHandler();
	@Test
	public void addField() {
		SchemaBean bean=new SchemaBean("titleik1","text_ik",true);
		schemaHandler.addField(bean,"http://localhost:8090/solr/core1/schema?commit=true");
	}
	/**ds+fileds——>dih配置
	 * 生成dih配置
	 */
	@Test
	public void addDihTbConf() {
		XmlHandler xmlHandler = new XmlHandler();
		DsDetails ds =new DsDetails();
		ds.setName("ds2");
		ds.setDbName("mark");
		ds.setEncode("");
		ds.setServerIp("127.0.0.1");
		ds.setServerPort(3306);
		ds.setServerUsrname("root");
		ds.setServerPsword("22331144");
		ds.setDefaultTb("marker");
		List<TableField> list =new ArrayList<TableField>();
		list.add(new TableField(false,"name","name","text_general"));
		list.add(new TableField(false,"title","title","text_general"));
		xmlHandler.addTb("E:\\workplace\\svn\\zkyunso\\docs\\data-config1.xml", 
				list, ds);
	}
	/**ds+fileds——>dih配置
	 * 生成dih配置
	 * 执行dih
	 */
	@Test
	public void doDih() {
		XmlHandler xmlHandler = new XmlHandler();
		DsDetails ds =new DsDetails();
		ds.setName("ds2");
		ds.setDbName("mark");
		ds.setEncode("");
		ds.setServerIp("127.0.0.1");
		ds.setServerPort(3306);
		ds.setServerUsrname("root");
		ds.setServerPsword("22331144");
		ds.setDefaultTb("marker");
		List<TableField> list =new ArrayList<TableField>();
		list.add(new TableField(false,"name","name","text_general"));
		list.add(new TableField(false,"title","title","text_general"));
		xmlHandler.addTb("E:\\workplace\\svn\\zkyunso\\docs\\data-config1.xml", 
				list, ds);
		dataConfHandler.doDih("full");
	}
	@Test
	public void generateDihDbConf() {
		XmlHandler xmlHandler = new XmlHandler();
		xmlHandler.addTest(
				"E:\\workplace\\svn\\zkyunso\\docs\\data-config1.xml",
				"com.mysql.jdbc.Driver",
				"jdbc:mysql://127.0.0.1:3306/mark", "root", "22331144",
				"marker");
	}
}
