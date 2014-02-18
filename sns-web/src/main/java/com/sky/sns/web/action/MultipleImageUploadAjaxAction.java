package com.sky.sns.web.action;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IUserService;
import com.sky.sns.web.utility.ImageUtils;


@Controller
@Scope("prototype")
public class MultipleImageUploadAjaxAction extends AjaxAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	@Autowired
	private IUserService userService;

	private File file;


	public String upload() throws Exception {
		String combineId=request.getParameter("CombineID");
		if(combineId!=null&&!combineId.isEmpty())
		{
			String[] array=combineId.split("\\|");
			if(array!=null&&array.length==2)
			{
			String email=array[0].replaceAll("\"", "");
			String pwd=array[1].replaceAll("\"", "");
			User user=userService.getUserByEmail(email);
			
				if(user!=null)
				{
					if(user.getPassWord().equalsIgnoreCase(pwd))
				
				{
					if(file!=null)
					{
		               String url=ImageUtils.SaveToTemporary(file);
						
					   
						jsonData.put("error", 0);   
						jsonData.put("url", url);  
						return "imgJson";
					}
				}
				}
			}
		}

			jsonData.put("error", 1);   
			jsonData.put("message", "你没有登录!");  

		return "imgJson";
	}
    
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
