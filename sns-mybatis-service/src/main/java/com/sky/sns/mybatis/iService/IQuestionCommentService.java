package com.sky.sns.mybatis.iService;

import java.util.List;

import com.sky.sns.mybatis.entity.QuestionComment;

public interface IQuestionCommentService {
	void insertQuestionComment(QuestionComment qc);
	void deleteQuestionComment(long id);
	List<QuestionComment> getQuestionCommentByRefId(long refId,int commentType);
}
