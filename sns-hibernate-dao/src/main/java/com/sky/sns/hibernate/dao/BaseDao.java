package com.sky.sns.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


public class BaseDao<T,PK extends Serializable> {
	protected SessionFactory sessionFactory ;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDao()
	{
		 entityClass =(Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void create(T entity)
	{

		sessionFactory.getCurrentSession().persist(entity);
	}
	
	public void update(T entity)
	{
        sessionFactory.getCurrentSession().update(entity);
	}
	
	@SuppressWarnings("unchecked")
	public T getById(PK id)
	{
		List<T> l=sessionFactory.getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("id",id)).list();
		if(l!=null&&l.size()>0)
		{
			return l.get(0);
		}
		else
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByKey(String key,String propertyName)
	{
		
		List<T> l=sessionFactory.getCurrentSession().createCriteria(entityClass).add(Restrictions.like(propertyName, key, MatchMode.ANYWHERE)).list();
		if(l!=null&&l.size()>0)
		{
			return l;
		}
		else
		{
			return null;
		}
	}
}
