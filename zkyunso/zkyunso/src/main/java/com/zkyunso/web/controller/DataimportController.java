package com.zkyunso.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tool.xml.XmlHandler;
import com.view.easyui.DataFormatter;
import com.web.bean.DbinfoBean;
import com.zkyunso.db.handler.DbInfoHandler;
import com.zkyunso.db.handler.MysqlInfoHandler;
import com.zkyunso.db.handler.TableField;
import com.zkyunso.db.utils.DBProperty;
import com.zkyunso.search.handler.ConfHandler;
import com.zkyunso.sys.SysUtil;

@Controller
@RequestMapping("/dataimport")
public class DataimportController {
	DbInfoHandler dbHandler;
	/*
	 * data-conf.xml操作类
	 */
	XmlHandler xmlHandler = new XmlHandler();
	/*
	 * schema.xml操作类
	 */
	ConfHandler confHandler = new ConfHandler();
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
		List<TableField> list=new ArrayList<TableField>();
		list.addAll(listIn);
		String json=DataFormatter.list2mapjson(list);
		confHandler.addFiledJson(json);
	}
}
