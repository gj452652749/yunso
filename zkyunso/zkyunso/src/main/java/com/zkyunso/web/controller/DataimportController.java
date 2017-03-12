package com.zkyunso.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tool.xml.XmlHandler;
import com.view.easyui.DataFormatter;
import com.view.model.ConfiguredTb;
import com.view.model.DsDetails;
import com.web.bean.DbinfoBean;
import com.zkyunso.db.handler.DbInfoHandler;
import com.zkyunso.db.handler.MysqlInfoHandler;
import com.zkyunso.db.handler.TableField;
import com.zkyunso.db.transaction.DsDbHandler;
import com.zkyunso.db.utils.DBProperty;
import com.zkyunso.mongo.service.MongoHandler;
import com.zkyunso.search.bean.SchemaBean;
import com.zkyunso.search.handler.ConfHandler;
import com.zkyunso.search.handler.ConfHandler.DataConfHandler;
import com.zkyunso.search.handler.ConfHandler.SchemaHandler;
import com.zkyunso.sys.SysUtil;

@Controller
@RequestMapping("/dataimport")
public class DataimportController {
	DbInfoHandler dbHandler;
	@Autowired
	private DsDbHandler dsDbHandler;
	/*
	 * data-conf.xml操作类
	 */
	XmlHandler xmlHandler = new XmlHandler();
	/*
	 * schema.xml操作类
	 */
	ConfHandler confHandler = new ConfHandler();
	SchemaHandler schemaHandler =confHandler.new SchemaHandler();
	DataConfHandler dataConfHandler=confHandler.new DataConfHandler();
	/*
	 * 获取数据源下所有数据库
	 */
	@ResponseBody
	@RequestMapping(value = "/getDbs", produces = "text/plain;charset=UTF-8")
	public String getDbs(DbinfoBean dbinfo,HttpServletResponse response) {// 2
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.print(dbinfo.getIp());
		String url="jdbc:mysql://"+dbinfo.getIp()+":"+dbinfo.getPort();
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
				url, dbinfo.getUsername(), dbinfo.getPassword());
				DbInfoHandler dbInfoHandler = new MysqlInfoHandler();
				String dbs=dbInfoHandler.getDbs(dbProperty);
				System.out.println(dbInfoHandler.getDbs(dbProperty));
		return dbs;// 返回首页
	}
	/*
	 * 获取数据库下所有表名
	 */
	@ResponseBody
	@RequestMapping(value = "/getTbs", produces = "text/plain;charset=UTF-8")
	public String getTbs(DbinfoBean dbinfo,HttpServletResponse response) {// 2
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.print(dbinfo.getIp());
		String url="jdbc:mysql://"+dbinfo.getIp()+":"+dbinfo.getPort();
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
				url, dbinfo.getUsername(), dbinfo.getPassword());
		DbInfoHandler dbInfoHandler = new MysqlInfoHandler();
		String tbs=dbInfoHandler.getTbs(dbProperty,dbinfo.getDb());
		return tbs;// 返回首页
	}
	/**
	 * 获取对应dsId下所有已配置的表名
	 */
	@ResponseBody
	@RequestMapping(value = "/getConfiguredTbs")
	public String getConfiguredTbs(Integer dsId,HttpServletResponse response) {// 2
		response.addHeader("Access-Control-Allow-Origin", "*");
		String tbs=dsDbHandler.getConfiguredTbs(dsId);
		return tbs;// 返回首页
	}
	/*
	 * 获取表字段信息
	 */
	@ResponseBody
	@RequestMapping(value = "/tbinfo", produces = "text/plain;charset=UTF-8")
	public String tbinfo(DbinfoBean dbinfo, String tbName,HttpServletResponse response) {// 2
		response.addHeader("Access-Control-Allow-Origin", "*");
		dbHandler = new MysqlInfoHandler();
		String url="jdbc:mysql://"+dbinfo.getIp()+":"+dbinfo.getPort()+"/"+dbinfo.getDb();
		String json = dbHandler.getColJson("com.mysql.jdbc.Driver",
				url, dbinfo.getUsername(), dbinfo.getPassword(),
				tbName);
		System.out.println(dbinfo.getIp() + "：" + tbName);
		return json;// 返回首页
	}
	/*
	 * 获取用户自定义field信息
	 */
	@ResponseBody
	@RequestMapping(value = "/name/{id}", produces = "text/plain;charset=UTF-8")
	public String name(@PathVariable("id") Integer id) {// 2
		String src = SysUtil.confRootDir + id + "\\name.json";
		String jsonStr = SysUtil.readFromFile(src);
		return jsonStr;// 返回首页
	}

	/*
	 * 获取用户分词器信息
	 */
	@ResponseBody
	@RequestMapping(value = "/tokenizer/{id}", produces = "text/plain;charset=UTF-8")
	public String tokenizer(@PathVariable("id") Integer id,HttpServletResponse response) {// 2
		response.addHeader("Access-Control-Allow-Origin", "*");
		String src = SysUtil.confRootDir + id + "\\tokenizer.json";
		String jsonStr = SysUtil.readFromFile(src);
		System.out.println(jsonStr);
		return jsonStr;// 返回首页
	}
	/*
	 * 获取表配置信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getTbConf", produces = "text/plain;charset=UTF-8")
	public String getTbConf(String userName,
			String coreName,String tbName) {// 2
		String key=userName+"_"+coreName+"_"+tbName;
		String jsonStr=MongoHandler.getInstance().get("tbConf",key);
		return jsonStr;// 返回首页
	}
	/*
	 * 更新表配置信息
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTbConf/{id}", produces = "text/plain;charset=UTF-8")
	public String updateTbConf(@PathVariable("id") Integer id,String userName,
			String coreName,String rows,String dsJson) {// 2
		DsDetails ds =(DsDetails) JSONObject.toBean(JSONObject.fromObject(dsJson),DsDetails.class);
		String src = SysUtil.confRootDir + id + "\\tb.json";
		String jsonStr = SysUtil.readFromFile(src);
		JSONArray jsonArr=JSONArray.fromObject(rows);
		List<TableField> list=(List<TableField>)JSONArray.toCollection(jsonArr, TableField.class);
		String tokenizerJson=DataFormatter.list2mapjson(list);
		SysUtil.stringToTxt(tokenizerJson, src);
		System.out.println(rows);
		System.out.println(list.size());
		/**
		 * 更新tbConf、对应schema和dihconf
		 */
		String core=userName+"_"+coreName;
		//String key=userName+"_"+coreName+"_"+ds.getDefaultTb();
		//String key=userName+"_"+coreName+"_"+ds.getId()+"_"+ds.getDefaultTb();
		String key=ds.getId()+"_"+ds.getDefaultTb();
		String url=ConfHandler.ConfContext.SOLR_HOME+"/"+core+"/schema?commit=true";
		String dir=(ConfHandler.ConfContext.SOLR_HOME_DIR).replace("core1", core);
		dsDbHandler.putConfiguredTb(new ConfiguredTb(0,ds.getId(),ds.getDefaultTb()));
		MongoHandler.getInstance().put("tbConf", key,rows);
		schemaHandler.addFields(list, url);
		xmlHandler.addTb(dir, 
				list, ds);
		return jsonStr;// 返回首页
	}
	/*
	 * 更新表配置信息
	 */
	@ResponseBody
	@RequestMapping(value = "/updatetb/{id}", produces = "text/plain;charset=UTF-8")
	public String updateTb(@PathVariable("id") Integer id,String rows,DbinfoBean dbinfo, String tbName) {// 2
		String src = SysUtil.confRootDir + id + "\\tb.json";
		String jsonStr = SysUtil.readFromFile(src);
		JSONArray jsonArr=JSONArray.fromObject(rows);
		List<TableField> list=JSONArray.toList(jsonArr);
		String tokenizerJson=DataFormatter.list2mapjson(list);
		SysUtil.stringToTxt(tokenizerJson, src);
		System.out.println(rows);
		System.out.println(list.size());
		return jsonStr;// 返回首页
	}
	/**
	 * 根据数据源、表名、tbJson生成dih配置
	 * @param ds
	 * @param tbName
	 * @param tbJson
	 */
	@ResponseBody
	@RequestMapping(value = "/getDihConf")
	public String getDIHConf(int dsId,String tbName) {
		if(StringUtils.isEmpty(tbName)) 
			return null;
		String key=dsId+"_"+tbName;
		String json=MongoHandler.getInstance().get("tbConf", key);
		//如果没有，则生成默认的配置
		if(null==json)
			json=dsDbHandler.generateDefaultfTbConf(dsId, tbName);
		System.out.println("dihJSON:"+json);
		return json;
	}
	@ResponseBody
	@RequestMapping(value = "/doDIH")
	public String doDIH(final String userName,final String coreName,final int dsId,final String tbName) {
		if(StringUtils.isEmpty(tbName)) 
			return null;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DsDetails ds=dsDbHandler.getDsDetails(dsId);
				String entityName=ds.getName()+"_"+tbName;
				dataConfHandler.doDih(userName,coreName,entityName,"entity");
				
			}
		}).start();		
		return "dih begin";
	}
	/*
	 * 更新数据库配置
	 */
	public void updateDbconf(String src,List<TableField> list,DbinfoBean dbinfo, String tbName) {
		String url="jdbc:mysql://"+dbinfo.getIp()+":"+dbinfo.getPort();
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
				url, dbinfo.getUsername(), dbinfo.getPassword());
		xmlHandler.addTb(src, list, dbinfo, tbName);
	}
	/*
	 * 更新schema配置
	 */
	public void updateSchemaconf(List<TableField> listIn) {
		List<SchemaBean> list=new ArrayList<SchemaBean>();
		list.addAll(listIn);
		String json=DataFormatter.list2mapjson(list);
		confHandler.addFiledJson(json);
	}
}
