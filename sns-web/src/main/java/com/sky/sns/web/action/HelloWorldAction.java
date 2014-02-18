package com.sky.sns.web.action;

import java.util.*;

import com.opensymphony.xwork2.ActionSupport;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IUserService;
import com.sky.sns.web.utility.*;


public class HelloWorldAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	//public static Log log = LogFactory.getLog(HelloWorldAction.class);
	
    private String html;
	private IUserService userService; 
	private User u;
	public String execute() throws Exception {
		User user=new User();
		user.setEmail("123333");
		user.setNickName("33333");
		user.setPassWord("111");
	    user.setRegesiterDate(new Date());
	    user.setIp("10.0.0.1");
	    user.setLastLoginDate(new Date());
	    userService.insertUser(user);
	    long id=user.getId();
	    return SUCCESS;
	}



	public IUserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public User getU() {
		return u;
	}
	/**
	 * @param u the u to set
	 */
	public void setU(User u) {
		this.u = u;
	}
	/**
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}
	/**
	 * @param html the html to set
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	

}
