package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IUserService;
import com.sky.sns.mybatis.mapper.UserMapper;

public class UserService implements IUserService
{
    private UserMapper userMapper;
    
    @Transactional   
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
    
    @Transactional(readOnly=true)
    public User getUserById(long id)
    {
    	return userMapper.getUserById(id);
    }
    
    @Transactional(readOnly=true)
    public User getUserByEmail(String email)
    {
    	return userMapper.getUserByEmail(email);
    }
    
    @Transactional(readOnly=true)
    public User getUserByNickName(String nickName)
    {
    	return userMapper.getUserByNickName(nickName);
    }
    
    @Transactional   
    public void updateUser(User user)
    {
    	 userMapper.updateUser(user);
    }
    
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	 @Transactional(readOnly=true)
	public List<User> searchUser(com.sky.sns.mybatis.searchEntity.User user) {
		long total=this.userMapper.countUsers(user);
		user.setTotal(total);
		return this.userMapper.searchUsers(user);
	}

	 @Transactional(readOnly=true)
	public User getUserByNickNameExceptCurUser(String nickName, long id) {
		return userMapper.getUserByNickNameExceptCurUser(nickName, id);
	}

	 @Transactional(readOnly=true)
	public User getUserDetailById(long id,long curId) {
		return userMapper.getUserDetailById(id,curId);
	}

	 @Transactional
	public boolean follow(long userId, long followUserId) {
		 if(userMapper.isFollow(userId, followUserId)>0)
			 return false;
		 else
		 {
		    userMapper.follow(userId, followUserId);
		    return true;
		 }
		
	}

	 @Transactional
	public void cacelFollow(long userId, long followUserId) {
	    userMapper.cacelFollow(userId, followUserId);
		
	}

	 @Transactional(readOnly=true)
	public List<User> getFollowing(long id, long curId, int pageStart,
			int pageSize) {
		return userMapper.getFollowing(id, curId, pageStart, pageSize);
	}

	 @Transactional(readOnly=true)
	public List<User> getFollowed(long id, long curId, int pageStart,
			int pageSize) {
		return userMapper.getFollowed(id, curId, pageStart, pageSize);
	}

	 @Transactional(readOnly=true)
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}

	 @Transactional(readOnly=true)
	public List<User> getUsersByTagId(long id,long uid, int pageStart, int pageSize) {
		return userMapper.getUsersByTagId(id,uid, pageStart, pageSize);
	}

	 @Transactional(readOnly=true)
	public long countUsersByTagId(long id) {
		return userMapper.countUsersByTagId(id);
	}

}
