package com.utils.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.utils.dao.BaseDao;
import com.utils.dao.IBaseDao;
import com.utils.hibernate.BaseEntity;

public class BaseService<T extends BaseEntity> implements IBaseService<T> {
	public IBaseDao<T> baseDao;
    
	public BaseService() {
		this.baseDao = new BaseDao<T>();
	}
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void saveObject(Object o) {
        if(null==o) {
        	System.out.println("saveObject o is null");
        	return;
        }
        System.out.println("o.name = "+o.toString());
        if(null==baseDao) {
        	System.out.println("baseDao is null");
        	return;
        }
        try {
		  baseDao.saveObject(o);
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	@Override
	public void saveListObject(List<T> list) {
		if(null==list || 0==list.size()) {
			System.out.println("saveListObject,list is null or list size is 0");
			return;
		}
		if(null==this.baseDao) {
			System.out.println("baseDao is null");
			return;
		}
		baseDao.saveListObject(list);
	}
	@Override
	public T getObjectById(String id) {

		return baseDao.getObjectById(id);
	}
	@Override
	public T getObject(String whereCondition) {
		// TODO Auto-generated method stub
		return baseDao.getObject(whereCondition);
	}
	@Override
	public List<T> findObjects(String whereCondition) {
		
		return baseDao.findObjects(whereCondition);
	}
	@Override
	public List<T> findObjectsByCollections(String whereCondition, Collection<?> collections){
		return baseDao.findObjectsByCollections(whereCondition, collections);
	}
	@Override
	public List<Map<String, Object>> findListMapByHql2(String hql) {
		
		return baseDao.findListMapByHql2(hql);
	}
	

}
