package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Question;

public interface IQuestionService {
    void insertQuestion(Question question);  
    
    Question getQuestionById(long id);
    
    void updateQuestion(Question question);  
    
    void updateQuestionForTags(Question question,String[] ts);
    
    List<Question> searchQuestions(com.sky.sns.mybatis.searchEntity.Question question);
    
    List<Question> getPendingQuestion(int pageStart,int pageSize);
    long countPendingQuestion();
    
    List<Question> getNewestQuestion(int pageStart,int pageSize);
    long countAllQuestion();
    
    boolean follow(long qid,long uid);
    
    void unfollow(long qid,long uid);
    
    Question getQuestionDetailById(long id,long userId);
    
    List<Question> getHotQuestion(int pageStart,int pageSize);
    
    List<Question> getQuestionByUserId(long id,int pageStart,int pageSize);
    
    List<Question> getAllQuestions();
    
     List<Question> getQuestionsByTagId(long id,int pageStart,int pageSize);
    
    long countQuestionsByTagId(long id);
}
