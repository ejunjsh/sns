package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.Album;
import com.sky.sns.mybatis.iService.IAlbumService;
import com.sky.sns.mybatis.mapper.AlbumMapper;

@Service
public class AlbumService implements IAlbumService {

	@Autowired 
	private AlbumMapper albumMapper;
	
	@Transactional
	public void insertAlbum(Album album) {
		albumMapper.insertAlbum(album);
	}

	@Transactional(readOnly=true)
	public List<Album> getAlbumsByUserId(long id, int pageStart, int pageSize) {
		return albumMapper.getAlbumsByUserId(id, pageStart, pageSize);
	}

	@Transactional
	public void updateAlbum(Album album) {
        albumMapper.updateAlbum(album);
	}

	@Transactional(readOnly=true)
	public int countAlbumsByUserId(long id) {
		return albumMapper.countAlbumsByUserId(id);
	}

	@Transactional(readOnly=true)
	public Album getAlbumById(long id) {
		return albumMapper.getAlbumById(id);
	}

}
