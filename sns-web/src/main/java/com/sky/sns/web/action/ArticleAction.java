package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.*;
import com.sky.sns.mybatis.iService.*;


public class ArticleAction extends BasePageAction {
	
	private static final long serialVersionUID = -777042926217372600L;
	private IArticleService articleService;
	private IArticleTopicService articleTopicService;
	private IArticleCommentService articleCommentService;
	private ITagService tagService;
	


	private List<Article> allArticles;
	private List<ArticleTopic> allTopics;
	private Article article;
	private ArticleTopic topic;
	private List<ArticleComment> comments;
	private Article nextArticle;
	private Article preArticle;
	private List<Article> relatedArticles;
	private ArticleComment articleComment;
	private String tags;
	
	public String all() throws Exception{
		com.sky.sns.mybatis.searchEntity.Article params=new com.sky.sns.mybatis.searchEntity.Article();
		params.setPageIndex(pageNo-1);
		params.setPageSize(pageSize);
        allArticles=articleService.searchArticles(params);
        recordCount= params.getTotal();
	    allTopics=articleTopicService.searchArticleTopic(new com.sky.sns.mybatis.searchEntity.Article());
        return "all";
	}
	
	public String site() throws Exception{
		topic=articleTopicService.getArticleTopicById(topic.getId());
		if(topic!=null)
		{
			com.sky.sns.mybatis.searchEntity.Article params=new com.sky.sns.mybatis.searchEntity.Article();
			params.setPageIndex(pageNo-1);
			params.setPageSize(pageSize);
			params.setArticleTopicId(topic.getId());
	        allArticles=articleService.searchArticles(params);
	        recordCount= params.getTotal();
		    allTopics=articleTopicService.searchArticleTopic(new com.sky.sns.mybatis.searchEntity.Article());
	        return "site";
		}
		return ERROR;
	}
	
	public String detail() throws Exception {
		if (article.getId() != null) {
			article = articleService.getArticleById(article.getId());
			if (article != null) {
				article.setViewCount(article.getViewCount() + 1);
				articleService.updateArticle(article);
				comments=articleCommentService.getArticleCommentByArticleId(article.getId(),(int)getPageStart(),pageSize);
				this.recordCount=articleCommentService.countArticleCommentsByArticleId(article.getId());
				nextArticle=articleService.getNextArticle(article.getId());
				preArticle=articleService.getPreviousArticle(article.getId());
				relatedArticles=articleService.getArticleInTopic(article.getArticleTopicId(),0,5);
				return "detail";
			}
		}
		return ERROR;
	}

	public String newComment() throws Exception {
			if (article != null) {
				if (article.getId() != null){
					if (articleComment != null) {
						articleComment.setArticleId(article.getId());
						articleComment.setPostedByUserId(curUser.getId());
						articleComment.setPostedDate(new Date());
						articleCommentService.insertArticleComment(articleComment);
						this.recordCount=articleCommentService.countArticleCommentsByArticleId(article.getId());
						this.pageNo=(int) ((this.recordCount+this.pageSize-1)/this.pageSize);
						return "goToComment";
					}
					else
					{
						return "goToDetail";
					}
				}
		}
		return ERROR;
	}
    
	public String post() throws Exception {
		if (isPost()) {
			if (curUser != null&&topic!=null&&topic.getId()>0) {
				topic=articleTopicService.getArticleTopicById(topic.getId());
				if(topic!=null)
				{
				if (!tags.isEmpty()) {
					List<Tag> newTags = new ArrayList<Tag>();
					String[] ts = tags.split(",");
					if (article != null ) {
						for (String s : ts) {
							Tag tag = tagService.getTagById(Integer.parseInt(s
									.trim()));
							if (tag != null) {
								newTags.add(tag);
							}
						}
						article.setCommentCount(0);
						article.setPostedByUserId(curUser.getId());
						article.setPostedDate(new Date());
						article.setStatus(StatusEnum.NORMAL.getValue());
						article.setUpdatedDate(new Date());
						article.setViewCount(0);
						article.setTags(newTags);
						article.setArticleTopicId(topic.getId());
						articleService.insertArticle(article);
					} 
				}
			
				return "success";
				}
			} 
			return ERROR;
		} else {
			if(topic!=null&&topic.getId()>0)
			{
				topic=articleTopicService.getArticleTopicById(topic.getId());
				if(topic!=null)
				{
			       return "post";
				}
			}
		    return ERROR;
		}
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

	public void setArticleTopicService(IArticleTopicService articleTopicService) {
		this.articleTopicService = articleTopicService;
	}

	public List<Article> getAllArticles() {
		return allArticles;
	}

	public void setAllArticles(List<Article> allArticles) {
		this.allArticles = allArticles;
	}

	public List<ArticleTopic> getAllTopics() {
		return allTopics;
	}

	public void setAllTopics(List<ArticleTopic> allTopics) {
		this.allTopics = allTopics;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<ArticleComment> getComments() {
		return comments;
	}

	public void setComments(List<ArticleComment> comments) {
		this.comments = comments;
	}

	public Article getNextArticle() {
		return nextArticle;
	}

	public void setNextArticle(Article nextArticle) {
		this.nextArticle = nextArticle;
	}

	public Article getPreArticle() {
		return preArticle;
	}

	public void setPreArticle(Article preArticle) {
		this.preArticle = preArticle;
	}

	public List<Article> getRelatedArticles() {
		return relatedArticles;
	}

	public void setRelatedArticles(List<Article> relatedArticles) {
		this.relatedArticles = relatedArticles;
	}

	public void setArticleCommentService(
			IArticleCommentService articleCommentService) {
		this.articleCommentService = articleCommentService;
	}

	public ArticleComment getArticleComment() {
		return articleComment;
	}

	public void setArticleComment(ArticleComment articleComment) {
		this.articleComment = articleComment;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public ArticleTopic getTopic() {
		return topic;
	}

	public void setTopic(ArticleTopic topic) {
		this.topic = topic;
	}
	
	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}
}
