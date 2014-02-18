package com.sky.sns.mybatis.searchEntity;

import java.util.Date;

public class Group extends com.sky.sns.mybatis.entity.Group{
/**
	 * 
	 */
	private static final long serialVersionUID = 1009571180959361386L;
   
	private Group group;
	
	private String title;
	
	private String content;
	
private Date postedDateMin;
	
	private Date postedDateMax;
	
	private User postedByUser;
	
	private String tagName;
	
  

private Date createdDateMin;
	
	private Date createdDateMax;
    private Date updatedDateMin;
    
    private Date updatedDateMax;
    
    private String sort="id";
    
    private String order="desc";
    
    private int pageSize=20;
    
    private int pageIndex=0;
    
    private long total=0;
    
    private String categoryName;
    
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
	
	 public Group getGroup() {
			return group;
		}



		public void setGroup(Group group) {
			this.group = group;
		}



		public String getTitle() {
			return title;
		}



		public void setTitle(String title) {
			this.title = title;
		}



		public String getContent() {
			return content;
		}



		public void setContent(String content) {
			this.content = content;
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



		public User getPostedByUser() {
			return postedByUser;
		}



		public void setPostedByUser(User postedByUser) {
			this.postedByUser = postedByUser;
		}



		public String getTagName() {
			return tagName;
		}



		public void setTagName(String tagName) {
			this.tagName = tagName;
		}



		public String getCategoryName() {
			return categoryName;
		}



		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}



}
