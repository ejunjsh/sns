package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.BlogComment;


public interface IBlogCommentService {
	void insertBlogComment(BlogComment qc);
	void deleteBlogComment(long id);
	List<BlogComment> getBlogCommentByBlogId(long blogId);
}
