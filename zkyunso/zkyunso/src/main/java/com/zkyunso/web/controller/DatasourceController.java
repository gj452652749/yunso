package com.zkyunso.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.view.model.DsDetails;
import com.web.bean.DbinfoBean;
import com.zkyunso.db.handler.DbInfoHandler;
import com.zkyunso.db.handler.MysqlInfoHandler;
import com.zkyunso.db.transaction.DsDbHandler;
import com.zkyunso.db.utils.DBProperty;

@Controller
@RequestMapping("/datasource")
public class DatasourceController {
	@Autowired
	private DsDbHandler dsDbHandler;
	/**
	 * 初始化
	 * @param User
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/init", produces = "text/plain;charset=UTF-8")
	public String init() {// 2
		return dsDbHandler.getIniDsPageData();// 返回首页
	}
	/**
	 * 新增数据源
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add")
	public String add(@RequestBody DsDetails ds) {// 2
		dsDbHandler.addDetail(ds);
		System.out.println("updateInfo:"+ds.getName());
		return dsDbHandler.getIniDsPageData();// 返回首页
	}
	@ResponseBody
	@RequestMapping(value = "/update/dsInfo", produces = "text/plain;charset=UTF-8")
	public String updateInfo() {// 2
		return "result";// 返回首页
	}
	@ResponseBody
	@RequestMapping(value = "/update/dsDetails")
	public String updateDetails(@RequestBody DsDetails ds) {// 2
		dsDbHandler.updateDetail(ds);
		System.out.println("updateInfo:"+ds.getName());
		return dsDbHandler.getIniDsPageData();// 返回首页
	}
	@ResponseBody
	@RequestMapping(value = "/getDbs")
	public String getDbs(DsDetails ds) {// 2
		System.out.print(ds.getServerIp());
		String url="jdbc:mysql://"+ds.getServerIp()+":"+ds.getServerPort();
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
				url, ds.getServerUsrname(), ds.getServerPsword());
				DbInfoHandler dbInfoHandler = new MysqlInfoHandler();
				String dbs=dbInfoHandler.getDbs(dbProperty);
				System.out.println(dbInfoHandler.getDbs(dbProperty));
		return dsDbHandler.array2jsonArray(dbs);// 返回首页
	}
	@ResponseBody
	@RequestMapping(value = "/getTbs")
	public String getTbs(DsDetails ds) {// 2
		String url="jdbc:mysql://"+ds.getServerIp()+":"+ds.getServerPort();
		DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
				url, ds.getServerUsrname(), ds.getServerPsword());
				DbInfoHandler dbInfoHandler = new MysqlInfoHandler();
				String tbs=dbInfoHandler.getTbs(dbProperty,ds.getDbName());
		return dsDbHandler.array2jsonArray(tbs);// 返回首页
	}
}
