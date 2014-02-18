package com.sky.sns.mybatis.iService;

import java.util.Date;
import java.util.List;

import com.sky.sns.mybatis.entity.GroupPost;

public interface IGroupPostService {
    void insertGroupPost(GroupPost groupPost);  
    
    GroupPost getGroupPostById(long id);
    
    void updateGroupPost(GroupPost groupPost);  
    
    void updateGroupPostForTags(GroupPost groupPost,String[] ts);
    
    boolean recommendGroupPost(long bid,long uid);
    
    List<GroupPost> searchGroupPosts(com.sky.sns.mybatis.searchEntity.Group params);
    
    List<GroupPost> getRecommendPosts(long id,long curPostId,int pageStart,int pageSize);

    List<GroupPost> getGroupPostInGroup(long id,int isBest,int pageSize,int pageStart);
    
    long countGroupPostInGroup(long id,int isBest);
    
    List<GroupPost> getHottestPostsInPeriod(Date maxDate,Date minDate,int pageSize,int pageStart);
    long countHottestPostsInPeriod(Date maxDate,Date minDate);
    
    List<GroupPost> getGroupPostByUserId(long id,int pageSize,int pageStart);
    
    List<GroupPost> getAllGroupPosts();
    
List<GroupPost> getGroupPostsByTagId(long id,int pageStart,int pageSize);
    
    long countGroupPostsByTagId(long id);
}
