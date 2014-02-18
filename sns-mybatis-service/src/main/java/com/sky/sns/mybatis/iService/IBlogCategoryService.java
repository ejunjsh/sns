package com.sky.sns.mybatis.iService;


import java.util.List;

import com.sky.sns.mybatis.entity.BlogCategory;

public interface IBlogCategoryService {
	  
    void insertBlogCategory(BlogCategory blogCategory);  
    
    BlogCategory getBlogCategoryById(long id);
    
    List<BlogCategory> getBlogCategoryByUserId(long uid);
    
    Boolean isExist(long uid, String name);
    
    Boolean isExist(long uid, String name,long id);
    
    void updateBlogCategory(BlogCategory blogCategory);  
    
    void deleteBlogCategory(long id);  
}
