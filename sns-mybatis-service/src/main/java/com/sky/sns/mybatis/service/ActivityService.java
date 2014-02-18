package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.iService.IActivityService;
import com.sky.sns.mybatis.mapper.ActivityMapper;


public class ActivityService implements IActivityService {
	
	 private ActivityMapper activityMapper;

	    @Transactional
	public void insertActivity(Activity activity) {
		activityMapper.insertActivity(activity);
		
	}

	   


	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}

	 @Transactional(readOnly=true)
		public List<Activity> getActivityByUserId(long id, int pageStart,
				int pageSize) {
		return activityMapper.getActivityByUserId(id, pageStart, pageSize);
		}
	
	
}
