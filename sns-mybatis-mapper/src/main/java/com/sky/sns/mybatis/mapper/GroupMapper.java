package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Group;
import com.sky.sns.mybatis.entity.User;


public interface GroupMapper {  
	  
    void insertGroup(Group group);  
    
    Group getGroupById(long id);
    
    
    Group getGroupDetailById(@Param("id")long id,@Param("userId")long userId);
    
    void addCategory(long gid,long cid);
    
    void deleteCategory(long gid,long cid);
    
    int getGroupByName(@Param("name")String name,@Param("id")long id);
    
    void updateGroup(Group group); 
    
    List<Group> searchGroups(com.sky.sns.mybatis.searchEntity.Group group);
    
    long countGroups(com.sky.sns.mybatis.searchEntity.Group group);
    
    List<Group> getGroupByCategory(@Param("categoryId")long categoryId,@Param("userId")long userId,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    
    long countGroupByCategory(long categoryId);
    
    void joinGroup(long groupId,long userId);
    
    void quitGroup(long groupId,long userId);
    
    int isJoinGroup(long groupId,long userId);
    
    List<Group> getHottestGroup(@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    
    List<Group> getNewGroup(@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    
    List<Group> getGroupByKey(@Param("userId")long userId,@Param("key")String key);

    List<User> getGroupMembers(@Param("name")String name,@Param("groupId")long groupId,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    
    long countGroupMembers(@Param("name")String name,@Param("groupId")long groupId);
    
    List<Group> getRelatedGroup(@Param("id")long id,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    
    List<User> getActiveMember(@Param("id")long id,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    
    List<Group> getMyJoinedGroups(long id);
    
    List<Group> getAllGroups();
}  
