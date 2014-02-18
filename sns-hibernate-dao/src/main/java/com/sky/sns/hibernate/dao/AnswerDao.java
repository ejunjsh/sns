package com.sky.sns.hibernate.dao;
import java.util.List;

import com.sky.sns.hibernate.entity.Answer;
public class AnswerDao extends BaseDao<Answer,Long> {
    public boolean isExistUser(long aid,long uid,String collectionName)
    {
    	@SuppressWarnings("unchecked")
		List<Object> o= sessionFactory.getCurrentSession()
    	.createQuery("select u from User u , Answer a where u.id=:uid and a.id=:aid and u in elements(a."+collectionName+")")
    	.setLong("uid",uid).setLong("aid", aid).list();
    	if(o!=null&&o.size()>0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
