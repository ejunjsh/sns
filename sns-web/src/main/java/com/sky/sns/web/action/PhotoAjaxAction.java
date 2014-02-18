package com.sky.sns.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.mybatis.entity.Album;
import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.entity.PhotoComment;
import com.sky.sns.mybatis.iService.IAlbumService;
import com.sky.sns.mybatis.iService.IPhotoCommentService;
import com.sky.sns.mybatis.iService.IPhotoService;
import com.sky.sns.web.pojo.AjaxCode;
import com.sky.sns.web.utility.ImageUtils;

@Controller
@Scope("prototype")
public class PhotoAjaxAction extends AjaxAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	@Autowired
	private IPhotoCommentService photoCommentService;
	@Autowired
	private IPhotoService photoService;
	
	@Autowired
	private IAlbumService albumService;
	
	private PhotoComment comment;
	
	private Photo photo;
	
	private Album album;
	
	private String tmpUrl;
	
	private String tmpUrls;
	
	public String addAlbum() throws Exception
	{
		if(album!=null&&album.getTitle()!=null&&album.getDescription()!=null)
		{
			album.setIsFixed(0);
			album.setIsPrivate(0);
			album.setUserId(curUser.getId());
			albumService.insertAlbum(album);
			jsonData.put("status", AjaxCode.successful);
   		    jsonData.put("data", null);
   			return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String addPhoto() throws Exception 
	{
		if(photo!=null&&photo.getAlbumId()>0&&photo.getDescription()!=null&&photo.getTitle()!=null)
		{
			if(tmpUrl!=null&&!tmpUrl.isEmpty())
			{
                String url=ImageUtils.SaveToRealDevice(tmpUrl, "user",curUser.getIsWaterMark()==1);
				photo.setUrl(url);
				photo.setUserId(curUser.getId());
				photoService.insertPhoto(photo);
			    jsonData.put("status", AjaxCode.successful);
		   		jsonData.put("data", null);
		   		return "json";
			}

		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String addPhotos() throws Exception 
	{
		if(photo!=null&&photo.getAlbumId()>0)
		{
			if(tmpUrls!=null&&!tmpUrls.isEmpty())
			{
				String[] ss = tmpUrls.split("\\|");
				for(String s :ss)
				{
					if(s!=null&&!s.isEmpty())
					{
						Photo p=new Photo();
		                String url=ImageUtils.SaveToRealDevice(s, "user",curUser.getIsWaterMark()==1);
						p.setAlbumId(photo.getAlbumId());
						p.setTitle("分享图片");
						p.setDescription("分享图片");
		                p.setUrl(url);
						p.setUserId(curUser.getId());
						photoService.insertPhoto(p);

					}
				}
			    jsonData.put("status", AjaxCode.successful);
		   		jsonData.put("data", null);
		   		return "json";

			}

		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}


	public String doRecommend() throws Exception 
	{
		if(photo!=null)
		{
		    if(photoService.recommendPhoto(photo.getId(),curUser.getId()))
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
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String deleteComment() throws Exception 
	{
		if(comment!=null)
		{
			photoCommentService.deletePhotoComment(comment.getId());   
		   jsonData.put("status", AjaxCode.successful);
   		    jsonData.put("data", null);
   			return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}




	public Photo getPhoto() {
		return photo;
	}




	public void setPhoto(Photo photo) {
		this.photo = photo;
	}




	public PhotoComment getComment() {
		return comment;
	}




	public void setComment(PhotoComment comment) {
		this.comment = comment;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getTmpUrl() {
		return tmpUrl;
	}

	public void setTmpUrl(String tmpUrl) {
		this.tmpUrl = tmpUrl;
	}

	public String getTmpUrls() {
		return tmpUrls;
	}

	public void setTmpUrls(String tmpUrls) {
		this.tmpUrls = tmpUrls;
	}
}
