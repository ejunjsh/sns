package com.sky.sns.mybatis.mapper;

import java.util.List;

import com.sky.sns.mybatis.entity.QuestionComment;


public interface QuestionCommentMapper {
	void insertQuestionComment(QuestionComment qc);
	void deleteQuestionComment(long id);
	List<QuestionComment> getQuestionCommentByRefId(long refId,int commentType);
	QuestionComment getQuestionCommentById(long id);
}
