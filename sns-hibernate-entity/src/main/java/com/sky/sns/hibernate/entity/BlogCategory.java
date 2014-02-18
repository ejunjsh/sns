package com.sky.sns.hibernate.entity;

import com.sky.sns.hibernate.entity.User;

import java.util.*;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BlogCategory")
public class BlogCategory {
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "name",length=100)
    private String name;
	
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "createdByUserId")
    private User createdByUser;
	
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
	public User getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
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



}
