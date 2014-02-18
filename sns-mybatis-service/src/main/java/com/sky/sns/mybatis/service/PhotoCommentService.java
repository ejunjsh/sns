package com.sky.sns.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.entity.PhotoComment;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IPhotoCommentService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.PhotoCommentMapper;
import com.sky.sns.mybatis.mapper.PhotoMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

@Service
public class PhotoCommentService implements IPhotoCommentService {

	@Autowired 
	private PhotoCommentMapper photoCommentMapper;
	@Autowired 
	private PhotoMapper photoMapper;
	@Autowired 
	private ActivityMapper activityMapper;
	@Autowired 
	private NoticeMapper noticeMapper;
	@Autowired 
	private UserMapper userMapper;
	
 
	@Transactional 
	public void insertPhotoComment(PhotoComment qc) {
		
		
		Photo photo=photoMapper.getPhotoById(qc.getPhotoId());
		if(photo!=null)
		{
			photoCommentMapper.insertPhotoComment(qc);
			
			Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.CommentPhoto.getValue());
			activity.setDescription(qc.getContentNoHtml100());
			activity.setRefId(qc.getId());
			activity.setTitle(photo.getTitle());
			activity.setUserId(qc.getPostedByUserId());
			activity.setPic(photo.getThumbnail());
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(qc.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.PhotoCommentAtNotice.getValue());
				  notice.setRefId(qc.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(photo.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
		}
	}
	  

	  @Transactional   
	public void deletePhotoComment(long id) {
		  PhotoComment bc=photoCommentMapper.getPhotoCommentById(id);
		  if(bc!=null)
		  {
		  Photo photo=photoMapper.getPhotoById(bc.getPhotoId());
			if(photo!=null)
			{
				photoCommentMapper.deletePhotoComment(id);
			}
		  }
	}

	  @Transactional(readOnly=true)
	public List<PhotoComment> getPhotoCommentByPhotoId(long photoId) {

		return photoCommentMapper.getPhotoCommentByPhotoId(photoId);
	}
}
