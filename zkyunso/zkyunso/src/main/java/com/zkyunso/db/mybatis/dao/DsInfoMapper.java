package com.zkyunso.db.mybatis.dao;

import java.util.List;

import com.view.model.DsInfo;

public interface DsInfoMapper {
	void save(DsInfo bean);
	void update(DsInfo bean);
	void delete(int id);
	DsInfo getById(int id);
	List<DsInfo> getAll();
}
