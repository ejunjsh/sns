package com.sky.sns.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.iService.IPhotoService;

@Controller
@Scope("prototype")
public class PhotoRepositoryAction extends BaseAction {

	
	private static final long serialVersionUID = 1567909995460604325L;
	
	@Autowired
	private IPhotoService photoService;
	
	private String size;
	
	private long id;
	
	private ByteArrayInputStream inputStream;
	public ByteArrayInputStream getInputStream()
	{
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream)
	{
		this.inputStream = inputStream;
	}

    
	public String photo() throws Exception {
		
		Photo photo=photoService.getPhotoById(id);
		String realPath = ServletActionContext.getServletContext().getRealPath(photo.getPhysicalUrlBySize(size));
		FileInputStream  fis=null;
		try
		{
			fis= new FileInputStream(new File(realPath));
		}
		catch(Exception e)
		{
			realPath= ServletActionContext.getServletContext().getRealPath("/staticFile/img/defaultAvatar.gif");
			fis = new FileInputStream(new File(realPath));
		}
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[128];
        int iLength = 0;
        while((iLength = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, iLength);
        }
        fis.close();
        baos.close();
		ByteArrayInputStream input = new ByteArrayInputStream(baos.toByteArray());
		this.setInputStream(input);
	    return SUCCESS;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
}
