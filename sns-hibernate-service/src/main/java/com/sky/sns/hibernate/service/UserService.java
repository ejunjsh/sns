package com.sky.sns.hibernate.service;



import org.springframework.transaction.annotation.*;

import com.sky.sns.hibernate.dao.*;
import com.sky.sns.hibernate.entity.User;
import com.sky.sns.hibernate.iService.*;

public class UserService implements IUserService
{
   private UserDao dao;



   public UserDao getDao() {
	return dao;
}

public void setDao(UserDao dao) {
	this.dao = dao;
}

@Transactional(readOnly=true)
public User getUserById(long id)
   {
	   return dao.getById(id);
   }

@Transactional
   public void createUser(User entity)
   {
	   dao.create(entity);
       
   }
@Transactional
   public void updateUser(User entity)
   {
	   dao.update(entity);
   }
   
@Transactional(readOnly=true)
   public User getByEmail(String email)
   {
	  return dao.getByEmail(email);
   }

@Transactional(readOnly=true)
public User getByNickName(String nickName)
{
	  return dao.getByNickName(nickName);
}
}
