package com.sky.sns.mybatis.mapper;

import java.util.List;

import com.sky.sns.mybatis.entity.PhotoComment;


public interface PhotoCommentMapper {
	void insertPhotoComment(PhotoComment qc);
	void deletePhotoComment(long id);
	List<PhotoComment> getPhotoCommentByPhotoId(long id);
	PhotoComment getPhotoCommentById(long id);
}
