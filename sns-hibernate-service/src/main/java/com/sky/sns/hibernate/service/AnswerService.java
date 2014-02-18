package com.sky.sns.hibernate.service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.hibernate.dao.*;
import com.sky.sns.hibernate.entity.Answer;
import com.sky.sns.hibernate.iService.*;

public class AnswerService implements IAnswerService
{
   private AnswerDao dao;

/**
 * @return the answerDao
 */
public AnswerDao getDao() {
	return dao;
}

/**
 * @param answerDao the answerDao to set
 */
public void setDao(AnswerDao dao) {
	this.dao = dao;
}

@Transactional(readOnly=true)
   public Answer getAnswerById(long id)
   {
	   return dao.getById(id);
   }
  
@Transactional
   public void createAnswer(Answer entity)
   {
	   dao.create(entity);
   }

@Transactional
   public void updateAnswer(Answer entity)
   {
	   dao.update(entity);
   }

@Transactional(readOnly=true)
public boolean isExistUserInVote(long aid, long uid) {
	return dao.isExistUser(aid, uid, "votedUsers");
}

@Transactional(readOnly=true)
public boolean isExistUserInUesless(long aid, long uid) {
	return dao.isExistUser(aid, uid, "uselessUsers");
}
}
