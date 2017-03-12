package com.zkyunso.search.handler;

import java.io.IOException;

import org.junit.Test;

import com.zkyunso.search.bean.SchemaBean;

public class RestCmdJsonGeneratorTest {

	@Test
	public void testGenerateAddField() {
		SchemaBean bean=new SchemaBean("title","ik","true","true");
		String json;
		json=RestCmdJsonGenerator.generateAddFieldJson(bean);
		System.out.println(json);
		
	}
}
