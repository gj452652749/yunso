package com.zkyunso.db.mybatis.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.zkyunso.db.mybatis.dao.DsDetailsMapper;
/**
 * 
 * @author Administrator
 * test url:http://localhost:8080/zkyunso/dbrest/dbrest
 */
@Path("/dbrest")  
public class DBRest {
	@Autowired
	private DsDetailsMapper dsDetailsMapper;
	/**
	 * 测试用
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
	@POST
	@Path("/updateInfo")  
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateInfo() {// 2
		System.out.println("updateInfo");
		return "result";// 返回首页
	}
	@POST
	@Path("/updateDetails")  
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateDetails() {// 2
		return "result";// 返回首页
	}
}
