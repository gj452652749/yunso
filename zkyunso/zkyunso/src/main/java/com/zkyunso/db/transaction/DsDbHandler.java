package com.zkyunso.db.transaction;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.view.model.ConfiguredTb;
import com.view.model.DihConf;
import com.view.model.DsDetails;
import com.view.model.DsInfo;
import com.zkyunso.db.handler.DbInfoHandler;
import com.zkyunso.db.handler.MysqlInfoHandler;
import com.zkyunso.db.mybatis.dao.ConfiguredTbMapper;
import com.zkyunso.db.mybatis.dao.DihConfMapper;
import com.zkyunso.db.mybatis.dao.DsDetailsMapper;
import com.zkyunso.db.mybatis.dao.DsInfoMapper;

public class DsDbHandler {
	@Autowired
	private DsDetailsMapper dsDetailsMapper;
	@Autowired
	private DsInfoMapper dsInfoMapper;
	@Autowired
	private DihConfMapper dihConfMapper;
	@Autowired
	ConfiguredTbMapper configuredTbMapper;
	/**
	 * 获得datasource.jsp初始化所需的数据，并封装成json
	 */
	public String getIniDsPageData() {
		JSONObject result=new JSONObject();
		List<DsDetails> dsDetailsBeans=dsDetailsMapper.getAll();
		//dsDetailsBeans=dsDetailsBeans.subList(0, 1);
		//List<DsInfo> dsInfoBeans=dsInfoMapper.getAll();
		result.put("details", dsDetailsBeans);
		//result.put("infos", dsInfoBeans);
		System.out.println(result.toString());
		return result.toString();
	}
	public void addDetail(DsDetails ds) {
		String url="jdbc:mysql://"+ds.getServerIp()+":"+ds.getServerPort()+"/"+ds.getDbName();
		ds.setDbUrl(url);
		dsDetailsMapper.save(ds);
		System.out.println("updateInfo");
	}
	public void deleteDs(int dsId) {
		dsDetailsMapper.delete(dsId);
		System.out.println("delete ds");
	}
	public void updateDetail(DsDetails ds) {
		String url="jdbc:mysql://"+ds.getServerIp()+":"+ds.getServerPort()+"/"+ds.getDbName();
		ds.setDbUrl(url);
		dsDetailsMapper.update(ds);
		System.out.println("updateInfo");
	}
	public String array2jsonArray(String arrayStr) {
		JSONArray jsonArray=new JSONArray();
		for(String ele:arrayStr.split(" ")) {
			jsonArray.add(ele);
		}
		return jsonArray.toString();
	}
	public DsDetails getDsDetails(int dsId) {
		DsDetails ds=dsDetailsMapper.getById(dsId);
		return ds;
	}
	public void putDihConf(DihConf bean) {
		if(0==bean.getId())
			dihConfMapper.save(bean);
		else dihConfMapper.update(bean);
	}
	public void addDihConf(DihConf bean) {
		dihConfMapper.save(bean);
	}
	public void updateDihConf(DihConf bean) {
		dihConfMapper.update(bean);
	}
	public String generateDefaultfTbConf(int dsId,String tbName) {
		DsDetails ds=dsDetailsMapper.getById(dsId);
			DbInfoHandler dbHandler=new MysqlInfoHandler();
			String json = dbHandler.getColJson("com.mysql.jdbc.Driver",
					ds.getDbUrl(), ds.getServerUsrname(), ds.getServerPsword(),
					tbName);
			return json;
	}
	public String getDihConf(int dsId,String tbName) {
		DsDetails ds=dsDetailsMapper.getById(dsId);
		DihConf bean=dihConfMapper.getByTb(ds.getId(), tbName);
		if(null==bean) {
			DbInfoHandler dbHandler=new MysqlInfoHandler();
			String json = dbHandler.getColJson("com.mysql.jdbc.Driver",
					ds.getDbUrl(), ds.getServerUsrname(), ds.getServerPsword(),
					tbName);
//			String url="jdbc:mysql://127.0.0.1:3306/spring";
//			String json = dbHandler.getColJson("com.mysql.jdbc.Driver",
//					url, "root", "22331144",
//					"book");
			return json;
		}
		return bean.getDihjson();
	}
	public void putConfiguredTb(ConfiguredTb bean) {
		int count=configuredTbMapper.isExist(bean);
		if(0==count)
			configuredTbMapper.save(bean);
	}
	public String getConfiguredTbs(int dsId) {
		JSONArray jsonArray=new JSONArray();
		ConfiguredTb bean=new ConfiguredTb();
		bean.setDsId(dsId);
		List<ConfiguredTb> beans= configuredTbMapper.getTbsByDs(bean);
		for(ConfiguredTb ele:beans) {
			System.out.println(ele.getTbName());
			jsonArray.add(ele.getTbName());
		}
		return jsonArray.toString();
	}
}
