package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.mybatis.entity.Album;
import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.IAlbumService;
import com.sky.sns.mybatis.iService.IPhotoService;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.web.pojo.*;
import com.sky.sns.web.utility.ImageUtils;


@Controller
@Scope("prototype")
public class TagAjaxAction extends AjaxAction   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;
    
	private String key;
	
	@Autowired
	private ITagService tagService;
	
	private Tag tag;
	
	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private IPhotoService photoService;
	
	private String tmpUrl;
	 
	public String getTagByKey() throws Exception{
		List<Tag> tags=tagService.getTagByName(key);
		if(tags!=null&&tags.size()>0){
		   jsonData.put("status",AjaxCode.successful);
		   List<TagComplete> tc=new ArrayList<TagComplete>();
		   for(int i=0;i<tags.size();i++){
			   TagComplete c=new TagComplete();
			   c.setId(tags.get(i).getId());
			   c.setName(tags.get(i).getName());
			   c.setFollowCount(tags.get(i).getFollowCount());
			   tc.add(c);
		   }
		   jsonData.put("data",tc);
		}
		else
		{
			jsonData.put("status",AjaxCode.recordNull);
			jsonData.put("data",null);
		}
		return "json";
	}
	
	public String doFollow() throws Exception 
	{
		if(tag!=null)
		{
				
				    if(tagService.followTag(curUser.getId(), tag.getId()))
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
	
	public String doUnfollow() throws Exception 
	{
		if(tag!=null)
		{

			tagService.unfollowTag(curUser.getId(),tag.getId());
		    jsonData.put("status", AjaxCode.successful);
	   		jsonData.put("data", null);
	   		return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doEdit() throws Exception 
	{
		if(tag!=null)
		{

			Tag editTag=tagService.getTagById(tag.getId());
			if(tag.getDescription()!=null&&!tag.getDescription().isEmpty())
			{
			editTag.setDescription(tag.getDescription());
			}
			if(tmpUrl!=null&&!tmpUrl.isEmpty())
			{
                String url=ImageUtils.SaveToAvatar(tmpUrl, "tag",curUser.getIsWaterMark()==1);
    			
                List<Album> albums=albumService.getAlbumsByUserId(curUser.getId(),0, Integer.MAX_VALUE);
				
				Album album=null;
				for(Album a : albums)
				{
					if(a.getIsFixed()==1&&a.getTitle().equalsIgnoreCase("默认相册"))
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
				photo.setDescription("标签封面");
				photo.setTitle("标签封面");
				photo.setUrl(url);
				photo.setUserId(curUser.getId());
				photoService.insertPhoto(photo);
				editTag.setPhotoId(photo.getId());
			}
			editTag.setUpdatedDate(new Date());
			tagService.updateTag(editTag);
		    jsonData.put("status", AjaxCode.successful);
	   		jsonData.put("data", null);
	   		return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getTmpUrl() {
		return tmpUrl;
	}

	public void setTmpUrl(String tmpUrl) {
		this.tmpUrl = tmpUrl;
	}
}
