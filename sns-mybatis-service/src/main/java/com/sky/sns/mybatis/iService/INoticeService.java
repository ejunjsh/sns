package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Notice;

public interface INoticeService {  
	  
void insertNotice(Notice notice);  
    
    void updateNoticeToRead(long id);  
    
    List<Notice> getNoticeByUserId(long id,int pageStart,int pageSize);  
    
    List<Notice> getUnreadNotice(long id);
    
    long countNoticeByUserId(long id);  
    
    List<Notice> getSysNotice(com.sky.sns.mybatis.searchEntity.Notice notice);  
    
    Notice getNoticeById(long id); 
    
}  

