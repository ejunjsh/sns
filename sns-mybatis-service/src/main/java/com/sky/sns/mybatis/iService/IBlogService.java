package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Blog;

public interface IBlogService {
    void insertBlog(Blog blog);  
    
    Blog getBlogById(long id);
    
    void updateBlog(Blog blog);  
    
    void updateBlogForTags(Blog blog,String[] ts);
    
    boolean recommendBlog(long bid,long uid);
    
    List<Blog> searchBlogs(com.sky.sns.mybatis.searchEntity.Blog blog);
    
    List<Blog> getMyBlogByCategoryId(long id,Long blogCategoryId,int pageStart,int pageSize);
    
    long countMyBlogByCategoryId(long id,Long blogCategoryId);
    
    List<Blog> getAllBlogs();
    
    List<Blog> getBlogsByTagId(long id,int pageStart,int pageSize);
    long countBlogsByTagId(long id);
    
    List<Blog> getAllBlogsPage(int pageStart,int pageSize);
    long countAllBlogs();
    
    List<Blog> getOtherBlogs(long blogId,long userId,int count);
}
