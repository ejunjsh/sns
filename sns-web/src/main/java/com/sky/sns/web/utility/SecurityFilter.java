package com.sky.sns.web.utility;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IUserService;


public class SecurityFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		HttpServletResponse httpResponse = ((HttpServletResponse) response);
		
		if(ignoreFile(httpRequest))
		{
			chain.doFilter(request, response);
			return;
		}
		
		boolean isLogon = true;
		//for use later
		httpRequest.setAttribute("originalUrl",httpRequest.getRequestURI());
		httpRequest.setAttribute("queryString",httpRequest.getQueryString());
		if (WebContext.getCurrentUser(httpRequest) == null) {
			Cookie cookie = CookieSupport.getCookieByName(httpRequest,
					"CombineID");
			if (cookie != null) {
				String s = cookie.getValue();
				String[] ss = s.split("\\|");
				if (ss.length == 2) {
					String email = ss[0];
					String pwd = ss[1];
					User user = userService.getUserByEmail(email);
					if (user != null) {
						if (user.getPassWord().equals(pwd)) {
							WebContext.setCurrentUser(httpRequest, user);
						}
						else
						{
							isLogon = false;
						}
					}
					else
					{
						isLogon = false;
					}
				}
				else
				{
					isLogon = false;
				}
			} else {
				isLogon = false;
			}
		}
		// pass the request along the filter chain
		if (isLogon) {
			chain.doFilter(request, response);
		} else {
			String url = httpRequest.getRequestURI().replace(httpRequest.getContextPath(),"");
			
			if(loginUrls!=null&&loginUrls.size()>0)
			{
			for (String u : loginUrls) {
				if(u!=null)
				{
				Pattern pattern = Pattern.compile(u);
				Matcher matcher = pattern.matcher(url);
				boolean b= matcher.matches();
				if(b)
				{
					isLogon = true;
					break;
				}
				}
			}
			}

			if (isLogon) {
				
				if (WebContext.isAjaxRequest(httpRequest)) {
					httpRequest.getRequestDispatcher(
							"/WEB-INF/jsp/common/needLoginJson.jsp").forward(
							httpRequest, httpResponse);
				} else {
                    httpRequest.setAttribute("needLogin", 1);
                    if(!httpRequest.getMethod().equalsIgnoreCase("POST"))
                    {
                        chain.doFilter(request, response);
                    }
                    else
                    {
                    	httpResponse.sendRedirect(url);
                    }
				}
			}
			else
			{
				chain.doFilter(request, response);
			}
		}
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private IUserService userService;
	
    private List<String> loginUrls;
    
    public void setLoginUrls(List<String> loginUrls) {
		this.loginUrls = loginUrls;
	}
    
    private boolean ignoreFile(HttpServletRequest request){
    	String url=request.getRequestURI();
        if (url.equalsIgnoreCase(request.getContextPath())||url.indexOf(".gif")>-1 || url.indexOf(".jpg")>-1 || url.indexOf(".png")>-1
            || url.indexOf(".bmp")>-1 || url.indexOf(".css")>-1 || url.indexOf(".js")>-1
                || url.indexOf(".jsp")>-1||url.indexOf("getTopBar")>-1){
            return true;
        } else {
            return false;
        }
    }

}
