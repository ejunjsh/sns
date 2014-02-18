package com.sky.sns.hibernate.service;


import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.hibernate.dao.*;
import com.sky.sns.hibernate.entity.Question;
import com.sky.sns.hibernate.iService.*;

public class QuestionService implements IQuestionService
{
   private QuestionDao dao;

/**
 * @return the answerDao
 */
public QuestionDao getDao() {
	return dao;
}

/**
 * @param answerDao the answerDao to set
 */
public void setDao(QuestionDao dao) {
	this.dao = dao;
}
@Transactional(readOnly=true)
   public Question getQuestionById(Long id)
   {
	   return dao.getById(id);
   }
   @Transactional
   public void createQuestion(Question entity)
   {
	   dao.create(entity);
   }
   @Transactional
   public void updateQuestion(Question entity)
   {
	   dao.update(entity);
   }
}