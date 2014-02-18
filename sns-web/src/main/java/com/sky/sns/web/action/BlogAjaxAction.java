package com.sky.sns.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sky.sns.mybatis.entity.Blog;
import com.sky.sns.mybatis.entity.BlogCategory;
import com.sky.sns.mybatis.entity.BlogComment;
import com.sky.sns.mybatis.iService.IBlogCategoryService;
import com.sky.sns.mybatis.iService.IBlogCommentService;
import com.sky.sns.mybatis.iService.IBlogService;
import com.sky.sns.web.pojo.AjaxCode;

public class BlogAjaxAction extends AjaxAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	private IBlogCategoryService blogCategoryService;
	private IBlogCommentService blogCommentService;
	private IBlogService blogService;

	private BlogCategory category;
	
	private BlogComment comment;
	
	private Blog blog;


	public String addCategory() throws Exception 
	{
        if(category!=null)
        {
        	if(blogCategoryService.isExist(curUser.getId(), category.getName()))
        	{
        		jsonData.put("status", AjaxCode.forbit);
        		jsonData.put("data", null);
        		return "json";
        	}
        	else
        	{
        		category.setCreatedByUserId(curUser.getId());
        		category.setCreatedDate(new Date());
        		category.setUpdatedDate(new Date());
        		blogCategoryService.insertBlogCategory(category);
        		jsonData.put("status", AjaxCode.successful);
        		jsonData.put("data", category);
        		return "json";
        	}
        }
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String updateCategory() throws Exception 
	{
        if(category!=null)
        {
        	if(blogCategoryService.isExist(curUser.getId(), category.getName(),category.getId()))
        	{
        		jsonData.put("status", AjaxCode.forbit);
        		jsonData.put("data", null);
        		return "json";
        	}
        	else
        	{
        		BlogCategory updateCategory=blogCategoryService.getBlogCategoryById(category.getId());
        		updateCategory.setName(category.getName());
        		updateCategory.setUpdatedDate(new Date());
        		blogCategoryService.updateBlogCategory(updateCategory);
        		jsonData.put("status", AjaxCode.successful);
        		jsonData.put("data", category);
        		return "json";
        	}
        }
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String deleteComment() throws Exception 
	{
		if(comment!=null)
		{
		   blogCommentService.deleteBlogComment(comment.getId());   
		   jsonData.put("status", AjaxCode.successful);
   		    jsonData.put("data", null);
   			return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doRecommend() throws Exception 
	{
		if(blog!=null)
		{
		    if(blogService.recommendBlog(blog.getId(),curUser.getId()))
		    {
		    	jsonData.put("status", AjaxCode.successful);
	   		    jsonData.put("data", null);
	   			return "json";
		    }
		    else
		    {
		    	jsonData.put("status", AjaxCode.forbit);
				jsonData.put("data", null);
				return "json";
		    }
		    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	public BlogComment getComment() {
		return comment;
	}
	public void setComment(BlogComment comment) {
		this.comment = comment;
	}

	public BlogCategory getCategory() {
		return category;
	}

	public void setCategory(BlogCategory category) {
		this.category = category;
	}

	public void setBlogCategoryService(IBlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}

	public void setBlogCommentService(IBlogCommentService blogCommentService) {
		this.blogCommentService = blogCommentService;
	}


	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
}
