package com.sky.sns.hibernate.entity;

import com.sky.sns.hibernate.entity.Tag;
import com.sky.sns.hibernate.entity.User;

import java.util.*;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Blog")
public class Blog {
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "title",length=100)
    private String title;
	
	@Column(name = "content",length=java.lang.Integer.MAX_VALUE)
    private String content;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "postedByUserId")
    private User postedByUser;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "blogCategoryId")
    private BlogCategory blogCategory;

	@Column(name = "postedDate")
    private Date postedDate;
	 
	 @Column(name = "updatedDate")
    private Date updatedDate;
	 
	 @Column(name = "status")
    private int status;
	 
	 @Column(name = "viewCount")
    private long viewCount;
	 
	
	 @ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "Blog_User_Recommend", joinColumns = { @JoinColumn(name = "blogId")},inverseJoinColumns={@JoinColumn(name="userId") })
    private List<User> recommendUsers;

	@ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "Blog_Tag", joinColumns = { @JoinColumn(name = "blogId")},inverseJoinColumns={@JoinColumn(name="tagId") })
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
	
	 public BlogCategory getBlogCategory() {
		return blogCategory;
	}
	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}
	 
	 public List<User> getRecommendUsers() {
		return recommendUsers;
	}
	public void setRecommendUsers(List<User> recommendUsers) {
		this.recommendUsers = recommendUsers;
	}
}
