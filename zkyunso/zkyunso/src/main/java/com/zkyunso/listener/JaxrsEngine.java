package com.zkyunso.listener;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.zkyunso.db.mybatis.dao.DsInfoMapper;


public class JaxrsEngine implements ServletContextListener{
	private static final URI BASE_URI = URI.create("http://localhost:8081/base/");
    public static final String ROOT_PATH = "helloworld";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
		            System.out.println("\"Hello World\" Jersey Example App");

		            final ResourceConfig resourceConfig = new ResourceConfig(DsInfoMapper.class);
		            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
		            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    server.shutdownNow();
		                }
		            }));
		            server.start();

		            System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
		                    BASE_URI, ROOT_PATH));
		            Thread.currentThread().join();
		        } catch (IOException | InterruptedException ex) {
		            Logger.getLogger(JaxrsEngine.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		}).start();
		 

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
