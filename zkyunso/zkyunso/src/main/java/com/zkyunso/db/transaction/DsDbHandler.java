package com.zkyunso.db.transaction;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.view.model.DsDetails;
import com.view.model.DsInfo;
import com.zkyunso.db.mybatis.dao.DsDetailsMapper;
import com.zkyunso.db.mybatis.dao.DsInfoMapper;

public class DsDbHandler {
	@Autowired
	private DsDetailsMapper dsDetailsMapper;
	@Autowired
	private DsInfoMapper dsInfoMapper;
	/**
	 * 获得datasource.jsp初始化所需的数据，并封装成json
	 */
	public String getIniDsPageData() {
		JSONObject result=new JSONObject();
		List<DsDetails> dsDetailsBeans=dsDetailsMapper.getAll();
		//List<DsInfo> dsInfoBeans=dsInfoMapper.getAll();
		result.put("details", dsDetailsBeans);
		//result.put("infos", dsInfoBeans);
		System.out.println(result.toString());
		return result.toString();
	}
	public void addDetail(DsDetails ds) {
		dsDetailsMapper.save(ds);
		System.out.println("updateInfo");
	}
	public void updateDetail(DsDetails ds) {
		dsDetailsMapper.update(ds);
		System.out.println("updateInfo");
	}
}
