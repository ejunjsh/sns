package com.sky.sns.mybatis.entity;

import java.util.Date;

import com.sky.sns.common.DateUtils;

public class GroupPostComment extends BaseEntity{
	
	private static final long serialVersionUID = -1035553124292694585L;

	private long id;

     private String content;
     
     private long postedByUserId;
     private User postedByUser;
     

     private Date postedDate;
     
     
     private long groupPostId;
     
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getPostedByUser() {
		return postedByUser;
	}
	public void setPostedByUser(User postedByUser) {
		this.postedByUser = postedByUser;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public String getPostedDateF() {
		return DateUtils.getDaysBeforeNow(postedDate);
	}
	
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public long getPostedByUserId() {
		return postedByUserId;
	}
	public void setPostedByUserId(long postedByUserId) {
		this.postedByUserId = postedByUserId;
	}
	public long getGroupPostId() {
		return groupPostId;
	}
	public void setGroupPostId(long groupPostId) {
		this.groupPostId = groupPostId;
	}

	
	public String getContentNoHtml20() {
		return getContentNoHtml(20,content);
	}
	
	public String getContentWithAtLink()
	{
		return getContentWithAtLink(content);
	}
	
}
