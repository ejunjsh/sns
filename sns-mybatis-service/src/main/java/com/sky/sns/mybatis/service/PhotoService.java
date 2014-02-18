package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.iService.IPhotoService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.AlbumMapper;
import com.sky.sns.mybatis.mapper.PhotoMapper;

@Service
public class PhotoService implements IPhotoService {
    
	@Autowired 
	private PhotoMapper photoMapper;
	
	@Autowired 
    private ActivityMapper activityMapper;
	
	@Autowired
	private AlbumMapper albumMapper;
	 @Transactional
	public void insertPhoto(Photo photo) {
		photoMapper.insertPhoto(photo);
		albumMapper.updateAlbumDate(photo.getAlbumId());
	}

	 @Transactional(readOnly=true)
	public List<Photo> getPhotosByAlbumId(long id, int pageStart, int pageSize) {
		return photoMapper.getPhotosByAlbumId(id, pageStart, pageSize);
	}

	@Transactional(readOnly=true)
	public Photo getPhotoById(long id) {
		return photoMapper.getPhotoById(id);
	}

	@Transactional(readOnly=true)
	public int countPhotosByAlbumId(long id) {
		return photoMapper.countPhotosByAlbumId(id);
	}

	@Transactional
	public void updatePhoto(Photo photo) {
		 photoMapper.updatePhoto(photo);
	}

	@Transactional
	public boolean recommendPhoto(long pid, long uid) {
		Photo photo=photoMapper.getPhotoById(pid);
	       if(photo!=null)
	       {
	    	   if(photoMapper.isRecommend(pid, uid)>0)
	    	   {
	    		   return false;
	    	   }
	    	   else
	    	   {
	    		   photoMapper.recommendPhoto(pid, uid);

	   				Activity activity =new Activity();
	   				activity.setActivityType(ActivityTypeEnum.RecommendPhoto.getValue());
	   				activity.setDescription(photo.getContentNoHtml100());
	   				activity.setRefId(pid);
	   				activity.setTitle(photo.getTitle());
	   				activity.setUserId(uid);
	   				activity.setPic(photo.getThumbnail());
	   				activityMapper.insertActivity(activity);
	   			
	    		   
	    	       return true;
	    	   }
	       }
	       return false;
	}

	
}
