package com.sky.sns.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Blog;


public interface BlogMapper {  
	  
	
    void insertBlog(Blog blog);  
    
    Blog getBlogById(long id);
    
    void updateBlog(Blog blog);  
    
    void addTags(long qid,long tid);
    
    void deleteTags(long qid,long tid);
    
    void recommendBlog(long bid,long uid);
    
    int isRecommend(long bid,long uid);
    
    List<Blog> searchBlogs(com.sky.sns.mybatis.searchEntity.Blog blog);
    
    long countBlogs(com.sky.sns.mybatis.searchEntity.Blog blog);
    
    List<Blog> getMyBlogByCategoryId(@Param("id")long id,@Param("blogCategoryId")Long blogCategoryId,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
    long countMyBlogByCategoryId(@Param("id")long id,@Param("blogCategoryId")Long blogCategoryId);

    List<Blog> getAllBlogs();
    
    List<Blog> getBlogsByTagId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    long countBlogsByTagId(long id);
    
    List<Blog> getAllBlogsPage(@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    long countAllBlogs();
    
    List<Blog> getOtherBlogs(@Param("blogId")long blogId,@Param("userId")long userId,@Param("count")int count);
}  
