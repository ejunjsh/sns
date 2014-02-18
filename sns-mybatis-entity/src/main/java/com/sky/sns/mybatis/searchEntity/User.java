package com.sky.sns.mybatis.searchEntity;

import java.util.Date;

public class User extends com.sky.sns.mybatis.entity.User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -89058995333559905L;

	private Date regesiterDateMin;
	
	private Date regesiterDateMax;
    private Date lastLoginDateMin;
    
    private Date lastLoginDateMax;
    
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

	public Date getRegesiterDateMin() {
		return regesiterDateMin;
	}

	public void setRegesiterDateMin(Date regesiterDateMin) {
		this.regesiterDateMin = regesiterDateMin;
	}

	public Date getRegesiterDateMax() {
		return regesiterDateMax;
	}

	public void setRegesiterDateMax(Date regesiterDateMax) {
		this.regesiterDateMax = regesiterDateMax;
	}

	public Date getLastLoginDateMin() {
		return lastLoginDateMin;
	}

	public void setLastLoginDateMin(Date lastLoginDateMin) {
		this.lastLoginDateMin = lastLoginDateMin;
	}

	public Date getLastLoginDateMax() {
		return lastLoginDateMax;
	}

	public void setLastLoginDateMax(Date lastLoginDateMax) {
		this.lastLoginDateMax = lastLoginDateMax;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
