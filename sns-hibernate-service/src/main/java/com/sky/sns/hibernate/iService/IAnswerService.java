package com.sky.sns.hibernate.iService;

import com.sky.sns.hibernate.entity.Answer;

public interface IAnswerService {
	   public Answer getAnswerById(long id);

	   
	   public void createAnswer(Answer entity);

	   
	   public void updateAnswer(Answer entity);
	   
	   public boolean isExistUserInVote(long aid,long uid);
	    
       public boolean isExistUserInUesless(long aid,long uid);
}
