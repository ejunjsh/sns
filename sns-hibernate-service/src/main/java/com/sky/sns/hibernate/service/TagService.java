package com.sky.sns.hibernate.service;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.hibernate.dao.*;
import com.sky.sns.hibernate.entity.Tag;
import com.sky.sns.hibernate.iService.*;

public class TagService implements ITagService
{
   private TagDao dao;

/**
 * @return the answerDao
 */
public TagDao getDao() {
	return dao;
}

/**
 * @param answerDao the answerDao to set
 */
public void setDao(TagDao dao) {
	this.dao = dao;
}
@Transactional(readOnly=true)
   public Tag getTagById(long id)
   {
	   return dao.getById(id);
   }
@Transactional(readOnly=true)
public List<Tag> getTagsByKey(String key)
{
	   return dao.getByKey(key, "name");
}
   @Transactional
   public void createTag(Tag entity)
   {
	   dao.create(entity);
   }
   @Transactional
   public void updateTag(Tag entity)
   {
	   dao.update(entity);
   }
}
