package com.zkyunso.mongo.service;

import org.junit.Test;

public class MongoTest {
	@Test
	public void put() {
		MongoHandler.getInstance().put("tbConf", "gj_core2", "{json4}");
	}

}
