package com.sky.sns.mybatis.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.GroupPost;

public interface GroupPostMapper {  
	  
    void insertGroupPost(GroupPost groupPost);  
    
    GroupPost getGroupPostById(long id);
    
    void updateGroupPost(GroupPost groupPost);  
    
    void addTags(long qid,long tid);
    
    void deleteTags(long qid,long tid);
    
    void recommendGroupPost(long bid,long uid);
    
    int isRecommend(long bid,long uid);
    
    List<GroupPost> searchGroupPosts(com.sky.sns.mybatis.searchEntity.Group params);
    
    long countGroupPosts(com.sky.sns.mybatis.searchEntity.Group params);
    
    List<GroupPost> getRecommendPosts(@Param("id")long id,@Param("curPostId")long curPostId,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);

    List<GroupPost> getGroupPostInGroup(@Param("id")long id,@Param("isBest")int isBest,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    long countGroupPostInGroup(@Param("id")long id,@Param("isBest")int isBest);
    
    List<GroupPost> getHottestPostsInPeriod(@Param("maxDate")Date maxDate,@Param("minDate")Date minDate,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    long countHottestPostsInPeriod(@Param("maxDate")Date maxDate,@Param("minDate")Date minDate);
    
    List<GroupPost> getGroupPostByUserId(@Param("id")long id,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);
    
    List<GroupPost> getAllGroupPosts();
    
    List<GroupPost> getGroupPostsByTagId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    long countGroupPostsByTagId(long id);
}  
