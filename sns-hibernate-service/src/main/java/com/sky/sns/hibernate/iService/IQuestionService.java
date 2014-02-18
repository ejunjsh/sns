package com.sky.sns.hibernate.iService;

import com.sky.sns.hibernate.entity.Question;

public interface IQuestionService {
	   public Question getQuestionById(Long id);

	   
	   public void createQuestion(Question entity);

	   
	   public void updateQuestion(Question entity);

}
