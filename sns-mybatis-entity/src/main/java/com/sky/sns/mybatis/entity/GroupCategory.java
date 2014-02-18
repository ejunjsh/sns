package com.sky.sns.mybatis.entity;
import java.util.Date;

import com.sky.sns.common.DateUtils;


public class GroupCategory  extends BaseEntity {
	
	
   /**
	 * 
	 */
   private static final long serialVersionUID = -5546019225370390447L;

   private long id;

   private Date createdDate;
   private Date updatedDate;
   private String name;
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
 * @return the createdDate
 */
public Date getCreatedDate() {
	return createdDate;
}

/**
 * @param createdDate the createdDate to set
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
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
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
}
