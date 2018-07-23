package com.utils.json;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
	
	public static final  ObjectMapper objectMapper = new ObjectMapper(); 
	private static Log logger = LogFactory.getLog(JacksonUtils.class);
	
	  public static String encode(Object obj) {
          try {
             return objectMapper.writeValueAsString(obj);
         } catch (Exception e) {
             logger.error("jackson encode error:", e); 
         }
         return null;
     }
 
     /**
     * 将json string反序列化成对象
      *
      * @param json
      * @param valueType
      * @return
     */
     public static <T> T decode(String json, Class<T> valueType) {
         try {
             return objectMapper.readValue(json, valueType);
         } catch (Exception e) {
             logger.error("jackson decode(String, Class<T>) error: ", e);
         }
         return null;
     }
 
     /**
      * 将json array反序列化为对象
      *
     * @param json
      * @param typeReference
      * @return
      */
     public static <T> T decode(String json, TypeReference<T> typeReference) {
         try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
             logger.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }

}
