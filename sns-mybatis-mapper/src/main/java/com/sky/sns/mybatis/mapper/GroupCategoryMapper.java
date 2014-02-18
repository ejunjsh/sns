package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.GroupCategory;


public interface GroupCategoryMapper {  
	  
    void insertGroupCategory(GroupCategory category);  
    
    void updateGroupCategory(GroupCategory category);  
    
    GroupCategory getGroupCategoryById(long id);
    
    List<GroupCategory> getAllGroupCategory();
    
    List<GroupCategory> getGroupCategoryByName(String name);
    
    int isExisting(@Param("name")String name,@Param("id")long id);
    
    List<GroupCategory> searchGroupCategorys(com.sky.sns.mybatis.searchEntity.Group category);
    
    long countGroupCategorys(com.sky.sns.mybatis.searchEntity.Group category);
}  
