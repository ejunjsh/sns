package com.sky.sns.hibernate.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="User")
public class User {
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
     private long id;
	
	@Column(name = "email", unique = true, nullable = false)
     private String email;
	
	@Column(name = "passWord",  nullable = false)
     private String passWord;
	
	@Column(name = "nickName", unique = true, nullable = false)
     private String nickName;
	
	@Column(name = "regesiterDate", nullable = false)
     private Date regesiterDate;
	
	@Column(name = "lastLoginDate", nullable = false)
     private Date lastLoginDate;
	
	@Column(name = "ip", nullable = false)
     private String ip;
	
	@Column(name = "status", nullable = false)
     private int status;
	

	
	 @ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "User_Tag", joinColumns = { @JoinColumn(name = "userId")},inverseJoinColumns={@JoinColumn(name="tagId") })
    private List<Tag> tags;
	 
	 @ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "FollowUser", joinColumns = { @JoinColumn(name = "userId")},inverseJoinColumns={@JoinColumn(name="followUserId") })
    private List<User> followUsers;
	 
	
	@Column(name = "title")
    private String title;
	 
	 @Column(name = "gender")
	 private int gender;
	 
	 @Column(name = "blogUrl")
	 private String blogUrl;
	 
	 @Column(name = "description")
	 private String description;
	 
	 @Column(name = "isWaterMark")
	 private int isWaterMark;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "photoId")
	 private Photo avatar;
	 
	
	public Photo getAvatar() {
		return avatar;
	}
	public void setAvatar(Photo avatar) {
		this.avatar = avatar;
	}
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the regesiterDate
	 */
	public Date getRegesiterDate() {
		return regesiterDate;
	}
	/**
	 * @param regesiterDate the regesiterDate to set
	 */
	public void setRegesiterDate(Date regesiterDate) {
		this.regesiterDate = regesiterDate;
	}
	/**
	 * @return the lastLoginDate
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBlogUrl() {
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsWaterMark() {
		return isWaterMark;
	}
	public void setIsWaterMark(int isWaterMark) {
		this.isWaterMark = isWaterMark;
	}
	
	 public List<User> getFollowUsers() {
			return followUsers;
		}
		public void setFollowUsers(List<User> followUsers) {
			this.followUsers = followUsers;
		}
}
