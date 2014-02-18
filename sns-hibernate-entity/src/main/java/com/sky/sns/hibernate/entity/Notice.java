package com.sky.sns.hibernate.entity;
import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Notice")
public class Notice {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
   private long id;
	
	 @Column(name = "createdDate")
   private Date createdDate;

	@Column(name = "title")
   private String title;
	 
	 
	 @Column(name = "content",length=java.lang.Integer.MAX_VALUE)
   private String content;
   
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
    private User user;

	 @Column(name = "noticeType")
    private int noticeType;
	 
	 @Column(name = "refId")
	private long refId;
	 
	 @Column(name = "isRead")
    private int isRead;
	 
	 @Column(name = "updatedDate")
	   private Date updatedDate;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}

	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	 
	
}
