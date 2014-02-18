package com.sky.sns.hibernate.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="`Group`")
public class Group {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
   private long id;
	
	 @Column(name = "createdDate")
   private Date createdDate;
	 
	 @Column(name = "updatedDate")
   private Date updatedDate;

	@Column(name = "name", unique = true)
   private String name;
	
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "photoId")
	 private Photo cover;
	 
	 @Column(name = "description",length=java.lang.Integer.MAX_VALUE)
   private String description;
   
	 @Column(name = "reason",length=java.lang.Integer.MAX_VALUE)
	private String reason;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "createdByUserId")
     private User createdByUser;
	 
	 @Column(name = "status")
	private int status;
	 
	 @Column(name = "isOpenContent")
	 private int isOpenContent;
	 
	 @Column(name = "isNeedValidate")
	 private int isNeedValidate;
	 
	 @ManyToMany(cascade = CascadeType.ALL)  
	@JoinTable(name = "Group_User_join", joinColumns = { @JoinColumn(name = "groupId")},inverseJoinColumns={@JoinColumn(name="userId") })
	private List<User> joinedUsers;

	public List<User> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}

	public int getIsOpenContent() {
		return isOpenContent;
	}

	public void setIsOpenContent(int isOpenContent) {
		this.isOpenContent = isOpenContent;
	}

	public int getIsNeedValidate() {
		return isNeedValidate;
	}

	public void setIsNeedValidate(int isNeedValidate) {
		this.isNeedValidate = isNeedValidate;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	 
	 public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Photo getCover() {
		return cover;
	}

	public void setCover(Photo cover) {
		this.cover = cover;
	}

}
