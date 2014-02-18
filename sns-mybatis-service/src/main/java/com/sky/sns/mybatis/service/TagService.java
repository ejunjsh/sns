package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.mybatis.mapper.TagMapper;

public class TagService implements ITagService {
	
	 private TagMapper tagMapper;
	
	    @Transactional   
    public void insertTag(Tag tag)
	    {
	    	tagMapper.insertTag(tag);
	    }
	    
	    @Transactional
	    public void updateTag(Tag tag)
	    {
		     tagMapper.updateTag(tag);
	    }
    
	    @Transactional(readOnly=true)
    public Tag getTagById(long id)
    {
    	return tagMapper.getTagById(id);
    }
    
	    @Transactional(readOnly=true)
    public List<Tag> getTagByName(String name)
    {
    	return tagMapper.getTagByName(name);
    }
    
    public void setTagMapper(TagMapper tagMapper) {
		this.tagMapper = tagMapper;
	}
    
    @Transactional(readOnly=true)
	public List<Tag> searchTags(com.sky.sns.mybatis.searchEntity.Tag tag) {
		long total=this.tagMapper.countTags(tag);
		tag.setTotal(total);
		return this.tagMapper.searchTags(tag);
	}

    @Transactional(readOnly=true)
	public boolean isExisting(String name,long id) {
		
		return tagMapper.isExisting(name, id)>0;
	}

    @Transactional(readOnly=true)
	public Tag getTagBySpell(String spell,long uid) {
		
		return tagMapper.getTagBySpell(spell,uid);
	}

    @Transactional
	public boolean followTag(long uid, long tid) {
		if(tagMapper.isFollowTag(uid, tid)>0)
		{
			return false;
		}
		else
		{
			tagMapper.followTag(uid, tid);
		}
		return true;
	}

    @Transactional
	public void unfollowTag(long uid, long tid) {
            tagMapper.unfollowTag(uid, tid);
	}

    @Transactional(readOnly=true)
	public List<Tag> getMyTags(long id,long uid, int pageStart, int pageSize) {
		return tagMapper.getMyTags(id,uid, pageStart, pageSize);
	}

    @Transactional(readOnly=true)
	public List<Tag> getAllTags(String key, long uid, int pageStart,
			int pageSize) {
		return tagMapper.getAllTags(key, uid, pageStart, pageSize);
	}

    @Transactional(readOnly=true)
	public long countAllTags(String key) {
		return tagMapper.countAllTags(key);
	}
}
