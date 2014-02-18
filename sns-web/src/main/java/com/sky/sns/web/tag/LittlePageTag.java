package com.sky.sns.web.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class LittlePageTag extends TagSupport {

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
			sb.append("<span>").append(this.pageNo+"/"+pageCount).append("</span>&nbsp;&nbsp;");
			sb.append("<span class='cmts-title-page'>");
			if(this.pageNo==1){
				sb.append("<span class='gicon-prev' title='上一页'></span>");
			}
			else{
				if(anchor!=null){
					sb.append("<a href='"+this.url+"page="+(this.pageNo-1)+this.anchor+"' class='gicon-prev' title='上一页'></a>");
				}
				else{
					sb.append("<a href='"+this.url+"page="+(this.pageNo-1)+"' class='gicon-prev' title='上一页'></a>");
				}
			}

			if(this.pageNo==pageCount){
				sb.append("<span class='gicon-next' title='下一页'></span>");
			}
			else{
				if(anchor!=null){
					sb.append("<a href='"+this.url+"page="+(this.pageNo+1)+this.anchor+"' class='gicon-next' title='下一页'></a>");
				}
				else{
					sb.append("<a href='"+this.url+"page="+(this.pageNo+1)+"' class='gicon-next' title='下一页'></a>");
				}
			}
			sb.append("</span>");
			try {
				this.pageContext.getOut().println(sb.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

		public void setAnchor(String anchor) {
			this.anchor = "#" + anchor;
		}

	}