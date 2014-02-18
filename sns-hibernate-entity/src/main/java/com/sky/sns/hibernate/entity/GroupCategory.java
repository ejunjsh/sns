package com.sky.sns.hibernate.entity;


import java.util.*;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="GroupCategory")
public class GroupCategory {
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "name",length=100)
    private String name;
	

	
	 @Column(name = "createdDate")
    private Date createdDate;
	 
	 @Column(name = "updatedDate")
    private Date updatedDate;
	 
	@ManyToMany(cascade = CascadeType.ALL)  
	@JoinTable(name = "Group_Category", joinColumns = { @JoinColumn(name = "categoryId")},inverseJoinColumns={@JoinColumn(name="groupId") })
	private List<Group> groups;
	 


	 

	 @Column(name = "description",length=java.lang.Integer.MAX_VALUE)
	   private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}



}
