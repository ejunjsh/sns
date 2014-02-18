package com.sky.sns.web.action;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.mybatis.entity.Album;
import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.iService.IAlbumService;
import com.sky.sns.mybatis.iService.IPhotoService;
import com.sky.sns.mybatis.iService.IUserService;
import com.sky.sns.web.utility.ImageUtils;


@Controller("keImageUploadAjaxAction")
@Scope("prototype")
public class KEImageUploadAjaxAction extends AjaxAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	@Autowired
	private IUserService userService;
    
	@Autowired
	private IPhotoService photoService;
	
	@Autowired
	private IAlbumService albumService;
	
	private File imgFile;

	public String upload() throws Exception {
		if(curUser!=null)
		{
			if(imgFile!=null)
			{
               String url=ImageUtils.SaveToRealDevice(imgFile, "ke", curUser.getIsWaterMark()==1);
				
				List<Album> albums=albumService.getAlbumsByUserId(curUser.getId(),0, Integer.MAX_VALUE);
				
				Album album=null;
				for(Album a : albums)
				{
					if(a.getIsFixed()==1&&a.getTitle().equalsIgnoreCase("默认相册") )
					{
						album=a;
						break;
					}
				}
				if(album==null)
				{
					album=new Album();
					album.setDescription("默认相册");
					album.setIsFixed(1);
					album.setTitle("默认相册");
					album.setUserId(curUser.getId());
					albumService.insertAlbum(album);
				}
				
				Photo photo=new Photo();
				photo.setAlbumId(album.getId());
				photo.setDescription("默认");
				photo.setTitle("默认");
				photo.setUrl(url);
				photo.setUserId(curUser.getId());
				photoService.insertPhoto(photo);
				
				jsonData.put("error", 0);   
				jsonData.put("url", "/photo/normal/"+photo.getId()+".jpg");  
			}
		}
		else
		{
			jsonData.put("error", 1);   
			jsonData.put("message", "你没有登录!");  
		}
		return "imgJson";
	}
    
	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

}
