package com.sky.sns.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Answer;


public interface AnswerMapper {  
	  
    void insertAnswer(Answer answer);  
    
    Answer getAnswerById(long id);
    
    void updateAnswer(Answer answer);  
    
    List<Answer> getAnswersByQuestionId(long id);
    
    void addVote(long aid,long uid,int value);
    void deleteVote(long id);
    void addUseless(long aid,long uid);
    void deleteUseless(long id);
    int isVoted(long aid,long uid);
    int isUseless(long aid,long uid);
    void deleteAnswer(long id);
    
    List<Answer> getAnswersByUserId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
    long countAnswersByUserId(@Param("id")long id);
}  
