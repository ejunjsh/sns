package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Album;


public interface AlbumMapper {  
	  
    void insertAlbum(Album album);  
    
    List<Album> getAlbumsByUserId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);  
    
    int countAlbumsByUserId(long id);
    
    void updateAlbum(Album album);
    
    Album getAlbumById(long id);
    
    void updateAlbumDate(long id);
}  
