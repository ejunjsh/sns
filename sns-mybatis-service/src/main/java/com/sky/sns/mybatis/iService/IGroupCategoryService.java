package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.GroupCategory;

public interface IGroupCategoryService {
    void insertGroupCategory(GroupCategory GroupCategory);  
    void updateGroupCategory(GroupCategory GroupCategory); 
    GroupCategory getGroupCategoryById(long id);
    
    List<GroupCategory> getGroupCategoryByName(String name);
    
    boolean isExisting(String name,long id);
    
    List<GroupCategory> getAllGroupCategory();
    
    List<GroupCategory> searchGroupCategorys(com.sky.sns.mybatis.searchEntity.Group GroupCategory);
}
