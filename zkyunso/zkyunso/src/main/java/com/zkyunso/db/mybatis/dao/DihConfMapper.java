package com.zkyunso.db.mybatis.dao;

import java.util.List;

import com.view.model.DihConf;

public interface DihConfMapper {
	void save(DihConf bean);
	void update(DihConf bean);
	void delete(int id);
	DihConf getById(int id);
	DihConf getByTb(int dsId,String tbName);
	List<DihConf> getAll();
}
