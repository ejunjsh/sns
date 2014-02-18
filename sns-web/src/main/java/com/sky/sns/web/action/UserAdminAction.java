package com.sky.sns.web.action;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IUserService;
import com.sky.sns.web.pojo.AjaxCode;
import com.sky.sns.web.utility.Coder;
import com.sky.sns.web.utility.CookieSupport;
import com.sky.sns.web.utility.WebContext;
import com.sky.sns.enumeration.*;

public class UserAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -1590591728655797554L;
    
	private IUserService userService;
	private com.sky.sns.mybatis.searchEntity.User params;
	private List<User> users;
	private User loginUser;
	
	private Map<Integer,String> statusMap;
	public Map<Integer,String> getStatusMap() {
		return StatusEnum.toMap();
	}

	public String myIndex() throws Exception{
		module="myIndex";
		return "page";
	}
	
    public String searchUser() throws Exception{
    	if(params==null)
    		params =new com.sky.sns.mybatis.searchEntity.User();
    	
    	if(params.getPageIndex()>0)
    		params.setPageIndex(params.getPageIndex()-1);
         users=userService.searchUser(params);
         module="searchUser";
 		 return "page";
    } 
    
    public String login() throws Exception{
         module="login";
         if(isPost())
         {
     		String account=loginUser.getEmail();
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
    			String pwd=new BigInteger(Coder.encryptMD5(loginUser.getPassWord().getBytes())).toString(32);
    			if(pwd.equals(u.getPassWord()))
    			{
    				u.setLastLoginDate(new Date());
    				u.setIp(WebContext.getIpAddr(request));
    				userService.updateUser(u);
    				CookieSupport.addCookie(response, "CombineID", u.getEmail()+"|"+u.getPassWord(),false?3600*24*7:0);
    			    jsonData.put("status", AjaxCode.successful);
    			    jsonData.put("message","登陆成功");
    			}
    			else
    			{
    				  jsonData.put("status", AjaxCode.getFail);
      			    jsonData.put("message","用户名或密码有错");
    			}
    		}
    		else
    		{
    			jsonData.put("status", AjaxCode.getFail);
  			    jsonData.put("message","用户名或密码有错");
    		}
    		return "json";
         }
 		 return "page";
    } 



	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	public com.sky.sns.mybatis.searchEntity.User getParams() {
		return params;
	}

	public void setParams(com.sky.sns.mybatis.searchEntity.User params) {
		this.params = params;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
