package com.zkyunso.mongo.service;

import org.bson.BSON;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.util.JSON;

public class MongoHandler {
	private static MongoHandler mongoHandler;
	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;
	private MongoHandler() {
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
	public static MongoHandler getInstance() {
		if(null==mongoHandler) {
			synchronized (MongoHandler.class) {
				if(null==mongoHandler) {
					mongoHandler= new MongoHandler();
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
	/**
	 * 获取 对应 key 的value，此处将mongodb当做键值对nosql用
	 * @param key
	 * @param collName
	 * @return
	 */
	public String get(String collName,String key) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collName);
		Document doc = collection.find
				(new BasicDBObject ("key",key)).first();
		if(null!=doc)
			return doc.getString("value");  
		else return null;
	}
	public String getAll(String collName,BasicDBObject stmt) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collName);
		FindIterable<Document> doc = collection.find(stmt);  
		return null;
	}
	public String getAll(String collName,String stmtJson) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collName);
		FindIterable<Document> findIterable  = collection.find( Document.parse(stmtJson));  
		 MongoCursor<Document> mongoCursor = findIterable.iterator();
		 while(mongoCursor.hasNext()){  
            System.out.println(mongoCursor.next());  
         } 
		return null;
	}
	public void put(String collName,String key,String value) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collName);
		Document doc = new Document("key", key).  
		         append("value", value);
		//collection.insertOne(doc);	
		UpdateOptions updateOptions=new UpdateOptions();
		updateOptions.upsert(true);//存在则更新
		collection.updateOne(Filters.eq("key", key), new Document("$set",doc), updateOptions);
	}
}
