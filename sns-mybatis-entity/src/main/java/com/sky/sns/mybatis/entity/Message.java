package com.sky.sns.mybatis.entity;
import java.util.Date;

import com.sky.sns.common.DateUtils;


public class Message extends BaseEntity{
	
	
   /**
	 * 
	 */
	private static final long serialVersionUID = -7090361244382785567L;


private long id;
	
	
   private Date createdDate;

	
   private String content;
	 
	
	   private String group;
   
	 
    private User fromUser;
    
    private long fromUserId;

	private long toUserId;
	
    private User toUser;
	 
	
	   private int isRead;
	   
	   private int messageCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	
	public String getCreatedDateF() {
		return DateUtils.getDaysBeforeNow(createdDate);
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public long getToUserId() {
		return toUserId;
	}

	public void setToUserId(long toUserId) {
		this.toUserId = toUserId;
	}
	


	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
}
