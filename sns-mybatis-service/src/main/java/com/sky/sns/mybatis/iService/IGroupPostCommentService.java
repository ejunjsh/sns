package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.GroupPostComment;

public interface IGroupPostCommentService {
	void insertGroupPostComment(GroupPostComment gpc);
	void deleteGroupPostComment(long id);
	
	List<GroupPostComment> getGroupPostCommentByGroupPostId(long id,int pageStart,int pageSize);
    
	int countGroupPostCommentByGroupPostId(long id);
}
