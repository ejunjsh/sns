package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Tag;

public interface ITagService {
    void insertTag(Tag tag);  
    void updateTag(Tag tag); 
    Tag getTagById(long id);
    
    List<Tag> getTagByName(String name);
    
    boolean isExisting(String name,long id);
    
    List<Tag> searchTags(com.sky.sns.mybatis.searchEntity.Tag tag);
    
    Tag getTagBySpell(String spell,long uid);
    
    boolean followTag(long uid,long tid);
    
    void unfollowTag(long uid,long tid);
    
    List<Tag> getMyTags(long id,long uid,int pageStart,int pageSize);
    
    List<Tag> getAllTags(String key,long uid,int pageStart,int pageSize);
    
    long countAllTags(String key);
}
