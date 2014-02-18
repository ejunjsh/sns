package com.sky.sns.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sky.sns.mybatis.entity.Article;
import com.sky.sns.mybatis.entity.ArticleComment;
import com.sky.sns.mybatis.iService.IArticleCommentService;
import com.sky.sns.mybatis.iService.IArticleService;
import com.sky.sns.web.pojo.AjaxCode;

public class ArticleAjaxAction extends AjaxAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	public ArticleComment getComment() {
		return comment;
	}

	public void setComment(ArticleComment comment) {
		this.comment = comment;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setArticleCommentService(
			IArticleCommentService articleCommentService) {
		this.articleCommentService = articleCommentService;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

	private IArticleCommentService articleCommentService;
	private IArticleService articleService;

	
	private ArticleComment comment;
	
	private Article article;


	public String deleteComment() throws Exception 
	{
		if(comment!=null)
		{
			articleCommentService.deleteArticleComment(comment.getId());   
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
		if(article!=null)
		{
		    if(articleService.recommendArticle(article.getId(),curUser.getId()))
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

}
