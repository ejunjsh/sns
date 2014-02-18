package com.sky.sns.mybatis.entity;



import java.util.*;

import com.sky.sns.common.DateUtils;



public class ArticleTopic extends BaseEntity{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2509273836649311498L;


	private Long id;
	

    private String name;
	
    private String description;
	

    private Date createdDate;
	 

    private Date updatedDate;
	 
	 

	 private int articleCount;


	    private Photo cover;
	    
	    private Long photoId;

	
	
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

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public String getCreatedDateF() {
		return DateUtils.format(createdDate, "yyyy-MM-dd HH:mm:ss");
	}
	
	public String getUpdatedDateF() {
		return DateUtils.format(updatedDate, "yyyy-MM-dd HH:mm:ss");
	}
	public Photo getCover() {
		return cover;
	}
	public void setCover(Photo cover) {
		this.cover = cover;
	}
	public Long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	
	public String getCover160() {
		if(cover!=null)
        return cover.getUrlBySize("160");
		else
        return Photo.defaultUrl;
	}
	
	
	public String getCover48() {
		if(cover!=null)
	        return cover.getUrlBySize("48");
			else
	        return Photo.defaultUrl;
	}
	
	public String getCover24() {
		if(cover!=null)
	        return cover.getUrlBySize("24");
			else
	        return Photo.defaultUrl;
	}

}
