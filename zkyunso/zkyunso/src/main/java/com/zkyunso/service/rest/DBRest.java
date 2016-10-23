package com.zkyunso.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.view.model.DsDetails;
import com.zkyunso.db.mybatis.dao.DsDetailsMapper;
/**
 * 
 * @author Administrator
 * test url:http://localhost:8080/zkyunso/dbrest
 */
@Path("")  
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
	@Path("/add")  
	//@Consumes("application/json") 
	public String add(DsDetails ds) {// 2
//		System.out.println(detail);
//		DsDetails bean=(DsDetails) JSONObject.toBean(JSONObject.fromObject(detail), DsDetails.class);
		dsDetailsMapper.save(ds);
		System.out.println("updateInfo:"+ds.getName());
		return "result";// 返回首页
	}
//	@POST
//	@Path("/put")  
//	@Produces(MediaType.TEXT_PLAIN) 
//	public String put(String info,String details) {// 2
//		MongoHandler.getInstance().put(info, "dbinfo");
//		MongoHandler.getInstance().put(details, "dbdetail");
//		System.out.println("updateInfo");
//		return "result";// 返回首页
//	}
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
