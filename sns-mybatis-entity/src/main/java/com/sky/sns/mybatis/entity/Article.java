package com.sky.sns.mybatis.entity;



import java.util.*;

import com.sky.sns.common.DateUtils;
import com.sky.sns.enumeration.*;


public class Article extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8522475758575630761L;


	private Long id;
	

    private String title;

	
    private String content;
	
    private long postedByUserId;
    private User postedByUser;
	 
    private Long articleTopicId;
    private ArticleTopic articleTopic;


    private Date postedDate;
	 

    private Date updatedDate;
	 
 private int status;
	 

    private long viewCount;
	 
    private long recommendCount;
	
    private List<Tag> tags;
    
    private Photo cover;
    
    private Long photoId;
	 

	
	    private int commentCount;
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the postedByUser
	 */
	public User getPostedByUser() {
		return postedByUser;
	}
	/**
	 * @param postedByUser the postedByUser to set
	 */
	public void setPostedByUser(User postedByUser) {
		this.postedByUser = postedByUser;
	}
	/**
	 * @return the postedDate
	 */
	public Date getPostedDate() {
		return postedDate;
	}
	/**
	 * @param postedDate the postedDate to set
	 */
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the viewCount
	 */
	public long getViewCount() {
		return viewCount;
	}
	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	

	public long getPostedByUserId() {
		return postedByUserId;
	}
	public void setPostedByUserId(long postedByUserId) {
		this.postedByUserId = postedByUserId;
	}

	
	 public long getRecommendCount() {
		return recommendCount;
	}
	public void setRecommendCount(long recommendCount) {
		this.recommendCount = recommendCount;
	}
	
	public String getStatusName()
    {
		 if(status>0)
  	      return StatusEnum.valueOf(status).toString();
  	 else
  		 return null;
    }
	
	public Long getArticleTopicId() {
		return articleTopicId;
	}
	public void setArticleTopicId(Long articleTopicId) {
		this.articleTopicId = articleTopicId;
	}
	public ArticleTopic getArticleTopic() {
		return articleTopic;
	}
	public void setArticleTopic(ArticleTopic articleTopic) {
		this.articleTopic = articleTopic;
	}
	public String getPostedDateF() {
		return DateUtils.getDaysBeforeNow(postedDate);
	}
	
	public String getUpdatedDateF() {
		return DateUtils.getDaysBeforeNow(updatedDate);
	}
	
	public String getTagString() {
		if(tags!=null&&tags.size()>0)
		{
			String s="";
			for(Tag t: tags)
			{
				s+=t.getName()+",";
			}
			if(!s.isEmpty())
			{
				s.substring(0, s.length()-1);
				return s;
			}
		}
		return null;
	}
	
	public String getContentNoHtml20() {
		return getContentNoHtml(20,content);
	}
	
	
	public String getContentNoHtml50() {
		return getContentNoHtml(50,content);
	}
	
	public String getContentNoHtml100() {
		return getContentNoHtml(100,content);
	}
	
	public String getContentNoHtml() {
		return getContentNoHtml(-1,content);
	}
	
	public String getContentWithAtLink()
	{
		return getContentWithAtLink(content);
	}
	
	public DocumentEntity convertToDoc()
	{
		if(this!=null)
		{
			DocumentEntity doc=new DocumentEntity();
			doc.setContent(this.getContentNoHtml());
			doc.setDate(this.getUpdatedDate());
			doc.setFromName(this.articleTopic.getName());
			doc.setFromValue(this.articleTopicId);
			doc.setId("A"+this.getId());
			doc.setRemark("");
			doc.setTagString(this.getTagString());
			doc.setTitle(this.getTitle());
			doc.setType(DocTypeEnum.Article.getValue());
			return doc;
		}
		return null;
	}
	public Photo getCover() {
		return cover;
	}
	public void setCover(Photo cover) {
		this.cover = cover;
	}
	public Long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	
	public String getCover160() {
		if(cover!=null)
        return cover.getUrlBySize("160");
		else
        return Photo.defaultUrl;
	}
	
	
	public String getCover48() {
		if(cover!=null)
	        return cover.getUrlBySize("48");
			else
	        return Photo.defaultUrl;
	}
	
	public String getCover24() {
		if(cover!=null)
	        return cover.getUrlBySize("24");
			else
	        return Photo.defaultUrl;
	}
}
