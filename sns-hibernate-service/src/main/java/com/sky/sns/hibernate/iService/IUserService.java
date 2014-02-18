package com.sky.sns.hibernate.iService;

import com.sky.sns.hibernate.entity.User;

public interface IUserService {
	 public User getUserById(long id);

	   
	   public void createUser(User entity);

	   
	   public void updateUser(User entity);
	   
	   public User getByEmail(String email);
	   
	   public User getByNickName(String nickName);

}
