package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.GroupCategory;
import com.sky.sns.mybatis.iService.IGroupCategoryService;
import com.sky.sns.mybatis.mapper.GroupCategoryMapper;

public class GroupCategoryService implements IGroupCategoryService {
	
	 private GroupCategoryMapper GroupCategoryMapper;
	
	    @Transactional   
    public void insertGroupCategory(GroupCategory GroupCategory)
	    {
	    	GroupCategoryMapper.insertGroupCategory(GroupCategory);
	    }
	    
	    @Transactional
	    public void updateGroupCategory(GroupCategory GroupCategory)
	    {
		     GroupCategoryMapper.updateGroupCategory(GroupCategory);
	    }
    
	    @Transactional(readOnly=true)
    public GroupCategory getGroupCategoryById(long id)
    {
    	return GroupCategoryMapper.getGroupCategoryById(id);
    }
    
	    @Transactional(readOnly=true)
    public List<GroupCategory> getGroupCategoryByName(String name)
    {
    	return GroupCategoryMapper.getGroupCategoryByName(name);
    }
    
    public void setGroupCategoryMapper(GroupCategoryMapper GroupCategoryMapper) {
		this.GroupCategoryMapper = GroupCategoryMapper;
	}
    
    @Transactional(readOnly=true)
	public List<GroupCategory> searchGroupCategorys(com.sky.sns.mybatis.searchEntity.Group GroupCategory) {
		long total=this.GroupCategoryMapper.countGroupCategorys(GroupCategory);
		GroupCategory.setTotal(total);
		return this.GroupCategoryMapper.searchGroupCategorys(GroupCategory);
	}

    @Transactional(readOnly=true)
	public boolean isExisting(String name,long id) {
		
		return GroupCategoryMapper.isExisting(name, id)>0;
	}

    @Transactional(readOnly=true)
	public List<GroupCategory> getAllGroupCategory() {
    	return GroupCategoryMapper.getAllGroupCategory();
	}
}
