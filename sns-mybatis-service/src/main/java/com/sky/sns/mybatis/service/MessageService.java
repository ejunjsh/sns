package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.Message;
import com.sky.sns.mybatis.iService.IMessageService;
import com.sky.sns.mybatis.mapper.MessageMapper;


public class MessageService implements IMessageService {
	
	 private MessageMapper messageMapper;





	public void setMessageMapper(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}



	 @Transactional
	public void insertMessage(Message message) {
		messageMapper.insertMessage(message);
	}



	 @Transactional
	public void updateMessageToRead(long fromUserId, long toUserId) {
         messageMapper.updateMessageToRead(fromUserId, toUserId);
	}



	 @Transactional
	public void deleteMessageById(long id,long uid) {
		messageMapper.deleteMessageById(id,uid);
	}



	 @Transactional
	public void deleteMessageByGroup(String group,long uid) {
		messageMapper.deleteMessageByGroup(group,uid);
	}



	 @Transactional(readOnly=true)
	public List<Message> getMessageByUserId(long fromUserId, long toUserId,
			int pageStart, int pageSize) {
		return messageMapper.getMessageByUserId(fromUserId, toUserId, pageStart, pageSize);
	}



	 @Transactional(readOnly=true)
	public long countMessageByUserId(long fromUserId, long toUserId) {
		return messageMapper.countMessageByUserId(fromUserId, toUserId);
	}



	 @Transactional(readOnly=true)
	public List<Message> getMessageByGroup(long fromUserId, int pageStart,
			int pageSize) {
		return messageMapper.getMessageByGroup(fromUserId, pageStart, pageSize);
	}



	 @Transactional(readOnly=true)
	public long countMessageByGroup(long fromUserId) {
		return messageMapper.countMessageByGroup(fromUserId);
	}



	 @Transactional(readOnly=true)
	public String getGroupByUserId(long fromUserId, long toUserId) {
		return messageMapper.getGroupByUserId(fromUserId, toUserId);
	}



	 @Transactional(readOnly=true)
	public List<Message> getUnreadMessage(long uid) {
		return messageMapper.getUnreadMessage(uid);
	}

}
