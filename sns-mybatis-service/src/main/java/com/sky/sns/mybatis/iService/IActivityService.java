package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Activity;


public interface IActivityService {  
	  
    void insertActivity(Activity activity);  
    
    List<Activity> getActivityByUserId(long id,int pageStart,int pageSize);  
    
}  

