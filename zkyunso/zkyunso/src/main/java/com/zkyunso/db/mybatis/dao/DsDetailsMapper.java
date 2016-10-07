package com.zkyunso.db.mybatis.dao;

import java.util.List;

import com.view.model.DsDetails;

public interface DsDetailsMapper {
	void save(DsDetails bean);
	void update(DsDetails bean);
	void delete(int id);
	DsDetails getById(int id);
	List<DsDetails> getAll();
}
