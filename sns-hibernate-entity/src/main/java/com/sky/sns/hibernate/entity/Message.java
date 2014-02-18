package com.sky.sns.hibernate.entity;
import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Message")
public class Message {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
   private long id;
	
	 @Column(name = "createdDate")
   private Date createdDate;

	 @Column(name = "content",length=java.lang.Integer.MAX_VALUE)
   private String content;
	 
	 @Column(name = "`group`",length=100)
	   private String group;
   
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "fromUserId")
    private User fromUser;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "toUserId")
    private User toUser;
	 
	 @Column(name = "isRead")
	   private int isRead;
	 
	 @Column(name = "isFromDelete")
	   private int isFromDelete;

	 
	 @Column(name = "isToDelete")
	   private int isToDelete;
	 


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
	
	public int getIsFromDelete() {
		return isFromDelete;
	}

	public void setIsFromDelete(int isFromDelete) {
		this.isFromDelete = isFromDelete;
	}

	public int getIsToDelete() {
		return isToDelete;
	}

	public void setIsToDelete(int isToDelete) {
		this.isToDelete = isToDelete;
	}
}
