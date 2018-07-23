package com.utils.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapUtils {
	public static Logger log = LoggerFactory.getLogger(MapUtils.class.getClass());
	
	/**
	 * 通用的遍历Map并输出Key和value
	 * @param map
	 */
	public static void traversalMap(Map<String,Object> map) {
		
		Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
	    while(iterator.hasNext()) {
	    	Map.Entry<String, Object> entry = iterator.next();
	    	if(null!=entry) {
	    		String key = entry.getKey();
	    		String value = String.valueOf(entry.getValue());
	    		System.out.println("key = "+key+"---value = "+value);
	    		log.info("key = "+key+"---value = "+value);
	    	}
	    }
	}
	/**
	 * 通用的遍历Map并输出Key和value
	 * @param map
	 */
	public static void traversalMap2(Map<Object,Object> map) {
		
		Iterator<Map.Entry<Object,Object>> iterator = map.entrySet().iterator();
	    while(iterator.hasNext()) {
	    	Map.Entry<Object, Object> entry = iterator.next();
	    	if(null!=entry) {
	    		String key = String.valueOf(entry.getKey());
	    		String value = String.valueOf(entry.getValue());
	    		System.out.println("key = "+key+"---value = "+value);
	    		log.info("key = "+key+"---value = "+value);
	    	}
	    }
	}
	/**
	 * 通用的遍历Map并输出Key和value
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public static void traversalMap3(Map map) {
		if(null==map || 0==map.size()) {
			return ;
		}
		Iterator<Map.Entry<Object,Object>> iterator = map.entrySet().iterator();
	    while(iterator.hasNext()) {
	    	Map.Entry<Object, Object> entry = iterator.next();
	    	if(null!=entry) {
	    		String key = String.valueOf(entry.getKey());
	    		String value = String.valueOf(entry.getValue());
	    		System.out.println("key = "+key+"---value = "+value);
	    		log.info("key = "+key+"---value = "+value);
	    	}
	    }
	}
	/**
	 * 遍历List<Map<String,Object>> listmap
	 * @param listmap
	 */
	public static void traversalListMap(List<Map<String,Object>> listmap) {
		 for(int i=0;i<listmap.size();i++){
		    	System.out.println("-----------------i = "+i+"----------------------");
		    	Map<String,Object> map = listmap.get(i);
		    	if(null!=map&&map.size()>0) {
		    		Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
		    		while(iterator.hasNext()) {
		    			Map.Entry<String, Object> entry = iterator.next();
		    			if(null!=entry) {
		    				String key = entry.getKey();
		    				Object oValue = entry.getValue();
		    				String value = "";
		    				if(null!=oValue) {
		    					value = String.valueOf(oValue); 
		    				}
		    				System.out.println("key = "+key+"---value = "+value);
		    			}
		    		}
		    	}
		    }
	}
	/**
	 * 遍历List<Map>
	 * @param listmap
	 */
	@SuppressWarnings("rawtypes")
	public static void traversalListMap3(List<Map> listmap) {
		if(null==listmap || 0==listmap.size()) {
			return ;
		}
		for(int i=0;i<listmap.size();i++) {
			Map map = listmap.get(i);
			traversalMap3(map);
		}
	}
	/**
	 * 为每个map的key-value更换key名  在原有的名上加上prefix  prefix_key
	 * @param map
	 * @param prefix
	 * @return 更名后的map
	 */
	public static Map<String,Object> translateKeysMap(Map<String,Object> map,String prefix) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
	    while(iterator.hasNext()) {
	    	Map.Entry<String, Object> entry = iterator.next();
	    	if(null!=entry) {
	    		String key = entry.getKey();
	    		String value = String.valueOf(entry.getValue());
	    		System.out.println("key = "+key+"---value = "+value);
	    		//key的第一个字母大写
	    		key = prefix+"_"+key;
	    		retMap.put(key, value);
	    	}
	    }
	    //traversalMap(retMap);
	    return retMap;
	}
 
}
