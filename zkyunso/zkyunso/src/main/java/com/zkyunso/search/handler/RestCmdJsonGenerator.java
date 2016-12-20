package com.zkyunso.search.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zkyunso.search.bean.SchemaBean;

/**
 * 生成各种命令json
 * @author Administrator
 *
 */
public class RestCmdJsonGenerator {
	static ObjectMapper objectMapper = new ObjectMapper(); 
	public static String AddFieldjson="";
	/**
	 * 
	 * @param bean
	 * @return like {"add-field":{"name":"title","stored":true,"type":"ik"}}
	 */
	public static String generateAddFieldJson(SchemaBean bean) {
		JSONObject jsonObj=new JSONObject(); 
		jsonObj.put("add-field", bean.toJson());
		return jsonObj.toString();
	}
}
