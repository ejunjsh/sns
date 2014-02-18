package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.ArticleComment;


public interface ArticleCommentMapper {
	void insertArticleComment(ArticleComment ac);
	void deleteArticleComment(long id);
	List<ArticleComment> getArticleCommentByArticleId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
	ArticleComment getArticleCommentById(long id);
	int countArticleCommentsByArticleId(long id);
}
