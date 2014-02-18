package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.Message;

public interface IMessageService {  
	  
public void insertMessage(Message message);
	
	public void updateMessageToRead(long fromUserId,long toUserId );
	
	public String getGroupByUserId(long fromUserId,long toUserId );
	
	public void deleteMessageById(long id,long uid);
	
	public void deleteMessageByGroup(String group,long uid);
	
	public List<Message> getMessageByUserId(long fromUserId,long toUserId,int pageStart,int pageSize);

	public long countMessageByUserId(long fromUserId,long toUserId);
	
	public List<Message> getMessageByGroup(long fromUserId,int pageStart,int pageSize);

	public long countMessageByGroup(long fromUserId);
	
	public List<Message> getUnreadMessage(long uid);
    
}  

