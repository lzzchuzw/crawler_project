package com.utils.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T>{
	
	public void saveObject(Object o);
	public void saveListObject(List<T> list);
	public T getObjectById(String id);
	public T getObject(String whereCondition);
	public List<T> findObjects(String whereCondition);	
	public List<T> findObjectsByCollections(String whereCondition, Collection<?> collections);
	public List<Map<String, Object>> findListMapByHql2(String hql);

}
