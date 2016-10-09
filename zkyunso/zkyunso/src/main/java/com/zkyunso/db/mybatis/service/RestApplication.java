package com.zkyunso.db.mybatis.service;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig{
	 public RestApplication() {  
		  
	        packages("com.zkyunso.db.mybatis.service");  
	        System.out.println("now!");
	    }  
}
