package com.zkyunso.search.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.zkyunso.db.handler.TableField;
import com.zkyunso.db.utils.DBProperty;
import com.zkyunso.search.bean.SchemaBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConfHandler {
	CloseableHttpClient httpClient = null;
	RestTemplate restTemplate = null;

	/*
	 * 根据前台配置的schema生成对应db
	 */
	public void list2Dbconf(List<SchemaBean> listIn) {
		List<SchemaBean> list=new ArrayList<SchemaBean>();
		list.addAll(listIn);
	}
	/*
	 * 根据前台配置的schema生成对应schema字段
	 */
	public void list2SchemaConf(List<SchemaBean> listIn) {
		List<SchemaBean> list=new ArrayList<SchemaBean>();
		list.addAll(listIn);
	}
	public ConfHandler() {
		System.out.println("init restrel...");
		httpClient = HttpClientBuilder.create().setMaxConnTotal(400)
				.setMaxConnPerRoute(400).build();
		restTemplate = new RestTemplate();
		restTemplate
				.setRequestFactory(new HttpComponentsClientHttpRequestFactory(
						httpClient));
	}

	/*
	 * 增加schema，传入参数为json格式
	 */
	public void addField(String scheme) {
		String url = "http://localhost:8090/solr/core1/schema?commit=true";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		HttpEntity<String> entity = new HttpEntity<String>(scheme, headers);
		ResponseEntity<String> sentData = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);
		System.out.println("USE: " + sentData.getBody());
	}
	public void addFiledJson(String json) {
		JSONObject jsonobj = JSONObject.fromObject(json);
		JSONArray rows = jsonobj.getJSONArray("rows");
		String rowsStr = rows.toString();
		rowsStr = rowsStr.replaceAll("\"column\":.*?,", "");
		rowsStr = rowsStr.replaceAll("\"pri\":.*?,", "");
		JSONObject schemaJson = new JSONObject();
		schemaJson.put("add-field", rowsStr);
		System.out.println(rowsStr);
		addField(schemaJson.toString());
		System.out.println(schemaJson.toString());
	}
	public void addDbEntity(int usrid, DBProperty dbProperty,
			List<TableField> fields) {
		String src = "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
