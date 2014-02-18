package com.sky.sns.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Question;

 

public interface QuestionMapper {  
	  
    void insertQuestion(Question question);  
    
    Question getQuestionById(long id);
    
    void updateQuestion(Question question);  
    
    void addTags(long qid,long tid);
    
    void deleteTags(long qid,long tid);
    
    List<Question> searchQuestions(com.sky.sns.mybatis.searchEntity.Question question);
    
    long countQuestions(com.sky.sns.mybatis.searchEntity.Question question);
    
    List<Question> getPendingQuestion(@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);

    
    long countPendingQuestion();
    void follow(long qid,long uid);
    
    void unfollow(long qid,long uid);
    
    Question getQuestionDetailById(@Param("id")long id,@Param("userId")long userId);
    
    int isFollow(long questionId,long userId);
    
    List<Question> getNewestQuestion(@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    long countAllQuestion();
    
    List<Question> getHotQuestion(@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
    List<Question> getQuestionByUserId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);

    List<Question> getAllQuestions();
    
    
    List<Question> getQuestionsByTagId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    long countQuestionsByTagId(long id);
}  
