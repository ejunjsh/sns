package com.sky.sns.mybatis.searchEntity;

import java.util.Date;

public class Tag extends com.sky.sns.mybatis.entity.Tag {
	/**
	 * 
	 */
	private static final long serialVersionUID = -89058995333559905L;

    private Date createdDateMin;
    
    private Date createdDateMax;
    
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

}
