package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.GroupPostComment;

public interface GroupPostCommentMapper {
	void insertGroupPostComment(GroupPostComment gpc);
	void deleteGroupPostComment(long id);
	List<GroupPostComment> getGroupPostCommentByGroupPostId(long id);
	GroupPostComment getGroupPostCommentById(long id);
	
	List<GroupPostComment> getGroupPostCommentByGroupPostId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    
	int countGroupPostCommentByGroupPostId(long id);
}
