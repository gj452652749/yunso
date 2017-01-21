package com.zkyunso.db.mybatis.dao;

import java.util.List;

import com.view.model.ConfiguredTb;
import com.view.model.DihConf;

public interface ConfiguredTbMapper {
	void save(ConfiguredTb bean);
	int isExist(ConfiguredTb bean);
	void update(ConfiguredTb bean);
	void delete(ConfiguredTb bean);
	List<ConfiguredTb> getTb(ConfiguredTb bean);
	List<ConfiguredTb> getTbsByDs(ConfiguredTb bean);
	List<ConfiguredTb> getAll();
}
