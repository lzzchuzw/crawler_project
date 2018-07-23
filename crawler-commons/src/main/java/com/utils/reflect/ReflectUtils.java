package com.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class ReflectUtils {
	/**
	 * 获得超类的参数类型，取第一个参数类型
	 * @param <T> 类型参数
	 * @param clazz 超类类型
	 */
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		
		return getClassGenricType(clazz, 0);
	}
	/**
	 * 根据索引获得超类的参数类型
	 * @param clazz 超类类型
	 * @param index 索引
	 */
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		System.out.println("genType = "+genType+"----params = "+params+"----index = "+index);
		return (Class) params[index];
	}
	
	public static void outputObject(final Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		System.out.println("fields.lenth = "+fields.length);
		for(int index=0;index<fields.length;index++) {
			Field field = fields[index];
			String fieldName = field.getName();
			
			String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			
			/*Method[] methods = TradingPlatform.class.getMethods();
			for(int i=0;i<methods.length;i++) {
				Method method = methods[i];
				if(method.getName().contains("get")) {
					System.out.println("i = "+i+"----getMethod name = "+method.getName());
					System.out.println(method.invoke(saveTradingPlatform, null));
				}
			}*/
			
			Method method;
			try {
				method = o.getClass().getDeclaredMethod(getMethodName, null);
				String s = String.valueOf(method.invoke(o, null));
				System.out.println("getMethodName = "+getMethodName+"--- = "+s);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
