package com.zkyunso.search.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.dom4j.Element;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.tool.xml.XmlHandler;
import com.view.model.DsDetails;
import com.zkyunso.db.handler.TableField;
import com.zkyunso.db.utils.DBProperty;
import com.zkyunso.search.bean.SchemaBean;
import com.zkyunso.search.bean.SchemaFieldBean;

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
	/**
	 * 操作schema
	 * @author Administrator
	 *
	 */
	public class SchemaHandler {
		final HttpHeaders headers = new HttpHeaders();
		static final String DELETE_CMD="";
		public SchemaHandler() {
			super();
			headers.setContentType(MediaType.TEXT_PLAIN);
		}
		/*
		 * list 转 schema json
		 */
		public String list2schemasJson(List<SchemaBean> list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("add-field", list);
			JSONObject json = JSONObject.fromObject(map);
			System.out.println(json);
			return json.toString();
		}
		public void addField(SchemaBean bean,String url) {
			String fieldJson=RestCmdJsonGenerator.generateAddFieldJson(bean);
			HttpEntity<String> entity = new HttpEntity<String>(fieldJson, headers);
			ResponseEntity<String> sentData = restTemplate.exchange(url,
					HttpMethod.POST, entity, String.class);
			System.out.println("USE: " + sentData.getBody());
		}
		public void addFields(List<? extends SchemaBean> list,String url) {
			System.out.println("add schemas:"+url);
			for(SchemaBean bean:list) 
				addField(bean, url);
		}
		public void deleteField() {
			
		}
		public void getField() {
			
		}
		public void modifyField() {
		
		}

	}
	/**
	 * 操作db-conf
	 * @author Administrator
	 *
	 */
	public class DataConfHandler {
		public String REST_DO_FULLIMPORT="http://localhost:8090/solr/core1/dataimport?command=full-import&clean=true&commit=true";
		public String REST_DO_DELTAIMPORT="http://localhost:8090/solr/core1/dataimport?command=full-import&clean=false&commit=true";
		public String FULLIMPORT_MODE="full";
		public String DELTAIMPORT_MODE="delta";
		/**
		 * 
		 * @param dihJson dih字段
		 * @param confDir dataconf.xml路径
		 * @param ds 数据源
		 */
		public void generateDataConf(String dihJson,String confSrc,DsDetails ds) {
			DBProperty dbProperty = new DBProperty("com.mysql.jdbc.Driver",
					ds.getDbUrl(), ds.getServerUsrname(), ds.getServerPsword());
			Element root = XmlHandler.addDataSource(confSrc,ds.getName(), dbProperty,ds.getDefaultTb());
		}
		/**
		 * 执行dih操作 full/delta模式
		 * @param mode
		 */
		public void doDih(String mode) {
			String response=null;
			if(mode.equals(this.FULLIMPORT_MODE))
				response=restTemplate.getForObject(REST_DO_FULLIMPORT, String.class);
			else response=restTemplate.getForObject(REST_DO_DELTAIMPORT, String.class);
			System.out.println(response);
		}
	}
	/**
	 * 存储配置全文信息
	 * @author Administrator
	 *
	 */
	public static class ConfContext {
		
		public static String SOLR_HOME="http://localhost:8090/solr";
		public static String SOLR_HOME_DIR="E:\\workplace\\svn\\zkyunso\\docs\\data-config1.xml";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
