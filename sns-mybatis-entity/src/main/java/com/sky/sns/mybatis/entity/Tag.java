package com.sky.sns.mybatis.entity;

import java.util.Date;

import com.sky.sns.common.DateUtils;

public class Tag extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5546019225370390447L;

	private long id;

	private Date createdDate;
	private Date updatedDate;
	private String name;

	private String cnSpell;

	private String description;

	private long followCount;

	private int isFollowed;

	private Photo cover;

	private Long photoId;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdByUser
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the letter
	 */
	public String getCnSpell() {
		return cnSpell;
	}

	/**
	 * @param letter
	 *            the letter to set
	 */
	public void setCnSpell(String cnSpell) {
		this.cnSpell = cnSpell;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public String getDescription50() {
		return this.getContentNoHtml(50, description);
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public long getFollowCount() {
		return followCount;
	}

	public void setFollowCount(long followCount) {
		this.followCount = followCount;
	}

	public String getCreatedDateF() {
		return DateUtils.format(createdDate, "yyyy-MM-dd HH:mm:ss");
	}

	public String getUpdatedDateF() {
		return DateUtils.format(updatedDate, "yyyy-MM-dd HH:mm:ss");
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getIsFollowed() {
		return isFollowed;
	}

	public void setIsFollowed(int isFollowed) {
		this.isFollowed = isFollowed;
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
		if (cover != null)
			return cover.getUrlBySize("160");
		else
			return Photo.defaultUrl;
	}

	public String getCover48() {
		if (cover != null)
			return cover.getUrlBySize("48");
		else
			return Photo.defaultUrl;
	}

	public String getCover24() {
		if (cover != null)
			return cover.getUrlBySize("24");
		else
			return Photo.defaultUrl;
	}
}
