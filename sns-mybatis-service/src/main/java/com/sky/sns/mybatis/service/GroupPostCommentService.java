package com.sky.sns.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.GroupPost;
import com.sky.sns.mybatis.entity.GroupPostComment;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IGroupPostCommentService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.GroupPostCommentMapper;
import com.sky.sns.mybatis.mapper.GroupPostMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

public class GroupPostCommentService implements IGroupPostCommentService {


	private GroupPostCommentMapper groupPostCommentMapper;
	private GroupPostMapper groupPostMapper;
	private ActivityMapper activityMapper;
	private UserMapper userMapper;
	private NoticeMapper noticeMapper;
 
	@Transactional 
	public void insertGroupPostComment(GroupPostComment gpc) {
		
		
		GroupPost groupPost=groupPostMapper.getGroupPostById(gpc.getGroupPostId());
		if(groupPost!=null)
		{
			groupPostCommentMapper.insertGroupPostComment(gpc);
			
			Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.CommentPost.getValue());
			activity.setDescription(gpc.getContentNoHtml20());
			activity.setRefId(gpc.getId());
			activity.setTitle(groupPost.getTitle());
			activity.setUserId(gpc.getPostedByUserId());
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(gpc.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.PostCommentAtNotice.getValue());
				  notice.setRefId(gpc.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(groupPost.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
		}
	}
	  

	  @Transactional   
	public void deleteGroupPostComment(long id) {
		  GroupPostComment gpc=groupPostCommentMapper.getGroupPostCommentById(id);
		  if(gpc!=null)
		  {
		  GroupPost groupPost=groupPostMapper.getGroupPostById(gpc.getGroupPostId());
			if(groupPost!=null)
			{
				groupPostCommentMapper.deleteGroupPostComment(id);
			}
		  }
	}

	public void setGroupPostCommentMapper(
			GroupPostCommentMapper groupPostCommentMapper) {
		this.groupPostCommentMapper = groupPostCommentMapper;
	}


	public void setGroupPostMapper(GroupPostMapper groupPostMapper) {
		this.groupPostMapper = groupPostMapper;
	}


	@Transactional(readOnly=true)
	public List<GroupPostComment> getGroupPostCommentByGroupPostId(long id,
			int pageStart, int pageSize) {
		return groupPostCommentMapper.getGroupPostCommentByGroupPostId(id, pageStart, pageSize);
	}


	@Transactional(readOnly=true)
	public int countGroupPostCommentByGroupPostId(long id) {
		return groupPostCommentMapper.countGroupPostCommentByGroupPostId(id);
	}



	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
}
