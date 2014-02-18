package com.sky.sns.mybatis.entity;
import java.util.*;

import com.sky.sns.common.DateUtils;
import com.sky.sns.enumeration.*;

public class Question extends BaseEntity{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3928178182427991670L;

	private Long id;

    private String title;
	

    private String content;
	
    private long postedByUserId;
    private User postedByUser;
	

    private Date postedDate;

    private Date updatedDate;
	 
    private int status;
	 
    private long viewCount;
	 
    private int answerCount;
	 
	private Date lockDate; 
	
	private List<Tag> tags;
	
	private String tagString;
	
	private int commentCount;
	
	private int followCount;
	
	private int isFollow;
	

	
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
	 * @return the answerCount
	 */
	public int getAnswerCount() {
		return answerCount;
	}
	/**
	 * @param answerCount the answerCount to set
	 */
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	
	 public Date getLockDate() {
		return lockDate;
	}
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	public long getPostedByUserId() {
		return postedByUserId;
	}
	public void setPostedByUserId(long postedByUserId) {
		this.postedByUserId = postedByUserId;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
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
	

	
	public String getPostedDateF() {
		return DateUtils.getDaysBeforeNow(postedDate);
	}
	
	public String getUpdatedDateF() {
		return DateUtils.getDaysBeforeNow(updatedDate);
	}
	
	 public String getStatusName()
     {
		 if(status>0)
   	      return StatusEnum.valueOf(status).toString();
   	 else
   		 return null;
     }
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	public int getIsFollow() {
		return isFollow;
	}
	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}

	 
	public String getContentWithAtLink()
	{
		return getContentWithAtLink(content);
	}
	 
	public String getContentNoHtml() {
		return getContentNoHtml(-1,content);
	}
	
	public DocumentEntity convertToDoc()
	{
		if(this!=null)
		{
			DocumentEntity doc=new DocumentEntity();
			doc.setContent(this.getContentNoHtml());
			doc.setDate(this.getUpdatedDate());
			doc.setId("Q"+this.getId());
			doc.setRemark("");
			doc.setTagString(this.getTagString());
			doc.setTitle(this.getTitle());
			doc.setType(DocTypeEnum.Question.getValue());
			doc.setFromName("");
			doc.setFromValue(0);
			return doc;
		}
		return null;
	}
}

