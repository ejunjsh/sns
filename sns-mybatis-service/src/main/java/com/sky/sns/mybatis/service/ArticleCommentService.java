package com.sky.sns.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Article;
import com.sky.sns.mybatis.entity.ArticleComment;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IArticleCommentService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.ArticleCommentMapper;
import com.sky.sns.mybatis.mapper.ArticleMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

public class ArticleCommentService implements IArticleCommentService {


	private ArticleCommentMapper articleCommentMapper;
	private ArticleMapper articleMapper;
	private ActivityMapper activityMapper;
	private NoticeMapper noticeMapper;
	private UserMapper userMapper;
 
	@Transactional 
	public void insertArticleComment(ArticleComment ac) {
		
		
		Article article=articleMapper.getArticleById(ac.getArticleId());
		if(article!=null)
		{
			articleCommentMapper.insertArticleComment(ac);
			
			Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.CommentArticle.getValue());
			activity.setDescription(ac.getContentNoHtml50());
			activity.setRefId(ac.getId());
			activity.setTitle(article.getTitle());
			activity.setUserId(ac.getPostedByUserId());
			activity.setPic(article.getCover().getUrlBySize("160"));
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(ac.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.ArticleCommentAtNotice.getValue());
				  notice.setRefId(ac.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(article.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
		}
	}
	  

	  @Transactional   
	public void deleteArticleComment(long id) {
		  ArticleComment ac=articleCommentMapper.getArticleCommentById(id);
		  if(ac!=null)
		  {
			  Article article=articleMapper.getArticleById(ac.getArticleId());
			if(article!=null)
			{
				articleCommentMapper.deleteArticleComment(id);
			}
		  }
	}

	  @Transactional(readOnly=true)
	public List<ArticleComment> getArticleCommentByArticleId(long articleId,int pageStart,int pageSize) {
		return articleCommentMapper.getArticleCommentByArticleId(articleId,pageStart,pageSize);
	}
	  
	  @Transactional(readOnly=true)
    public int countArticleCommentsByArticleId(long articleId)
    {
    	return articleCommentMapper.countArticleCommentsByArticleId(articleId);
    }
	public void setArticleCommentMapper(ArticleCommentMapper articleCommentMapper) {
		this.articleCommentMapper = articleCommentMapper;
	}


	public void setArticleMapper(ArticleMapper articleMapper) {
		this.articleMapper = articleMapper;
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
