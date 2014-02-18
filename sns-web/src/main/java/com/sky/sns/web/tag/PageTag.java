package com.sky.sns.web.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class PageTag extends TagSupport{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int pageNo;  
	private int recordCount;  
	private int pageSize;  
	private String url;
	private String anchor;
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		int pageCount = (this.recordCount+this.pageSize-1)/this.pageSize;
		StringBuilder sb = new StringBuilder();
		
		if(this.recordCount != 0&&this.recordCount>this.pageSize){
			sb.append("<ul class='gpages'>");
			if(this.pageNo > pageCount){
				this.pageNo = pageCount;
			}
			if(this.pageNo < 1){
				this.pageNo = 1;
			}
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();

			url = request.getAttribute("originalUrl").toString();
            url+="?";
			if(request.getAttribute("queryString")!=null)
			{
	            String queryString=request.getAttribute("queryString").toString();
			    url+=queryString.replaceAll("page=[0-9]*","");
			    if(!url.endsWith("&")&&!url.endsWith("?")){
                	url+="&";
                }
			}
            
			if(this.pageNo>1){
				sb.append("<li><a href='"+this.url+"page="+(this.pageNo-1)+"'>上一页</a></li>");
			}
			int start = 1;
			if(this.pageNo > 4){
				start = this.pageNo - 1;
				sb.append("<li><a href='"+this.url+"page=1'>1</a></li>");
				sb.append("<li><a href='"+this.url+"page=2'>2</a></li>");
				sb.append("<li><span>...</span></li>");
			}
			int end = this.pageNo + 1;
			if (end > pageCount) {
				end = pageCount;
			}
			for (int i = start; i <= end; i++) {
				if (this.pageNo == i){
					sb.append("<li><span>"+i+"</span></li>");
				}else{
					sb.append("<li><a href='"+this.url+"page="+i+"'>").append(i).append("</a></li>");
				}
			}
			if (end < pageCount - 2) {
				sb.append("<li><span>...</span></li>");
			}
			if (end < pageCount - 1) {
				sb.append("<li><a href='"+this.url+"page="+(pageCount - 1)+"'>").append((pageCount - 1)+"</a></li>");
			}
			if (end < pageCount) {
				sb.append("<li><a href='"+this.url+"page="+pageCount+"'>").append(pageCount+"</a></li>");
			}
			if (this.pageNo != pageCount){
				sb.append("<li><a href='"+this.url+"page="+(this.pageNo+1)+"'>下一页 </a></li>");
			}
		}
		if(anchor!=null){
			sb.append("<script type='text/javascript'>$('.gpages a').each(function(){var href=$(this).attr('href');$(this).attr('href',href+'"+anchor+"')});</script>");
		}
		sb.append("</ul>");
		try {
			this.pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setAnchor(String anchor ){
		this.anchor="#"+anchor;
	}

}