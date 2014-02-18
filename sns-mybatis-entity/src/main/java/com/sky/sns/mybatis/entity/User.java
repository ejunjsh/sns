package com.sky.sns.mybatis.entity;

import java.util.Date;


import com.sky.sns.common.DateUtils;

import com.sky.sns.enumeration.*;


public class User extends BaseEntity{

     /**
	 * 
	 */
	private static final long serialVersionUID = 2050434329433821776L;


	private long id;
	
	
     private String email;
	
	
     private String passWord;
	
	
     private String nickName;
	
	
     private Date regesiterDate;
	
	
     private Date lastLoginDate;
	
	
     private String ip;
	
	
     private int status;
     

     private String title;
 	 

 	 private int gender;
 	 

 	 private String blogUrl;
 	 

 	 private String description;
 	 

 	 private int isWaterMark;
 	 
 	private Photo avatar;
 	
 	private Long photoId;
 	
 	private int followingCount;
 	private int followedCount;
 	
 	private int blogCount;
 	
 	private int answerCount;
 	
 	private int questionCount;
 	
 	private int postCount;
 	
 	private int tagCount;
 	
 	private int activityCount;
 	
 	private int groupCount;
 	
 	private int photoCount;
 	
 	private int isFollow;
 	
	
     public String getStatusName()
     {
    	 if(status>0)
    	      return StatusEnum.valueOf(status).toString();
    	 else
    		 return null;
     }
	
   
	/**
	 * @return the id
	 */
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
	
	public String getRegesiterDateF() {
		return DateUtils.format(regesiterDate, "yyyy-MM-dd HH:mm:ss");
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
	
	public String getLastLoginDateF() {
		return DateUtils.format(lastLoginDate, "yyyy-MM-dd HH:mm:ss");
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
		if(description!=null&&!description.isEmpty())
		    return description;
		else
			return "这个人很懒。。。";
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

	
	public String getAvatar160() {
		if(avatar!=null)
        return avatar.getUrlBySize("160");
		else
        return Photo.defaultUrl;
	}
	
	
	public String getAvatar48() {
		if(avatar!=null)
	        return avatar.getUrlBySize("48");
			else
	        return Photo.defaultUrl;
	}
	
	public String getAvatar24() {
		if(avatar!=null)
	        return avatar.getUrlBySize("24");
			else
	        return Photo.defaultUrl;
	}




	public int getFollowingCount() {
		return followingCount;
	}


	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}


	public int getFollowedCount() {
		return followedCount;
	}


	public void setFollowedCount(int followedCount) {
		this.followedCount = followedCount;
	}


	public int getBlogCount() {
		return blogCount;
	}


	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}


	public int getAnswerCount() {
		return answerCount;
	}


	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}


	public int getQuestionCount() {
		return questionCount;
	}


	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}


	public int getPostCount() {
		return postCount;
	}


	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}


	public int getTagCount() {
		return tagCount;
	}


	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}


	public int getActivityCount() {
		return activityCount;
	}


	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}


	public int getGroupCount() {
		return groupCount;
	}


	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}


	public int getIsFollow() {
		return isFollow;
	}


	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}
	
	public DocumentEntity convertToDoc()
	{
		if(this!=null)
		{
			DocumentEntity doc=new DocumentEntity();
			doc.setFromName("");
			doc.setFromValue(0);
			if(this.getTitle()!=null)
			    doc.setContent(this.getTitle());
			else
				doc.setContent("");
			doc.setDate(this.lastLoginDate);
			doc.setId("U"+this.getId());
			doc.setRemark(this.getAvatar48()+","+this.followedCount);
			doc.setTitle(this.getNickName());
			doc.setTagString("");
			doc.setType(DocTypeEnum.User.getValue());
			return doc;
		}
		return null;
	}


	public Long getPhotoId() {
		return photoId;
	}


	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}


	public Photo getAvatar() {
		return avatar;
	}


	public void setAvatar(Photo avatar) {
		this.avatar = avatar;
	}


	public int getPhotoCount() {
		return photoCount;
	}


	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}

	
	
}
