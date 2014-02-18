package com.sky.sns.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.QuestionComment;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IQuestionCommentService;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.QuestionCommentMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

public class QuestionCommentService implements IQuestionCommentService {

	private QuestionCommentMapper questionCommentMapper;
	private UserMapper userMapper;
	private NoticeMapper noticeMapper;

	public void setQuestionCommentMapper(
			QuestionCommentMapper questionCommentMapper) {
		this.questionCommentMapper = questionCommentMapper;
	}

	@Transactional
	public void insertQuestionComment(QuestionComment qc) {
		questionCommentMapper.insertQuestionComment(qc);
		
		List<String> referers=new ArrayList<String>();
		StringConversion.generateRefererLinks(qc.getContent(), referers);
		for(String s :referers)
		{
		  User user=userMapper.getUserByNickName(s);
		  if(user!=null)
		  {
			  Notice notice=new Notice();
			  if(qc.getCommentType()==1)
			      notice.setNoticeType(NoticeTypeEnum.QuestionCommentAtNotice.getValue());
			  else
				  notice.setNoticeType(NoticeTypeEnum.AnswerCommentAtNotice.getValue()); 
			  notice.setRefId(qc.getRefId());
			  notice.setUserId(user.getId());
			  notice.setTitle("");
			  noticeMapper.insertNotice(notice);
		  }
		}
	}

	@Transactional
	public void deleteQuestionComment(long id) {
		QuestionComment qc = questionCommentMapper.getQuestionCommentById(id);
		if (qc != null) {
			questionCommentMapper.deleteQuestionComment(id);
		}

	}

	@Transactional(readOnly = true)
	public List<QuestionComment> getQuestionCommentByRefId(long refId,
			int commentType) {
		return questionCommentMapper.getQuestionCommentByRefId(refId, commentType);
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

}
