package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Notice;
 


public interface NoticeMapper {  
	  
    void insertNotice(Notice notice);  
    
    void updateNoticeToRead(long id);  
    
    List<Notice> getNoticeByUserId(@Param("id")long id,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);  
    
    List<Notice> getUnreadNotice(long id);
    
    Notice getNoticeById(long id); 
    
    long countNoticeByUserId(long id);  
    
    List<Notice> getSysNotice(com.sky.sns.mybatis.searchEntity.Notice notice);  
    
    long countSysNotice(com.sky.sns.mybatis.searchEntity.Notice notice);  
}  
