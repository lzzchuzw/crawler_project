package com.utils.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.utils.hibernate.AnnotationHibernateUtil;
import com.utils.hibernate.BaseEntity;
import com.utils.reflect.ReflectUtils;

public class BaseDao<T extends BaseEntity> implements IBaseDao<T> {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 数据操作工厂SessionFactory
	 */
	//private static SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
	//private SessionFactory sessionFactory;
	protected Class<T> entityClass;
	/**
	 * 在构造函数中利用反射机制获得参数T的具体类
	 */
	public BaseDao() {
		entityClass = ReflectUtils.getClassGenricType(getClass());
		log.info(entityClass.getName());
		
	}
	/*
	 * 获得Session
	 */
	/*public Session getCurrentSession() {
		try {
			//this.sessionFactory =  AnnotationHibernateUtil.getSessionFactory();
			SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			log.error("获取session工厂出现异常:" + e);
			e.printStackTrace();
			return null;
		}
	}*/
	@Override
	public void saveObject(Object o) {
		SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(o);
			session.getTransaction().commit();	
		} catch (Exception e) {
			log.error("保存出现异常:" + e);
			e.printStackTrace();
			return ;
		}finally {
			
			session.close();
			sessionFactory.close();
		}
		
	}
	@Override
	public void saveListObject(List<T> list) {
		SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			for(int index=0;index<list.size();index++) {
				T o = list.get(index);
				session.save(o);
				if(index>0&&0==index%100) {
					session.flush();
					session.clear();
				}
			}
		}catch(Exception e) {
			log.error("保存出现异常:" + e);
			e.printStackTrace();
			return ;
		}finally {
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObjectById(String id) {
		SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
		    Object obj = session.get(entityClass, Integer.parseInt(id));
		    session.getTransaction().commit();
		    return (T) obj;
		}catch(Exception e) {
			log.error("通过Id查询对象出现异常:" + e);
			e.printStackTrace();
			return null;
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public T getObject(String whereCondition) {
		String hql = " from " + entityClass.getSimpleName() + "  o ";
		if (whereCondition != null) {
			hql += whereCondition;
		}
		SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();			
			Query query = session.createQuery(hql);
			//query.setMaxResults(1);
			Object obj = query.uniqueResult();			
			session.getTransaction().commit();
			return (T) obj;
		}catch(Exception e) {
			log.error("通过条件查询集合出现异常:" + e);
			e.printStackTrace();
			return null;
		}
		
	}
	@SuppressWarnings("unchecked")
	public List<T> findObjects(String whereCondition) {
		String hql = " from " + entityClass.getSimpleName() + "  o ";
		if (whereCondition != null) {
			hql += whereCondition;
		}
		SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<T> list = session.createQuery(hql).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			log.error("通过条件查询集合出现异常:" + e);
			e.printStackTrace();
			return null;
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findObjectsByCollections(String whereCondition, Collection<?> collections) {
		String hql = " from " + entityClass.getSimpleName() + "  o ";
		SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		if (whereCondition != null)
			hql += whereCondition;
		try {
			Query query = session.createQuery(hql);
			query.setParameterList("alist", collections);
			List<T> list = query.list();
			return list;
		} catch (Exception e) {
			log.error("通过条件查询集合出现异常:" + e);
			e.printStackTrace();
			return null;
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findListMapByHql2(String hql) {
		SessionFactory sessionFactory = AnnotationHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<Map<String, Object>> list = session.createQuery(hql)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			return list;
		} catch (Exception e) {
			log.error("获取list<map>集合出现异常:" + e);
			e.printStackTrace();
			return null;
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	    

}
