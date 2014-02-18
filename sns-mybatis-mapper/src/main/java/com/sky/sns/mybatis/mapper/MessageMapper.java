package com.sky.sns.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.sns.mybatis.entity.Message;
 

public interface MessageMapper {  
	  
	public void insertMessage(Message message);
	
	public void updateMessageToRead(long fromUserId,long toUserId );
	
	public String getGroupByUserId(long fromUserId,long toUserId );
	
	public void deleteMessageById(long id,long uid);
	
	public void deleteMessageByGroup(String group,long uid);
	
	public List<Message> getMessageByUserId(@Param("fromUserId")long fromUserId,@Param("toUserId")long toUserId,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);

	public long countMessageByUserId(@Param("fromUserId")long fromUserId,@Param("toUserId")long toUserId);
	
	public List<Message> getMessageByGroup(@Param("fromUserId")long fromUserId,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);

	public long countMessageByGroup(@Param("fromUserId")long fromUserId);
	
	public List<Message> getUnreadMessage(long uid);
}  
