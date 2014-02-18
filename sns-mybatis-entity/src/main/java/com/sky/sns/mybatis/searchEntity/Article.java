package com.sky.sns.mybatis.searchEntity;

import java.util.Date;

public class Article extends com.sky.sns.mybatis.entity.Article{
/**
	 * 
	 */
	private static final long serialVersionUID = 1609571180959361386L;
   
	private String tagName;
	
	private String topic;
	
   private Date postedDateMin;
	
	private Date postedDateMax;
	
    private Date createdDateMin;
	
	private Date createdDateMax;
	
	private String name;
	
	private String description;
	
    private Date updatedDateMin;
    
    private Date updatedDateMax;
    
    private String sort="id";
    
    private String order="desc";
    
    private int pageSize=20;
    
    private int pageIndex=0;
    
    private long total=0;
    
    public long getPageStart()
    {
    	return pageSize*pageIndex;
    }



	public String getSort() {
		return sort;
	}



	public void setSort(String sort) {
		this.sort = sort;
	}



	public String getOrder() {
		return order;
	}



	public void setOrder(String order) {
		this.order = order;
	}



	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}



	public Date getPostedDateMin() {
		return postedDateMin;
	}



	public void setPostedDateMin(Date postedDateMin) {
		this.postedDateMin = postedDateMin;
	}



	public Date getPostedDateMax() {
		return postedDateMax;
	}



	public void setPostedDateMax(Date postedDateMax) {
		this.postedDateMax = postedDateMax;
	}



	public Date getUpdatedDateMin() {
		return updatedDateMin;
	}



	public void setUpdatedDateMin(Date updatedDateMin) {
		this.updatedDateMin = updatedDateMin;
	}



	public Date getUpdatedDateMax() {
		return updatedDateMax;
	}



	public void setUpdatedDateMax(Date updatedDateMax) {
		this.updatedDateMax = updatedDateMax;
	}



	public String getTagName() {
		return tagName;
	}



	public void setTagName(String tagName) {
		this.tagName = tagName;
	}



	public String getTopic() {
		return topic;
	}



	public void setTopic(String topic) {
		this.topic = topic;
	}



	public Date getCreatedDateMin() {
		return createdDateMin;
	}



	public void setCreatedDateMin(Date createdDateMin) {
		this.createdDateMin = createdDateMin;
	}



	public Date getCreatedDateMax() {
		return createdDateMax;
	}



	public void setCreatedDateMax(Date createdDateMax) {
		this.createdDateMax = createdDateMax;
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
}
