package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.ArticleTopic;
import com.sky.sns.mybatis.iService.IArticleTopicService;
import com.sky.sns.mybatis.mapper.ArticleTopicMapper;


public class ArticleTopicService implements IArticleTopicService {
	
	 private ArticleTopicMapper articleTopicMapper;

	@Transactional
	public void insertArticleTopic(ArticleTopic articleTopic) {
		articleTopicMapper.insertArticleTopic(articleTopic);
	}

	public void setArticleTopicMapper(ArticleTopicMapper articleTopicMapper) {
		this.articleTopicMapper = articleTopicMapper;
	}

	@Transactional(readOnly=true)
	public ArticleTopic getArticleTopicById(long id) {
	 return articleTopicMapper.getArticleTopicById(id);
	}
	
	@Transactional(readOnly=true)
	public Boolean isExist(String name,long id) {
		 return articleTopicMapper.getArticleTopicByName(name,id)>0;
	}
	
	@Transactional(readOnly=true)
	public  List<ArticleTopic> searchArticleTopic(com.sky.sns.mybatis.searchEntity.Article articleTopic)
	{
		long total=articleTopicMapper.countArticleTopic(articleTopic);
		articleTopic.setTotal(total);
   		return this.articleTopicMapper.searchArticleTopic(articleTopic);
	}

	@Transactional
	public void updateArticleTopic(ArticleTopic articleTopic) {
		articleTopicMapper.updateArticleTopic(articleTopic);
		
	}

	@Transactional
	public void deleteArticleTopic(long id) {
		articleTopicMapper.deleteArticleTopic(id);
		
	}

}
