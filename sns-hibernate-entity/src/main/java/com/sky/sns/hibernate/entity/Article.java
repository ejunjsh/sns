package com.sky.sns.hibernate.entity;

import com.sky.sns.hibernate.entity.Tag;
import com.sky.sns.hibernate.entity.User;

import java.util.*;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Article")
public class Article {
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "title",length=100)
    private String title;

	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "photoId")
	 private Photo cover;
	
	@Column(name = "content",length=java.lang.Integer.MAX_VALUE)
    private String content;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "postedByUserId")
    private User postedByUser;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "articleTopicId")
    private ArticleTopic articleTopic;

	@Column(name = "postedDate")
    private Date postedDate;
	 
	 @Column(name = "updatedDate")
    private Date updatedDate;
	 
	 @Column(name = "status")
    private int status;
	 
	 @Column(name = "viewCount")
    private long viewCount;
	 


	
	 @ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "Article_User_Recommend", joinColumns = { @JoinColumn(name = "articleId")},inverseJoinColumns={@JoinColumn(name="userId") })
    private List<User> recommendUsers;

	@ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "Article_Tag", joinColumns = { @JoinColumn(name = "articleId")},inverseJoinColumns={@JoinColumn(name="tagId") })
    private List<Tag> tags;
	 


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
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the postedByUser
	 */
	public User getPostedByUser() {
		return postedByUser;
	}
	/**
	 * @param postedByUser the postedByUser to set
	 */
	public void setPostedByUser(User postedByUser) {
		this.postedByUser = postedByUser;
	}
	/**
	 * @return the postedDate
	 */
	public Date getPostedDate() {
		return postedDate;
	}
	/**
	 * @param postedDate the postedDate to set
	 */
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	/**
	 * @return the viewCount
	 */
	public long getViewCount() {
		return viewCount;
	}
	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public ArticleTopic getArticleTopic() {
		return articleTopic;
	}
	public void setArticleTopic(ArticleTopic articleTopic) {
		this.articleTopic = articleTopic;
	}
	
	 
	 public List<User> getRecommendUsers() {
		return recommendUsers;
	}
	public void setRecommendUsers(List<User> recommendUsers) {
		this.recommendUsers = recommendUsers;
	}
	public Photo getCover() {
		return cover;
	}
	public void setCover(Photo cover) {
		this.cover = cover;
	}
}
