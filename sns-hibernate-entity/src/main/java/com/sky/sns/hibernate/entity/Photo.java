package com.sky.sns.hibernate.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Photo")
public class Photo {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
   private long id;
	
	 @Column(name = "createdDate")
   private Date createdDate;

	@Column(name = "title")
   private String title;
	 
	 
	 @Column(name = "description",length=java.lang.Integer.MAX_VALUE)
   private String description;
   
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
    private User user;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "albumId")
    private Album album;

	 @Column(name = "url")
	   private String url;
	 
	 @Column(name = "viewCount")
	 private long viewCount;
	 
	 @Column(name = "updatedDate")
	   private Date updatedDate;
	 
	 @ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "Photo_User_Recommend", joinColumns = { @JoinColumn(name = "photoId")},inverseJoinColumns={@JoinColumn(name="userId") })
    private List<User> recommendUsers;

	public List<User> getRecommendUsers() {
		return recommendUsers;
	}

	public void setRecommendUsers(List<User> recommendUsers) {
		this.recommendUsers = recommendUsers;
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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
