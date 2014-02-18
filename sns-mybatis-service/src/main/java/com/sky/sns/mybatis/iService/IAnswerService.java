package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Answer;

public interface IAnswerService {
	  void insertAnswer(Answer answer);  
	    
	    Answer getAnswerById(long id);
	    
	    void updateAnswer(Answer answer);  
	    List<Answer> getAnswersByQuestionId(long id);
	    
	    void addVote(Answer a,long uid,boolean isUp);
	    void addUseless(Answer a,long uid);
	    boolean isVoted(long aid,long uid);
	    boolean isUseless(long aid,long uid);
	    void deleteAnswer(Answer answer);
	    
	    List<Answer> getAnswersByUserId(long id,int pageStart,int pageSize);
	    
	    long countAnswersByUserId(long id);
}
