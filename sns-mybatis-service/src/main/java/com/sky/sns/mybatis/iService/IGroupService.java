package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Group;
import com.sky.sns.mybatis.entity.User;

public interface IGroupService {
		  
	    void insertGroup(Group group);  
	    
	    Group getGroupDetailById(long id,long userId);
	    
	    Group getGroupById(long id);
	    
	    void updateGroup(Group group); 
	    
	    void updateGroupForCategorys(Group group, String[] cs);
	    
	    List<Group> searchGroups(com.sky.sns.mybatis.searchEntity.Group group);
	    
	    Boolean isExist(String name,long id);
	    
	    List<Group> getGroupByCategory(long categoryId,long userId,int pageSize,int pageStart);
	    
	    long countGroupByCategory(long categoryId);
	    
	    boolean joinGroup(long groupId,long userId);
	    
	    void quitGroup(long groupId,long userId);
	    
	    List<Group> getMyJoinedGroups(long id);
        
	    
	    List<Group> getHottestGroup(int pageSize,int pageStart);
	    
	    List<Group> getNewGroup(int pageSize,int pageStart);
	    
	    List<Group> getGroupByKey(long userId,String key);
	    
	    List<User> getGroupMembers(String name,long groupId,int pageSize,int pageStart);
	    
	    long countGroupMembers(String name,long groupId);
	    
	    List<Group> getRelatedGroup(long id,int pageSize,int pageStart);
	    
	    List<User> getActiveMember(long id,int pageSize,int pageStart);
	    
	    List<Group> getAllGroups();
}
