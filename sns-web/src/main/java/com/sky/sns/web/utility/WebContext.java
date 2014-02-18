package com.sky.sns.web.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sky.sns.mybatis.entity.User;


public class WebContext {
    public static User getCurrentUser(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("CurrentUser")!=null)
        {
        	User user=(User)session.getAttribute("CurrentUser");
        	return user;
        }
        return null;
    }
    
    public static void setCurrentUser(HttpServletRequest request,User user)
    {
    	if(user!=null)
    	{
        HttpSession session=request.getSession();
       session.setAttribute("CurrentUser",user);
    	}
    }
    
    public static void removeCurrentUser(HttpServletRequest request)
    {

        HttpSession session=request.getSession();
       session.removeAttribute("CurrentUser");
     }
    
    public static boolean isAjaxRequest(HttpServletRequest request) {  
        String header = request.getHeader("X-Requested-With");  
        if (header != null && "XMLHttpRequest".equals(header))  
            return true;  
        else  
            return false;  
    }
    
	   public static String getIpAddr(HttpServletRequest request) {      
		      String ip = request.getHeader("x-forwarded-for");      
		      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
		         ip = request.getHeader("Proxy-Client-IP");      
		     }      
		      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
		         ip = request.getHeader("WL-Proxy-Client-IP");      
		      }      
		     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
		          ip = request.getRemoteAddr();      
		     }      
		     return ip;      
		}
    
}
