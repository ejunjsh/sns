package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.BlogCategory;
import com.sky.sns.mybatis.iService.IBlogCategoryService;
import com.sky.sns.mybatis.mapper.BlogCategoryMapper;

public class BlogCategoryService implements IBlogCategoryService {
	
	 private BlogCategoryMapper blogCategoryMapper;

	@Transactional
	public void insertBlogCategory(BlogCategory blogCategory) {
		blogCategoryMapper.insertBlogCategory(blogCategory);
	}

	@Transactional(readOnly=true)
	public BlogCategory getBlogCategoryById(long id) {
	 return blogCategoryMapper.getBlogCategoryById(id);
	}
	
	@Transactional(readOnly=true)
	public Boolean isExist(long uid, String name) {
		 return blogCategoryMapper.getBlogCategoryByName(uid, name)>0;
	}
	
	@Transactional(readOnly=true)
	public Boolean isExist(long uid, String name, long id) {
		 return blogCategoryMapper.getBlogCategoryByNameForUpdate(uid, name,id)>0;
	}

	
	@Transactional(readOnly=true)
	public List<BlogCategory> getBlogCategoryByUserId(long uid)
	{
		return blogCategoryMapper.getBlogCategoryByUserId(uid);
	}

	@Transactional
	public void updateBlogCategory(BlogCategory blogCategory) {
		blogCategoryMapper.updateBlogCategory(blogCategory);
		
	}

	@Transactional
	public void deleteBlogCategory(long id) {
		blogCategoryMapper.deleteBlogCategory(id);
		
	}


	public void setBlogCategoryMapper(BlogCategoryMapper blogCategoryMapper) {
		this.blogCategoryMapper = blogCategoryMapper;
	}

	

	
	 
}
