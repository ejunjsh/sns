package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.entity.PhotoComment;
import com.sky.sns.mybatis.iService.IAlbumService;
import com.sky.sns.mybatis.iService.IPhotoCommentService;
import com.sky.sns.mybatis.iService.IPhotoService;


@Controller
@Scope("prototype")
public class PhotoAction extends BasePageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private IPhotoService photoService;
	
	@Autowired
	private IPhotoCommentService photoCommentService;
	
	 private Photo photo;
	    
	    private Photo previousPhoto;
	    
	    private Photo nextPhoto;
	    
	    private List<Photo> albumPhotos;
	    
	    private int curPhotoIndex;
	    
	    private int albumSize;
	    
	    private PhotoComment photoComment;
	    
	    private List<PhotoComment> comments;


		public String detail() throws Exception
		{
				   if(photo!=null&&photo.getId()>0)
					{
						photo=photoService.getPhotoById(photo.getId());
						photo.setViewCount(photo.getViewCount() + 1);
						photoService.updatePhoto(photo);
						setComments(photoCommentService.getPhotoCommentByPhotoId(photo.getId()));
					   List<Photo>	allPhotos=photoService.getPhotosByAlbumId( photo.getAlbumId(), 0,100000);
					   albumPhotos=new ArrayList<Photo>();
						if(allPhotos!=null&&allPhotos.size()>0)
						{
							albumSize=allPhotos.size();
	                        if(allPhotos.size()>5)
	                        {
	                        	for(int i=0;i<allPhotos.size();i++)
	                        	{
	                        		if(allPhotos.get(i).getId()==photo.getId())
	                        		{
	                        			setCurPhotoIndex(i+1);
	                        			int count=2;
	                        			int k=i-1;
	                        			while(count>0)
	                        			{
	                        				
	                        				if(k>=0)
	                        				{
	                        					albumPhotos.add(allPhotos.get(k));
	                        				}
	                        				else
	                        				{
	                        					albumPhotos.add(allPhotos.get(allPhotos.size()-count));
	                        				}
	                        				count--;
	                        				k--;
	                        			}
	                        			Photo tmp=albumPhotos.get(0);
	                        			albumPhotos.set(0, albumPhotos.get(1));
	                        			albumPhotos.set(1, tmp);
	                        			albumPhotos.add(allPhotos.get(i));
	                        			count=2;
	                        		    k=i+1;
	                        			while(count>0)
	                        			{
	                        				
	                        				if(k>=allPhotos.size())
	                        				{
	                        					albumPhotos.add(allPhotos.get(2-count));
	                        				}
	                        				else
	                        				{
	                        					albumPhotos.add(allPhotos.get(k));
	                        				}
	                        				count--;
	                        				k++;
	                        			}
	                        			break;
	                        		}
	                        	}
	                        }
	                        else
	                        {
	                        	for(int i=0;i<allPhotos.size();i++)
	                        	{
	                        		if(allPhotos.get(i).getId()==photo.getId())
	                        		{
	                        			setCurPhotoIndex(i+1);
	                        		}
	                        	}
	                        	albumPhotos=allPhotos;
	                        }
	                        
	                        if(albumPhotos!=null)
	                        {
	                        	for(int i=0;i<albumPhotos.size();i++)
	                        	{
	                        		if(albumPhotos.get(i).getId()==photo.getId())
	                        		{
	                        			if(i+1>albumPhotos.size()-1)
	                        			{
	                        				nextPhoto=albumPhotos.get(0);
	                        			}
	                        			else
	                        			{
	                        				nextPhoto=albumPhotos.get(i+1);
	                        			}
	                        			
	                        			if(i-1<0)
	                        			{
	                        				setPreviousPhoto(albumPhotos.get(albumPhotos.size()-1));
	                        			}
	                        			else
	                        			{
	                        				setPreviousPhoto(albumPhotos.get(i-1));
	                        			}
	                        		}
	                        	}
	                        }
						}
					}
				   return "detail";
		}
	
		public String newComment() throws Exception {
			if (photo != null) {
				if (photo.getId()>0){
					if (photoComment != null) {
						photoComment.setPhotoId(photo.getId());
						photoComment.setPostedByUserId(curUser.getId());
						photoComment.setPostedDate(new Date());
						photoCommentService.insertPhotoComment(photoComment);
						return "goToComment";
					}
					else
					{
						return "goToDetail";
					}
				}
		}
		return ERROR;
	}

		
	public String index() throws Exception
	{
		return null;
	}
	


	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Photo getPreviousPhoto() {
		return previousPhoto;
	}

	public void setPreviousPhoto(Photo previousPhoto) {
		this.previousPhoto = previousPhoto;
	}

	public Photo getNextPhoto() {
		return nextPhoto;
	}

	public void setNextPhoto(Photo nextPhoto) {
		this.nextPhoto = nextPhoto;
	}

	public List<Photo> getAlbumPhotos() {
		return albumPhotos;
	}

	public void setAlbumPhotos(List<Photo> albumPhotos) {
		this.albumPhotos = albumPhotos;
	}

	public int getAlbumSize() {
		return albumSize;
	}

	public void setAlbumSize(int albumSize) {
		this.albumSize = albumSize;
	}

	public int getCurPhotoIndex() {
		return curPhotoIndex;
	}

	public void setCurPhotoIndex(int curPhotoIndex) {
		this.curPhotoIndex = curPhotoIndex;
	}

	public PhotoComment getPhotoComment() {
		return photoComment;
	}

	public void setPhotoComment(PhotoComment photoComment) {
		this.photoComment = photoComment;
	}

	public List<PhotoComment> getComments() {
		return comments;
	}

	public void setComments(List<PhotoComment> comments) {
		this.comments = comments;
	}
}
