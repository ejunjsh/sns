package com.sky.sns.hibernate.iService;

import java.util.List;

import com.sky.sns.hibernate.entity.Tag;

public interface ITagService {
	   public Tag getTagById(long id);

	   
	   public void createTag(Tag entity);

	   
	   public void updateTag(Tag entity);
	   
	   public List<Tag> getTagsByKey(String key);

}
