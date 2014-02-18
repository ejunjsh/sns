package com.sky.sns.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;



import com.opensymphony.xwork2.ActionSupport;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.web.utility.WebContext;
public class BaseAction extends ActionSupport implements ServletRequestAware,  
ServletResponseAware  {


    
	private static final long serialVersionUID = -3940472959982036826L;
	protected HttpServletRequest request;  
	protected HttpServletResponse response;
	protected User curUser;
	
	
	public User getCurUser() {
		return curUser;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		curUser=WebContext.getCurrentUser(request);
	}
	
	public boolean isPost()
	{
		return request.getMethod().equalsIgnoreCase("POST");
	}
	
	public void setPrompt(String prompt)
	{
		request.setAttribute("prompt", prompt);
	}
	
	public String getPrompt()
	{
		if(request.getAttribute("prompt")!=null)
		{
			return request.getAttribute("prompt").toString();
					
		}
		return null;
	}
	
}