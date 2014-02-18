package com.sky.sns.mybatis.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.GroupPost;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IGroupPostService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.GroupPostMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.TagMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

public class GroupPostService implements IGroupPostService {
	
	 private GroupPostMapper groupPostMapper;
	 private TagMapper tagMapper;
	 private ActivityMapper activityMapper;
	 private UserMapper userMapper;
	 private NoticeMapper noticeMapper;

		public void setTagMapper(TagMapper tagMapper) {
			this.tagMapper = tagMapper;
		}
		

		public void setGroupPostMapper(GroupPostMapper groupPostMapper) {
			this.groupPostMapper = groupPostMapper;
		}

	
	    @Transactional   
    public void insertGroupPost(GroupPost groupPost)
	    {
	    	groupPostMapper.insertGroupPost(groupPost);
	    	List<Tag> tags=groupPost.getTags();
	    	if(tags!=null&&tags.size()>0)
	    	{
	    		for(Tag t:tags)
	    		{
	    			groupPostMapper.addTags(groupPost.getId(), t.getId());
	    		}
	    	}
	    	
	    	Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.NewPost.getValue());
			activity.setDescription(groupPost.getContentNoHtml20());
			activity.setRefId(groupPost.getId());
			activity.setTitle(groupPost.getTitle());
			activity.setUserId(groupPost.getPostedByUserId());
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(groupPost.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.PostAtNotice.getValue());
				  notice.setRefId(groupPost.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(groupPost.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
	    }
    
	    @Transactional(readOnly=true)
    public GroupPost getGroupPostById(long id)
    {
    	return groupPostMapper.getGroupPostById(id);
    }
    
	    @Transactional
    public void updateGroupPost(GroupPost groupPost)
    {
	    	groupPostMapper.updateGroupPost(groupPost);
    }
    


    @Transactional
	public void updateGroupPostForTags(GroupPost groupPost, String[] ts) {
		for (String s : ts) {
			boolean isNew = true;
			for (Tag t : groupPost.getTags()) {
				if (Integer.parseInt(s.trim()) == t
						.getId()) {
					isNew = false;
					break;
				}
			}
			if (isNew) {
				Tag tag = tagMapper
						.getTagById(Integer
								.parseInt(s.trim()));
				if (tag != null) {
					groupPostMapper.addTags(groupPost.getId(), tag.getId());
				}
			}
		}
		for (int i = 0; i < groupPost.getTags()
				.size(); i++) {
			Tag t = groupPost.getTags().get(i);
			boolean isDelete = true;
			for (String s : ts) {
				if (Integer.parseInt(s.trim()) == t
						.getId()) {
					isDelete = false;
					break;
				}
			}
			if (isDelete) {
				groupPostMapper.deleteTags(groupPost.getId(), t.getId());
			}
		}
		groupPostMapper.updateGroupPost(groupPost);
		
		List<String> referers=new ArrayList<String>();
		StringConversion.generateRefererLinks(groupPost.getContent(), referers);
		for(String s :referers)
		{
		  User user=userMapper.getUserByNickName(s);
		  if(user!=null)
		  {
			  Notice notice=new Notice();
			  notice.setNoticeType(NoticeTypeEnum.PostAtNotice.getValue());
			  notice.setRefId(groupPost.getId());
			  notice.setUserId(user.getId());
			  notice.setTitle(groupPost.getTitle());
			  noticeMapper.insertNotice(notice);
		  }
		}
	}

    @Transactional
    public boolean recommendGroupPost(long bid,long uid)
    {
    	GroupPost groupPost=groupPostMapper.getGroupPostById(bid);
       if(groupPost!=null)
       {
    	   if(groupPostMapper.isRecommend(bid, uid)>0)
    	   {
    		   return false;
    	   }
    	   else
    	   {
    		   groupPostMapper.recommendGroupPost(bid, uid);
    		   
    		   Activity activity =new Activity();
	   			activity.setActivityType(ActivityTypeEnum.RecommendPost.getValue());
	   			activity.setDescription(groupPost.getContentNoHtml20());
	   			activity.setRefId(groupPost.getId());
	   			activity.setTitle(groupPost.getTitle());
	   			activity.setUserId(groupPost.getPostedByUserId());
	   			activityMapper.insertActivity(activity);
    	       return true;
    	   }
       }
       return false;
    }


    
    @Transactional(readOnly=true)
   	public List<GroupPost> searchGroupPosts(com.sky.sns.mybatis.searchEntity.Group params) {
   		long total=this.groupPostMapper.countGroupPosts(params);
   		params.setTotal(total);
   		return this.groupPostMapper.searchGroupPosts(params);
   	}


    @Transactional(readOnly=true)
	public List<GroupPost> getRecommendPosts(long id, long curPostId,
			int pageStart, int pageSize) {
		return groupPostMapper.getRecommendPosts(id, curPostId, pageStart, pageSize);
	}


    @Transactional(readOnly=true)
	public List<GroupPost> getGroupPostInGroup(long id, int isBest,
			int pageSize, int pageStart) {
		return groupPostMapper.getGroupPostInGroup(id, isBest, pageSize, pageStart);
	}


    @Transactional(readOnly=true)
	public long countGroupPostInGroup(long id, int isBest) {
		return groupPostMapper.countGroupPostInGroup(id, isBest);
	}


    @Transactional(readOnly=true)
	public List<GroupPost> getHottestPostsInPeriod(Date maxDate, Date minDate,int pageSize,int pageStart) {
		return groupPostMapper.getHottestPostsInPeriod(maxDate, minDate,pageSize,pageStart);
	}


    @Transactional(readOnly=true)
	public long countHottestPostsInPeriod(Date maxDate, Date minDate) {
		return groupPostMapper.countHottestPostsInPeriod(maxDate, minDate);
	}


    @Transactional(readOnly=true)
	public List<GroupPost> getGroupPostByUserId(long id, int pageSize,
			int pageStart) {
        return groupPostMapper.getGroupPostByUserId(id, pageSize, pageStart);
	}

	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}


	@Transactional(readOnly=true)
	public List<GroupPost> getAllGroupPosts() {
		return groupPostMapper.getAllGroupPosts();
	}


	@Transactional(readOnly=true)
	public List<GroupPost> getGroupPostsByTagId(long id, int pageStart,
			int pageSize) {
		return groupPostMapper.getGroupPostsByTagId(id, pageStart, pageSize);
	}


	@Transactional(readOnly=true)
	public long countGroupPostsByTagId(long id) {
		return groupPostMapper.countGroupPostsByTagId(id);
	}


	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
}