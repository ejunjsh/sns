package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Tag;


public interface TagMapper {  
	  
    void insertTag(Tag tag);  
    
    void updateTag(Tag tag);  
    
    Tag getTagById(long id);
    
    List<Tag> getTagByName(String name);
    
    int isExisting(@Param("name")String name,@Param("id")long id);
    
    List<Tag> searchTags(com.sky.sns.mybatis.searchEntity.Tag tag);
    
    long countTags(com.sky.sns.mybatis.searchEntity.Tag tag);
    
    Tag getTagBySpell(@Param("spell")String spell,@Param("uid")long uid);
    
    void followTag(long uid,long tid);
    
    int isFollowTag(long uid,long tid);
    
    void unfollowTag(long uid,long tid);
    
    List<Tag> getMyTags(@Param("id")long id,@Param("uid")long uid,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
    
    List<Tag> getAllTags(@Param("key")String key,@Param("uid")long uid,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
    long countAllTags(@Param("key")String key);
}  
