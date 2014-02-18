package com.sky.sns.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Article;


public interface ArticleMapper {  
	  
    void insertArticle(Article article);  
    
    Article getArticleById(long id);
    
    void updateArticle(Article article);  
    
    void addTags(long qid,long tid);
    
    void deleteTags(long qid,long tid);
    
    void recommendArticle(long bid,long uid);
    
    int isRecommend(long bid,long uid);
    
List<Article> searchArticles(com.sky.sns.mybatis.searchEntity.Article article);
    
    long countArticles(com.sky.sns.mybatis.searchEntity.Article article);
    
    Article getNextArticle(long id);
    
    Article getPreviousArticle(long id);
    
    List<Article> getArticleInTopic(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
    List<Article> getAllArticles();
    
    List<Article> getArticlesByTagId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    long countArticlesByTagId(long id);
}  
