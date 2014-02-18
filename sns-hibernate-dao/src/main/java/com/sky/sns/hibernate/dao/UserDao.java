package com.sky.sns.hibernate.dao;


import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.sky.sns.hibernate.entity.User;

public class UserDao extends BaseDao<User,Long>
{
	@SuppressWarnings("unchecked")
    public User getByEmail(String email)
    {
		List<User> l=sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email",email)).list();
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
    public User getByNickName(String nickName)
    {
		List<User> l=sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("nickName",nickName)).list();
		if(l!=null&&l.size()>0)
		{
			return l.get(0);
		}
		else
		{
			return null;
		}
    }
}
