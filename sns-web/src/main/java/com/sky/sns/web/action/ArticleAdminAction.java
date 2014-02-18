package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.web.pojo.AjaxCode;
import com.sky.sns.web.utility.ImageUtils;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.*;
import com.sky.sns.mybatis.iService.*;

@Controller
@Scope("prototype")
public class ArticleAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -1591591128655797554L;
    
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IArticleTopicService articleTopicService;
	
	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private IPhotoService photoService;
	
	@Autowired
	private ITagService tagService;
	
	private com.sky.sns.mybatis.searchEntity.Article params;
	private List<ArticleTopic> topics;
	private List<Article> articles;
	
	private Map<Integer,String> statusMap;
	private String tags;
	private Article article;
	private ArticleTopic topic;
	private int searchType;
	
	private String tmpUrl;
	
	public Map<Integer,String> getStatusMap() {
		return StatusEnum.toMap();
	}



	public String searchArticle() throws Exception{
	    	if(params==null)
	    		params =new com.sky.sns.mybatis.searchEntity.Article();

	    	if(params.getPageIndex()>0)
	    		params.setPageIndex(params.getPageIndex()-1);
	         setArticles(articleService.searchArticles(params));
	         setModule("searchArticle");
	 		 return "page";
	    }

	  public String doArticle() throws Exception{
		  setModule("doArticle");
			if (isPost()) {
				if (curUser != null) {
					if (!tags.isEmpty()) {
						List<Tag> newTags = new ArrayList<Tag>();
						String[] ts = tags.split(",");
						if (article != null && article.getId() == null) {
							for (String s : ts) {
								Tag tag = tagService.getTagById(Integer.parseInt(s
										.trim()));
								if (tag != null) {
									newTags.add(tag);
								}
							}
							article.setRecommendCount(0);
							article.setPostedByUserId(curUser.getId());
							article.setPostedDate(new Date());
							article.setStatus(StatusEnum.NORMAL.getValue());
							article.setUpdatedDate(new Date());
							article.setViewCount(0);
							article.setTags(newTags);
							articleService.insertArticle(article);
						} else {
							if (article.getId() > 0) {
								Article editArticle = articleService
										.getArticleById(article.getId());
								if (editArticle != null) {
									editArticle.setTitle(article.getTitle());
									editArticle.setContent(article
												.getContent());
									if(tmpUrl!=null&&!tmpUrl.isEmpty())
									{
						                String url=ImageUtils.SaveToAvatar(tmpUrl, "article",curUser.getIsWaterMark()==1);
						    			
						                List<Album> albums=albumService.getAlbumsByUserId(curUser.getId(),0, Integer.MAX_VALUE);
										
										Album album=null;
										for(Album a : albums)
										{
											if(a.getIsFixed()==1&&a.getTitle().equalsIgnoreCase("默认相册"))
											{
												album=a;
												break;
											}
										}
										if(album==null)
										{
											album=new Album();
											album.setDescription("默认相册");
											album.setIsFixed(1);
											album.setTitle("默认相册");
											album.setUserId(curUser.getId());
											albumService.insertAlbum(album);
										}
										
										Photo photo=new Photo();
										photo.setAlbumId(album.getId());
										photo.setDescription("文章封面");
										photo.setTitle("文章封面");
										photo.setUrl(url);
										photo.setUserId(curUser.getId());
										photoService.insertPhoto(photo);
										editArticle.setPhotoId(photo.getId());
									}
									editArticle.setUpdatedDate(new Date());
									editArticle.setArticleTopicId(article.getArticleTopicId());
									articleService.updateArticleForTags(editArticle,ts);
								}
							}
						}
					}
					jsonData.put("status", AjaxCode.successful);
					return "json";
				} else {
					return ERROR;
				}

			} else {
				if (article != null) {
					if (article.getId() != null) {
						article = articleService.getArticleById(article.getId());
					}
				}
				return "page";
			}
	  }
	  
	 
	  public String searchArticleTopic() throws Exception{
	    	if(params==null)
	    		params =new com.sky.sns.mybatis.searchEntity.Article();

	    	if(params.getPageIndex()>0)
	    		params.setPageIndex(params.getPageIndex()-1);
	         setTopics(articleTopicService.searchArticleTopic(params));
	         setModule("searchArticleTopic");
	 		 return "page";
	    }
	  

	  public String doArticleTopic() throws Exception{
		  setModule("doArticleTopic");
			if (isPost()) {
				if (curUser != null) {
					if (topic != null && topic.getId() == null){
						  if(articleTopicService.isExist(topic.getName(),0))
					      {
					          jsonData.put("status", AjaxCode.getFail);
					          jsonData.put("message","已存在主题名");
					          return "json";
					     }
						  else
						  {
							topic.setCreatedDate(new Date());
							topic.setUpdatedDate(new Date());
							articleTopicService.insertArticleTopic(topic);
						  }
						} else {
							if (topic.getId() > 0) {
								 if(!articleTopicService.isExist(topic.getName(),topic.getId()))
							      {
								ArticleTopic editTopic = articleTopicService
										.getArticleTopicById(topic.getId());
								if (editTopic != null) {
									editTopic.setName(topic.getName());
									editTopic.setDescription(topic.getDescription());
									if(tmpUrl!=null&&!tmpUrl.isEmpty())
									{
						                String url=ImageUtils.SaveToAvatar(tmpUrl, "article",curUser.getIsWaterMark()==1);
						    			
						                List<Album> albums=albumService.getAlbumsByUserId(curUser.getId(),0, Integer.MAX_VALUE);
										
										Album album=null;
										for(Album a : albums)
										{
											if(a.getIsFixed()==1&&a.getTitle().equalsIgnoreCase("默认相册"))
											{
												album=a;
												break;
											}
										}
										if(album==null)
										{
											album=new Album();
											album.setDescription("默认相册");
											album.setIsFixed(1);
											album.setTitle("默认相册");
											album.setUserId(curUser.getId());
											albumService.insertAlbum(album);
										}
										
										Photo photo=new Photo();
										photo.setAlbumId(album.getId());
										photo.setDescription("文章封面");
										photo.setTitle("文章封面");
										photo.setUrl(url);
										photo.setUserId(curUser.getId());
										photoService.insertPhoto(photo);
										editTopic.setPhotoId(photo.getId());
									}
									editTopic.setUpdatedDate(new Date());
									articleTopicService.updateArticleTopic(editTopic);
								}
							      }
								 else
								 {
									  jsonData.put("status", AjaxCode.getFail);
							          jsonData.put("message","已存在主题名");
							          return "json";
								 }
							}
						}
					jsonData.put("status", AjaxCode.successful);
					return "json";
				} else {
					return ERROR;
				}
				}
	  
else {
				if (topic != null) {
					if (topic.getId() != null) {
						topic = articleTopicService
								.getArticleTopicById(topic.getId());
					}
				}
				return "page";
			}
	  }

	public com.sky.sns.mybatis.searchEntity.Article getParams() {
		return params;
	}



	public void setParams(com.sky.sns.mybatis.searchEntity.Article params) {
		this.params = params;
	}



	public List<Article> getArticles() {
		return articles;
	}



	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public String getTags() {
		return tags;
	}



	public void setTags(String tags) {
		this.tags = tags;
	}



	public Article getArticle() {
		return article;
	}



	public void setArticle(Article article) {
		this.article = article;
	}



	public List<ArticleTopic> getTopics() {
		return topics;
	}



	public void setTopics(List<ArticleTopic> topics) {
		this.topics = topics;
	}



	public ArticleTopic getTopic() {
		return topic;
	}



	public void setTopic(ArticleTopic topic) {
		this.topic = topic;
	}





	public int getSearchType() {
		return searchType;
	}



	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}



	public String getTmpUrl() {
		return tmpUrl;
	}



	public void setTmpUrl(String tmpUrl) {
		this.tmpUrl = tmpUrl;
	}
	
}
