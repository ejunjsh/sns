package com.sky.sns.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sky.sns.mybatis.entity.Message;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IMessageService;
import com.sky.sns.mybatis.iService.INoticeService;
import com.sky.sns.mybatis.iService.IUserService;
import com.sky.sns.web.pojo.AjaxCode;
import com.sky.sns.web.utility.Coder;
import com.sky.sns.web.utility.CookieSupport;
import com.sky.sns.web.utility.FreeMarkerGenerator;
import com.sky.sns.web.utility.WebContext;

public class UserAjaxAction extends AjaxAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9011797524560513291L;
    
	private IUserService userService;
	private INoticeService noticeService;
	private IMessageService messageService;
	private User user;
	private String code;
	private boolean remember;
	private InputStream inputStream;
    private String backUrl;
    private Message message;
	
	public String getLogin() throws Exception {
		jsonData.put("status", 1);
		FreeMarkerGenerator g = new FreeMarkerGenerator("WEB-INF");
		jsonData.put("data",g.parse("/ftl/ajax/login.ftl", null));
		return "json";
	}

	public String getReg() throws Exception {
		jsonData.put("status", 1);
		FreeMarkerGenerator g = new FreeMarkerGenerator("WEB-INF");
		 Enumeration<String> attrs = request.getAttributeNames();
			Map root = new HashMap();
			root.put("contextPath", request.getContextPath());
		jsonData.put("data",g.parse("/ftl/ajax/reg.ftl", root));
		return "json";
	}
	
	public String getNoticeList() throws Exception {
		jsonData.put("status", AjaxCode.successful);
		FreeMarkerGenerator g = new FreeMarkerGenerator("WEB-INF");
		

		List<Notice> notices=noticeService.getUnreadNotice(curUser.getId());
		if(notices!=null&&notices.size()>0)
		{
			Map map=new HashMap();
			map.put("notices", notices);
			map.put("contextPath", request.getContextPath());
			jsonData.put("data",g.parse("/ftl/ajax/noticeList.ftl", map));
		}
		else
		{
			jsonData.put("data","");
		}
		return "json";
	}
	
	public String getMessageList() throws Exception {
		jsonData.put("status", AjaxCode.successful);
		FreeMarkerGenerator g = new FreeMarkerGenerator("WEB-INF");
		
        
		List<Message> messages=messageService.getUnreadMessage(curUser.getId());
		if(messages!=null&&messages.size()>0)
		{
			Map map=new HashMap();
		    map.put("messages", messages);
		    map.put("contextPath", request.getContextPath());
		    jsonData.put("data",g.parse("/ftl/ajax/messageList.ftl", map));
		}
		else
		{
			jsonData.put("data","");
		}
		return "json";
	}
	
	public String checkEmail() throws Exception {
		jsonData.put("status", 1);
		User u=userService.getUserByEmail(user.getEmail());
		if(u!=null)
		{
			jsonData.put("data",1);
		}
		else
		{
			jsonData.put("data",0);
		}
		return "json";
	}
	
	public String checkNickName() throws Exception {
		jsonData.put("status", 1);
		User u=userService.getUserByNickName(user.getNickName());
		if(u!=null)
		{
			jsonData.put("data",1);
		}
		else
		{
			jsonData.put("data",0);
		}
		return "json";
	}
	public String checkCode() throws Exception {
		jsonData.put("status", 1);
		String c;
		Object o=request.getSession().getAttribute("randomImg");
		if(o!=null)
		{
	       c=o.toString();
	       if(c.equalsIgnoreCase(code))
	       {
	    	   jsonData.put("data",0);
	       }
	       else
	       {
	    	   jsonData.put("data",1);
	       }
		}
		else
		{
			jsonData.put("data",1);
		
		}
		return "json";
	}
	
	@SuppressWarnings("deprecation")
	public String login() throws Exception {
		String account=user.getEmail();
		User u;
		if(account.indexOf("@")>=0)
		{
		   u=userService.getUserByEmail(account);
		}
		else
		{
			u=userService.getUserByNickName(account);
		}
		if(u!=null)
		{
			String pwd=new BigInteger(Coder.encryptMD5(user.getPassWord().getBytes())).toString(32);
			if(pwd.equals(u.getPassWord()))
			{
				u.setLastLoginDate(new Date());
				u.setIp(WebContext.getIpAddr(request));
				userService.updateUser(u);
				CookieSupport.addCookie(response, "CombineID", u.getEmail()+"|"+u.getPassWord(), remember?3600*24*7:0);
			    inputStream=new StringBufferInputStream("<script type='text/javascript'>parent.popWin.loginCallBack(true);</script>");
			}
			else
			{
				inputStream=new StringBufferInputStream("<script type='text/javascript'>parent.popWin.loginCallBack(false);</script>");
			}
		}
		else
		{
			inputStream=new StringBufferInputStream("<script type='text/javascript'>parent.popWin.loginCallBack(false);</script>");
		}
		return "text";
	}
	
	@SuppressWarnings("deprecation")
	public String reg() throws Exception {
		if(user.getEmail()!=null&&user.getPassWord()!=null&&user.getNickName()!=null&&code!=null)
		{
			User newUser=new User();
			newUser.setEmail(user.getEmail());
			newUser.setIp(WebContext.getIpAddr(request));
			newUser.setLastLoginDate(new Date());
			newUser.setNickName(user.getNickName());
			
			String pwd=new BigInteger(Coder.encryptMD5(user.getPassWord().getBytes())).toString(32);
			newUser.setPassWord(pwd);
			newUser.setRegesiterDate(new Date());
			newUser.setStatus(1);
            userService.insertUser(newUser);
            CookieSupport.addCookie(response, "CombineID", newUser.getEmail()+"|"+newUser.getPassWord(), remember?3600*24*7:0);
			inputStream=new StringBufferInputStream("<script type='text/javascript'>parent.popWin.regCallBack(true);</script>");

		}
		else
		{
			inputStream=new StringBufferInputStream("<script type='text/javascript'>parent.popWin.regCallBack(false);</script>");
		}
		return "text";
	}
	
	public String logout() throws Exception {
		 CookieSupport.addCookie(response, "CombineID", null, 0);
		WebContext.removeCurrentUser(request);
		 if(backUrl!=null&&! backUrl.isEmpty())
		 {
			 inputStream=new ByteArrayInputStream(("退出...<script type='text/javascript'>window.location.href='"+backUrl+"'</script>").getBytes("UTF-8"));
		 }
		 else
		 {
			 inputStream=new ByteArrayInputStream("退出...<script type='text/javascript'>window.location.href='/'</script>".getBytes("UTF-8"));
		 }
		 return "text";
	}
	
	public String getTopBar() throws Exception 
	{
		if(curUser!=null)
		{
			setUser(curUser);
		}
		return "javascript";
	}
	
    
	public String doFollow() throws Exception 
	{
		if(user!=null)
		{
				if(curUser.getId()!=user.getId())
				{
				    if(userService.follow(curUser.getId(), user.getId()))
				    {
				    	jsonData.put("status", AjaxCode.successful);
			   		    jsonData.put("data", null);
			   			return "json";
				    }
				    else
				    {
				    	jsonData.put("status", AjaxCode.forbit);
						jsonData.put("data", null);
						return "json";
				    }
			    
			}
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doUnfollow() throws Exception 
	{
		if(user!=null)
		{
			if(curUser.getId()!=user.getId())
			{
			userService.cacelFollow(curUser.getId(),user.getId());
		    jsonData.put("status", AjaxCode.successful);
	   		jsonData.put("data", null);
	   		return "json";

			}
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doDeleteGroupMessage() throws Exception 
	{
		if(message!=null)
		{
			messageService.deleteMessageByGroup(message.getGroup(), curUser.getId());
		    jsonData.put("status", AjaxCode.successful);
	   		jsonData.put("data", null);
	   		return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doDeleteMessage() throws Exception 
	{
		if(message!=null)
		{
			messageService.deleteMessageById(message.getId(),curUser.getId());
		    jsonData.put("status", AjaxCode.successful);
	   		jsonData.put("data", null);
	   		return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public boolean getRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public InputStream getInputStream()
	{
		return inputStream;
	}
	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}


	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

}
