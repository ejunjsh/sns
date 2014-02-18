package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Activity;


public interface ActivityMapper {  
	  
    void insertActivity(Activity activity);  
    
    List<Activity> getActivityByUserId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);  
    
}  
