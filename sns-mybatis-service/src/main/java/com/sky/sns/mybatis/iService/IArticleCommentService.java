package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.ArticleComment;

public interface IArticleCommentService {
	void insertArticleComment(ArticleComment ac);
	void deleteArticleComment(long id);
	List<ArticleComment> getArticleCommentByArticleId(long articleId,int pageStart,int pageSize);
	int countArticleCommentsByArticleId(long id);
}
