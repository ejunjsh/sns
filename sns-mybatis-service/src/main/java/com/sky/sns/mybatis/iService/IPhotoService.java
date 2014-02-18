package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Photo;

public interface IPhotoService {
	  void insertPhoto(Photo photo);  
	  
	  void updatePhoto(Photo photo);  
	    
	    List<Photo> getPhotosByAlbumId(long id,int pageStart,int pageSize);  
	    
	    Photo getPhotoById(long id);
	    
	    int countPhotosByAlbumId(long id);
	    
	    boolean recommendPhoto(long pid,long uid);
	   
}
