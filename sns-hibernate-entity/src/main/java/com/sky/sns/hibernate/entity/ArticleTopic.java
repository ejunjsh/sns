package com.sky.sns.hibernate.entity;


import java.util.*;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ArticleTopic")
public class ArticleTopic {
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "name", unique = true,length=100)
    private String name;

	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "photoId")
	 private Photo cover;
	
	@Column(name = "description",length=java.lang.Integer.MAX_VALUE)
    private String description;
	
	
	@Column(name = "createdDate")
    private Date createdDate;
	 
	@Column(name = "updatedDate")
    private Date updatedDate;
	 
	 


	 

	
	
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
	public Photo getCover() {
		return cover;
	}
	public void setCover(Photo cover) {
		this.cover = cover;
	}



}
