package com.sky.sns.hibernate.entity;
import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Album")
public class Album {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
   private long id;
	
	 @Column(name = "createdDate")
   private Date createdDate;
	 
	 @Column(name = "updatedDate")
	   private Date updatedDate;

	@Column(name = "title")
   private String title;
	 
	 
	 @Column(name = "description",length=java.lang.Integer.MAX_VALUE)
   private String description;
   
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
    private User user;
	 
	 @Column(name = "isFixed")
	 private int isFixed;
	 
	 @Column(name = "isPrivate")
	 private int isPrivate;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getIsFixed() {
		return isFixed;
	}

	public void setIsFixed(int isFixed) {
		this.isFixed = isFixed;
	}

	public int getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(int isPrivate) {
		this.isPrivate = isPrivate;
	}
}
