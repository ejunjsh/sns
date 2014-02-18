package com.sky.sns.web.action;

public class BasePageAction extends BaseAction {


	private static final long serialVersionUID = 1L;
    
	protected long recordCount;
	protected int pageSize=20;
	protected int pageNo=1;
	
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	//for url query
	public void setPage(int page)
	{
		if(page<=0)
		{
			page=1;
		}
		this.pageNo = page;
	}
	
	 protected long getPageStart()
	    {
	    	return pageSize*(pageNo-1);
	    }
}
