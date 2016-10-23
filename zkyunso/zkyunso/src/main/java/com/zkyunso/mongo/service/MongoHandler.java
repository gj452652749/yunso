package com.zkyunso.mongo.service;

import org.bson.Document;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class MongoHandler {
	private static MongoHandler mongoHandler;
	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;
	private MongoHandler() {
		
	}
	public static MongoHandler getInstance() {
		if(null==mongoHandler) {
			synchronized (MongoHandler.class) {
				if(null==mongoHandler) {
					try{   
						// 连接到 mongodb 服务
						mongoClient = new MongoClient("localhost",
								27017);

						// 连接到数据库
						mongoDatabase = mongoClient
								.getDatabase("yunso");
						System.out.println("Connect to database successfully");

					} catch (Exception e) {
						System.err.println(e.getClass().getName() + ": "
								+ e.getMessage());
					}
				}
			}
		}
		return mongoHandler;
	}
	public void put(String json,String collName) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collName);
		Document doc = Document.parse(json);
		collection.insertOne(doc);
		
	}
}
