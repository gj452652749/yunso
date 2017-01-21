package com.view.easyui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class DataFormatter {
	static public String list2mapjson(List list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println("generate default:"+json);
		return json.toString();
	}
}
