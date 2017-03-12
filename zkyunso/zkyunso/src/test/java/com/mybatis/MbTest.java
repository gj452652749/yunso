package com.mybatis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.view.model.ConfiguredTb;
import com.view.model.DsDetails;
import com.zkyunso.db.mybatis.dao.ConfiguredTbMapper;
import com.zkyunso.db.mybatis.dao.DsDetailsMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class MbTest {
	@Autowired
	private DsDetailsMapper dsDetailsMapper;
	@Autowired
	ConfiguredTbMapper configuredTbMapper;
	@Test
	public void save() {
		DsDetails bean=new DsDetails();
		bean.setName("gj");
		bean.setRemark("just a test");
		//bean.setDbType(1);
		bean.setDbUrl("");
		dsDetailsMapper.save(bean);
	}
	@Test
	public void update() {
		DsDetails bean=dsDetailsMapper.getById(1);
		bean.setName("gj-2");
		dsDetailsMapper.update(bean);
	}
	@Test
	public void delete() {
		dsDetailsMapper.delete(1);
	}
	@Test
	public void findById() {
		DsDetails bean=dsDetailsMapper.getById(1);
		System.out.println(bean.getName());
	}
	@Test
	public void findAll() {
		List<DsDetails> beans=dsDetailsMapper.getAll();
		System.out.println(beans.size());
	}
	@Test
	public void testDsDetailsMapper() {
		ConfiguredTb bean=new ConfiguredTb();
		bean.setDsId(1);
		bean.setUsrId(0);
		bean.setTbName("sda");
//		configuredTbMapper.save(bean);
		configuredTbMapper.delete(bean);
		List<ConfiguredTb> beans= configuredTbMapper.getTbsByDs(bean);
		for(ConfiguredTb ele:beans)
			System.out.println(ele.getTbName());
	}
	@Test
	public void isExist() {
		ConfiguredTb bean=new ConfiguredTb();
		bean.setDsId(1);
		bean.setUsrId(0);
		bean.setTbName("sda");
		int count= configuredTbMapper.isExist(bean);
		System.out.println(count);
	}
}
