package com.sky.sns.mybatis.mapper;

import java.util.List;

import com.sky.sns.mybatis.entity.BlogComment;


public interface BlogCommentMapper {
	void insertBlogComment(BlogComment qc);
	void deleteBlogComment(long id);
	List<BlogComment> getBlogCommentByBlogId(long id);
	BlogComment getBlogCommentById(long id);
}
