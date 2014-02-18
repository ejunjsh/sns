package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Article;

public interface IArticleService {
    void insertArticle(Article article);  
    
    Article getArticleById(long id);
    
    void updateArticle(Article article);  
    
    void updateArticleForTags(Article article,String[] ts);
    
    boolean recommendArticle(long aid,long uid);
    
    List<Article> searchArticles(com.sky.sns.mybatis.searchEntity.Article article);
    
    Article getNextArticle(long id);
    
    Article getPreviousArticle(long id);
    
    List<Article> getArticleInTopic(long id,int pageStart,int pageSize);
    
    List<Article> getAllArticles();
    
    List<Article> getArticlesByTagId(long id,int pageStart,int pageSize);
    
    long countArticlesByTagId(long id);
}
