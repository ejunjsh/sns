package com.sky.sns.mybatis.iService;


import java.util.List;

import com.sky.sns.mybatis.entity.ArticleTopic;

public interface IArticleTopicService {
	  
    void insertArticleTopic(ArticleTopic articleTopic);  
    
    ArticleTopic getArticleTopicById(long id);
    
    List<ArticleTopic> searchArticleTopic(com.sky.sns.mybatis.searchEntity.Article articleTopic);
    
    Boolean isExist(String name,long id);
    
    void updateArticleTopic(ArticleTopic articleTopic);  
    
    void deleteArticleTopic(long id);  
}
