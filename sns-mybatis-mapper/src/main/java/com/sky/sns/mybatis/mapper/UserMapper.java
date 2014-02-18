package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.User;


public interface UserMapper {  
	  
    void insertUser(User user);  
    
    User getUserById(long id);
    
    User getUserByEmail(String email);
    
    User getUserByNickName(String nickName);
    
    User getUserByNickNameExceptCurUser(String nickName,long id);
    
    void updateUser(User user);
    
    List<User> searchUsers(com.sky.sns.mybatis.searchEntity.User user);
    
    long countUsers(com.sky.sns.mybatis.searchEntity.User user);
    
    User getUserDetailById(@Param("id")long id,@Param("curId")long curId);
    
    void follow(long userId,long followUserId);
    
    int isFollow(long userId,long followUserId);
    
    void cacelFollow(long userId,long followUserId);
    
    List<User> getFollowing(@Param("id")long id,@Param("curId")long curId,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
    List<User> getFollowed(@Param("id")long id,@Param("curId")long curId,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);

    List<User> getAllUsers();
    
    List<User> getUsersByTagId(@Param("id")long id,@Param("uid")long uid,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    long countUsersByTagId(long id);
}  