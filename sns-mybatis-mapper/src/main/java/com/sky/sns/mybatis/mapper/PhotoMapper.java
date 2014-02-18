package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Photo;


public interface PhotoMapper {  
	  
    void insertPhoto(Photo photo);  
    
    void updatePhoto(Photo photo);  
    
    List<Photo> getPhotosByAlbumId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);  
    
    Photo getPhotoById(long id);
    
    int countPhotosByAlbumId(long id);
    
    void recommendPhoto(long pid,long uid);
    
    int isRecommend(long pid,long uid);
}  
