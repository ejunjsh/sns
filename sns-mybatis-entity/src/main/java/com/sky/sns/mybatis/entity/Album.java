package com.sky.sns.mybatis.entity;
import java.util.Date;

import com.sky.sns.common.DateUtils;

public class Album  extends BaseEntity{
	

   /**
	 * 
	 */
	private static final long serialVersionUID = -889672481756129981L;

private long id;
	
   private Date createdDate;
	 
	   private Date updatedDate;

   private String title;
	 
   private String description;
   
    private User user;
    
    private long userId;
    
    private int isFixed;
     
    private int isPrivate;
    
    private Photo cover;
    
    private int photoCount;


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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Photo getCover() {
		return cover;
	}

	public void setCover(Photo cover) {
		this.cover = cover;
	}

	public int getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}
	
	public String getUpdatedDateF() {
		return DateUtils.getDaysBeforeNow(updatedDate);
	}
	
	public String getCoverThumbnail() {
		if(cover!=null)
	        return cover.getUrlBySize("thumbnail");
			else
	        return Photo.defaultUrl;
	}
}
