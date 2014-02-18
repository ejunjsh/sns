package com.sky.sns.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.ArticleTopic;

public interface ArticleTopicMapper {  
	  
    void insertArticleTopic(ArticleTopic articleTopic);  
    
    ArticleTopic getArticleTopicById(long id);
    
    int getArticleTopicByName(@Param("name")String name,@Param("id")long id);
    
    List<ArticleTopic> searchArticleTopic(com.sky.sns.mybatis.searchEntity.Article articleTopic);
    
    int countArticleTopic(com.sky.sns.mybatis.searchEntity.Article articleTopic);

    void updateArticleTopic(ArticleTopic articleTopic);  
    
    void deleteArticleTopic(long id);  
    
    
}  
