package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Album;

public interface IAlbumService {
    void insertAlbum(Album album);  
    
    List<Album> getAlbumsByUserId(long id,int pageStart,int pageSize);  
    
    int countAlbumsByUserId(long id);
    
    void updateAlbum(Album album);
    
    Album getAlbumById(long id);
}
