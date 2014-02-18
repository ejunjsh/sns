package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.PhotoComment;


public interface IPhotoCommentService {
	void insertPhotoComment(PhotoComment qc);
	void deletePhotoComment(long id);
	List<PhotoComment> getPhotoCommentByPhotoId(long photoId);
}
