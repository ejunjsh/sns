package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.User;

public interface IUserService {
	void insertUser(User user);
	User getUserById(long id);
	
	 User getUserByEmail(String email);
	    
	 User getUserByNickName(String nickName);
	 
	 void updateUser(User user);
	 
	 List<User> searchUser(com.sky.sns.mybatis.searchEntity.User user);
	 
	 User getUserByNickNameExceptCurUser(String nickName,long id);
	 
	 User getUserDetailById(long id,long curId);
	 
	 boolean follow(long userId,long followUserId);
	    
	    void cacelFollow(long userId,long followUserId);
	    
	    List<User> getFollowing(long id,long curId,int pageStart,int pageSize);
	    
	    List<User> getFollowed(long id,long curId,int pageStart,int pageSize);
	    
	    List<User> getAllUsers();
	    
	    List<User> getUsersByTagId(long id,long uid,int pageStart,int pageSize);
	    long countUsersByTagId(long id);

}
