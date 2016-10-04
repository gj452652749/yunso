package com.zkyunso.search.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zkyunso.search.bean.SchemaBean;

import net.sf.json.JSONObject;

public class ConfDataFormatter {
	/*
	 * list 转 schema json
	 */
	static public String list2schemasJson(List<SchemaBean> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("add-field", list);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json);
		return json.toString();
	}
}
