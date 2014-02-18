package com.sky.sns.mybatis.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Blog;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IBlogService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.BlogMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.TagMapper;
import com.sky.sns.mybatis.mapper.UserMapper;


public class BlogService implements IBlogService {
	
	 private BlogMapper blogMapper;
	 private TagMapper tagMapper;
	 private ActivityMapper activityMapper;
	 private UserMapper userMapper;
	 private NoticeMapper noticeMapper;
	 
	    public void setBlogMapper(BlogMapper blogMapper) {
			this.blogMapper = blogMapper;
		}
		public void setTagMapper(TagMapper tagMapper) {
			this.tagMapper = tagMapper;
		}

	
	    @Transactional   
    public void insertBlog(Blog blog)
	    {
	    	blogMapper.insertBlog(blog);
	    	List<Tag> tags=blog.getTags();
	    	if(tags!=null&&tags.size()>0)
	    	{
	    		for(Tag t:tags)
	    		{
	    			blogMapper.addTags(blog.getId(), t.getId());
	    		}
	    	}
	    	
	    	Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.NewBlog.getValue());
			activity.setDescription(blog.getContentNoHtml100());
			activity.setRefId(blog.getId());
			activity.setTitle(blog.getTitle());
			activity.setUserId(blog.getPostedByUserId());
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(blog.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.BlogAtNotice.getValue());
				  notice.setRefId(blog.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(blog.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
	    }
    
	    @Transactional(readOnly=true)
    public Blog getBlogById(long id)
    {
    	return blogMapper.getBlogById(id);
    }
    
	    @Transactional
    public void updateBlog(Blog blog)
    {
	     blogMapper.updateBlog(blog);
    }
    


    @Transactional
	public void updateBlogForTags(Blog blog, String[] ts) {
		for (String s : ts) {
			boolean isNew = true;
			for (Tag t : blog.getTags()) {
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
					blogMapper.addTags(blog.getId(), tag.getId());
				}
			}
		}
		for (int i = 0; i < blog.getTags()
				.size(); i++) {
			Tag t = blog.getTags().get(i);
			boolean isDelete = true;
			for (String s : ts) {
				if (Integer.parseInt(s.trim()) == t
						.getId()) {
					isDelete = false;
					break;
				}
			}
			if (isDelete) {
				blogMapper.deleteTags(blog.getId(), t.getId());
			}
		}
		blogMapper.updateBlog(blog);
		
		List<String> referers=new ArrayList<String>();
		StringConversion.generateRefererLinks(blog.getContent(), referers);
		for(String s :referers)
		{
		  User user=userMapper.getUserByNickName(s);
		  if(user!=null)
		  {
			  Notice notice=new Notice();
			  notice.setNoticeType(NoticeTypeEnum.BlogAtNotice.getValue());
			  notice.setRefId(blog.getId());
			  notice.setUserId(user.getId());
			  notice.setTitle(blog.getTitle());
			  noticeMapper.insertNotice(notice);
		  }
		}
		
	}

    @Transactional
    public boolean recommendBlog(long bid,long uid)
    {
       Blog blog=blogMapper.getBlogById(bid);
       if(blog!=null)
       {
    	   if(blogMapper.isRecommend(bid, uid)>0)
    	   {
    		   return false;
    	   }
    	   else
    	   {
    		   blogMapper.recommendBlog(bid, uid);

   				Activity activity =new Activity();
   				activity.setActivityType(ActivityTypeEnum.RecommendBlog.getValue());
   				activity.setDescription(blog.getContentNoHtml100());
   				activity.setRefId(bid);
   				activity.setTitle(blog.getTitle());
   				activity.setUserId(uid);
   				activityMapper.insertActivity(activity);
   			
    		   
    	       return true;
    	   }
       }
       return false;
    }
    
    @Transactional(readOnly=true)
   	public List<Blog> searchBlogs(com.sky.sns.mybatis.searchEntity.Blog blog) {
   		long total=this.blogMapper.countBlogs(blog);
   		blog.setTotal(total);
   		return this.blogMapper.searchBlogs(blog);
   	}
    @Transactional(readOnly=true)
	public List<Blog> getMyBlogByCategoryId(long id, Long blogCategoryId,
			int pageStart, int pageSize) {
		return this.blogMapper.getMyBlogByCategoryId(id, blogCategoryId, pageStart, pageSize);
	}
    @Transactional(readOnly=true)
	public long countMyBlogByCategoryId(long id, Long blogCategoryId) {
		return this.blogMapper.countMyBlogByCategoryId(id, blogCategoryId);
	}
    
	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}
	
	 @Transactional(readOnly=true)
	public List<Blog> getAllBlogs() {
		return blogMapper.getAllBlogs();
	}
	 @Transactional(readOnly=true)
	public List<Blog> getBlogsByTagId(long id, int pageStart, int pageSize) {
		return blogMapper.getBlogsByTagId(id, pageStart, pageSize);
	}
	 @Transactional(readOnly=true)
	public long countBlogsByTagId(long id) {
		return blogMapper.countBlogsByTagId(id);
	}
	 @Transactional(readOnly=true)
	public List<Blog> getAllBlogsPage(int pageStart, int pageSize) {
		return blogMapper.getAllBlogsPage(pageStart, pageSize);
	}
	 @Transactional(readOnly=true)
	public long countAllBlogs() {
		return blogMapper.countAllBlogs();
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
	 @Transactional(readOnly=true)
	public List<Blog> getOtherBlogs(long blogId, long userId, int count) {
		return blogMapper.getOtherBlogs(blogId, userId, count);
	}
}
