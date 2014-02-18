package com.sky.sns.mybatis.entity;



import java.util.*;

import com.sky.sns.common.DateUtils;
import com.sky.sns.enumeration.*;

public class GroupPost  extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8522475758575630761L;


	private Long id;
	

    private String title;
	
	
    private String content;
	
    private long postedByUserId;
    private User postedByUser;
	 
    private Long groupId;
    private Group group;


    private Date postedDate;
	 

    private Date updatedDate;
	 
 private int status;
	 

    private long viewCount;
	 
    private long recommendCount;
	
    private List<Tag> tags;
    
    private int isTop;
    
    private int isBest;
    
    private Date lastCommentDate;
	 

	
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
	

	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public int getIsBest() {
		return isBest;
	}
	public void setIsBest(int isBest) {
		this.isBest = isBest;
	}

	public Date getLastCommentDate() {
		  return lastCommentDate;
		}
	public String getLastCommentDateF() {
	  return DateUtils.format(lastCommentDate, "yyyy-MM-dd HH:mm:ss");
	}
	public void setLastCommentDate(Date lastCommentDate) {
		this.lastCommentDate = lastCommentDate;
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
			doc.setFromName(this.getGroup().getName());
			doc.setFromValue(this.getGroupId());
			doc.setId("P"+this.getId());
			doc.setRemark("");
			doc.setTagString(this.getTagString());
			doc.setTitle(this.getTitle());
			doc.setType(DocTypeEnum.Post.getValue());
			return doc;
		}
		return null;
	}
}
