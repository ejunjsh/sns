package com.sky.sns.mybatis.mapper;


import java.util.List;

import com.sky.sns.mybatis.entity.BlogCategory;


public interface BlogCategoryMapper {  
	  
    void insertBlogCategory(BlogCategory blogCategory);  
    
    BlogCategory getBlogCategoryById(long id);
    
    int getBlogCategoryByName(long uid,String name);
    
    int getBlogCategoryByNameForUpdate(long uid,String name,long id);
    
    List<BlogCategory> getBlogCategoryByUserId(long uid);
    
    void updateBlogCategory(BlogCategory blogCategory);  
    
    void deleteBlogCategory(long id);  
}  
