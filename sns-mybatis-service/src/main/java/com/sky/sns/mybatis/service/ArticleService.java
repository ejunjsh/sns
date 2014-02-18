package com.sky.sns.mybatis.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Article;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IArticleService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.ArticleMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.TagMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

public class ArticleService implements IArticleService {
	
	 private ArticleMapper articleMapper;
	 private ActivityMapper activityMapper;
	 private UserMapper userMapper;
	 private NoticeMapper noticeMapper;

    
	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}

	public void setArticleMapper(ArticleMapper articleMapper) {
		this.articleMapper = articleMapper;
	}

	private TagMapper tagMapper;

		public void setTagMapper(TagMapper tagMapper) {
			this.tagMapper = tagMapper;
		}

	
	    @Transactional   
    public void insertArticle(Article article)
	    {
	    	articleMapper.insertArticle(article);
	    	List<Tag> tags=article.getTags();
	    	if(tags!=null&&tags.size()>0)
	    	{
	    		for(Tag t:tags)
	    		{
	    			articleMapper.addTags(article.getId(), t.getId());
	    		}
	    	}
	    	
	    	Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.NewArticle.getValue());
			activity.setDescription(article.getContentNoHtml50());
			activity.setRefId(article.getId());
			activity.setTitle(article.getTitle());
			activity.setUserId(article.getPostedByUserId());
			activity.setPic(article.getCover160());
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(article.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.ArticleAtNotice.getValue());
				  notice.setRefId(article.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(article.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
	    }
    
	    @Transactional(readOnly=true)
    public Article getArticleById(long id)
    {
    	return articleMapper.getArticleById(id);
    }
    
	    @Transactional
    public void updateArticle(Article article)
    {
	    articleMapper.updateArticle(article);
	    
    }
    


    @Transactional
	public void updateArticleForTags(Article article, String[] ts) {
		for (String s : ts) {
			boolean isNew = true;
			for (Tag t :article.getTags()) {
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
					articleMapper.addTags(article.getId(), tag.getId());
				}
			}
		}
		for (int i = 0; i < article.getTags()
				.size(); i++) {
			Tag t = article.getTags().get(i);
			boolean isDelete = true;
			for (String s : ts) {
				if (Integer.parseInt(s.trim()) == t
						.getId()) {
					isDelete = false;
					break;
				}
			}
			if (isDelete) {
				articleMapper.deleteTags(article.getId(), t.getId());
			}
		}
		articleMapper.updateArticle(article);
		
		List<String> referers=new ArrayList<String>();
		StringConversion.generateRefererLinks(article.getContent(), referers);
		for(String s :referers)
		{
		  User user=userMapper.getUserByNickName(s);
		  if(user!=null)
		  {
			  Notice notice=new Notice();
			  notice.setNoticeType(NoticeTypeEnum.ArticleAtNotice.getValue());
			  notice.setRefId(article.getId());
			  notice.setUserId(user.getId());
			  notice.setTitle(article.getTitle());
			  noticeMapper.insertNotice(notice);
		  }
		}
	}

    @Transactional
    public boolean recommendArticle(long aid,long uid)
    {
    	Article article=articleMapper.getArticleById(aid);
       if(article!=null)
       {
    	   if(articleMapper.isRecommend(aid, uid)>0)
    	   {
    		   return false;
    	   }
    	   else
    	   {
    		   articleMapper.recommendArticle(aid, uid);
    		   
    		     Activity activity =new Activity();
  				activity.setActivityType(ActivityTypeEnum.RecommendActicle.getValue());
  				activity.setDescription(article.getContentNoHtml50());
  				activity.setRefId(aid);
  				activity.setTitle(article.getTitle());
  				activity.setUserId(uid);
  				activity.setPic(article.getCover160());
  				activityMapper.insertActivity(activity);
  			
    		   
    	       return true;
    	   }
       }
       return false;
    }
    
    @Transactional(readOnly=true)
   	public List<Article> searchArticles(com.sky.sns.mybatis.searchEntity.Article article) {
   		long total=this.articleMapper.countArticles(article);
   		article.setTotal(total);
   		return this.articleMapper.searchArticles(article);
   	}


    @Transactional(readOnly=true)
	public Article getNextArticle(long id) {
		return articleMapper.getNextArticle(id);
	}


    @Transactional(readOnly=true)
	public Article getPreviousArticle(long id) {
		return articleMapper.getPreviousArticle(id);
	}


    @Transactional(readOnly=true)
	public List<Article> getArticleInTopic(long id, int pageStart, int pageSize) {
		return articleMapper.getArticleInTopic(id, pageStart, pageSize);
	}

    @Transactional(readOnly=true)
	public List<Article> getAllArticles() {
		return articleMapper.getAllArticles();
	}

    @Transactional(readOnly=true)
	public List<Article> getArticlesByTagId(long id, int pageStart, int pageSize) {
		return articleMapper.getArticlesByTagId(id, pageStart, pageSize);
	}

    @Transactional(readOnly=true)
	public long countArticlesByTagId(long id) {
		return articleMapper.countArticlesByTagId(id);
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
}
