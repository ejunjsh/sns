package com.sky.sns.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Blog;
import com.sky.sns.mybatis.entity.BlogComment;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IBlogCommentService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.BlogCommentMapper;
import com.sky.sns.mybatis.mapper.BlogMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

public class BlogCommentService implements IBlogCommentService {


	private BlogCommentMapper blogCommentMapper;
	private BlogMapper blogMapper;
	private ActivityMapper activityMapper;
	private NoticeMapper noticeMapper;
	private UserMapper userMapper;
	
 
	@Transactional 
	public void insertBlogComment(BlogComment qc) {
		
		
		Blog blog=blogMapper.getBlogById(qc.getBlogId());
		if(blog!=null)
		{
			blogCommentMapper.insertBlogComment(qc);
			
			Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.CommentBlog.getValue());
			activity.setDescription(qc.getContentNoHtml100());
			activity.setRefId(qc.getId());
			activity.setTitle(blog.getTitle());
			activity.setUserId(qc.getPostedByUserId());
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(qc.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.BlogCommentAtNotice.getValue());
				  notice.setRefId(qc.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(blog.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
		}
	}
	  

	  @Transactional   
	public void deleteBlogComment(long id) {
		  BlogComment bc=blogCommentMapper.getBlogCommentById(id);
		  if(bc!=null)
		  {
		  Blog blog=blogMapper.getBlogById(bc.getBlogId());
			if(blog!=null)
			{
				blogCommentMapper.deleteBlogComment(id);
			}
		  }
	}

	  @Transactional(readOnly=true)
	public List<BlogComment> getBlogCommentByBlogId(long blogId) {

		return blogCommentMapper.getBlogCommentByBlogId(blogId);
	}


	public void setBlogCommentMapper(BlogCommentMapper blogCommentMapper) {
		this.blogCommentMapper = blogCommentMapper;
	}



	public void setBlogMapper(BlogMapper blogMapper) {
		this.blogMapper = blogMapper;
	}




	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}


	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}



	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	

}
