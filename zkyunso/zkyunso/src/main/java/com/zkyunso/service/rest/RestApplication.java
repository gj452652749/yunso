package com.zkyunso.service.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig{
	 public RestApplication() {  
		  
	        packages("com.zkyunso.db.service.rest");  
	        System.out.println("now!");
	    }  
}
