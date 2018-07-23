package com.utils.json;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonUtils {
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String json){
		return JSON.parseObject(json,Map.class);
	}
	public static String Object2JsonString(Object object) {
		return JSON.toJSONString(object);
		//JSONArray.parse(text)
	}
	public static List<Map> jsonArray2Map(String json){	
		return JSON.parseArray(json, Map.class);
	}
	
	public static String Map2JsonString(Map map) {
		return JSON.toJSONString(map);
	}
	
	

}
