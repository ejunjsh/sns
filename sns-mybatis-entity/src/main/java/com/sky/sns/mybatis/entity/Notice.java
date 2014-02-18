package com.sky.sns.mybatis.entity;


import java.util.Date;

import com.sky.sns.common.DateUtils;




public class Notice extends BaseEntity{
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 809927796082678312L;


private long id;
	

   private Date createdDate;
   
   private Date updatedDate;

   private String title;
	 
	 

   private String content;
   

    private Long userId;

    private int noticeType;

	private Long refId;
	
	private int isRead;
	
	private int count;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	 
	public String getCreatedDateF() {
		return DateUtils.getDaysBeforeNow(createdDate);
	}
	
	public String getUpdatedDateF() {
		return DateUtils.getDaysBeforeNow(updatedDate);
	}
	
	public String getContentNoHtml20() {
		return getContentNoHtml(20,content);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

