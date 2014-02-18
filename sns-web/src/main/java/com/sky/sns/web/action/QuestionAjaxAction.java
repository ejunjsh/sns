package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




import java.util.GregorianCalendar;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.Answer;
import com.sky.sns.mybatis.entity.Question;
import com.sky.sns.mybatis.entity.QuestionComment;
import com.sky.sns.mybatis.iService.IAnswerService;
import com.sky.sns.mybatis.iService.IQuestionCommentService;
import com.sky.sns.mybatis.iService.IQuestionService;
import com.sky.sns.web.pojo.*;

public class QuestionAjaxAction extends AjaxAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	private long id;
	private String value;
	private IQuestionService questionService;
	private IAnswerService answerService;
	private IQuestionCommentService questionCommentService;
	private QuestionComment comment;
	private Question question;

	
	public String doQuestionLock() throws Exception {
		if (id > 0) {
			Question q = questionService.getQuestionById(id);
			if (q != null) {
				GregorianCalendar gc = new GregorianCalendar();
				Date lockDate = q.getLockDate();
				if (lockDate != null) {
					gc.setTime(q.getLockDate());
					gc.add(Calendar.HOUR, 1);
					if (gc.getTime().after(new Date())) {
						jsonData.put("status", AjaxCode.forbit);
						jsonData.put("data", null);
						return "json";
					}
				}
				q.setLockDate(new Date());
				if (q.getStatus() == StatusEnum.NORMAL.getValue())
					q.setStatus(StatusEnum.LOCKED.getValue());
				else
					q.setStatus(StatusEnum.NORMAL.getValue());
				questionService.updateQuestion(q);
				jsonData.put("status", AjaxCode.successful);
				jsonData.put("data", null);
				return "json";
			}
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}

	public String doUselessAnswer() throws Exception {
		if (id > 0) {
			Answer a = answerService.getAnswerById(id);
			if (a != null) {
                if(answerService.isUseless(a.getId(), curUser.getId()))
                {
                	jsonData.put("status", AjaxCode.forbit);
            		jsonData.put("data", null);
                }
                else
                {

                    answerService.addUseless(a, curUser.getId());
                    jsonData.put("status", AjaxCode.successful);
            		jsonData.put("data", null);
                }
				return "json";
			}
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String deleteAnswer() throws Exception {
		if (id > 0) {
			Answer a = answerService.getAnswerById(id);
			if (a != null) {
				answerService.deleteAnswer(a);
				jsonData.put("status", AjaxCode.successful);
         		jsonData.put("data", null);
				return "json";
			}
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doVoteAnswer() throws Exception {
		if (id > 0) {
			Answer a = answerService.getAnswerById(id);
			if (a != null) {
                if(answerService.isVoted(a.getId(),curUser.getId()))
                {
                	jsonData.put("status", AjaxCode.forbit);
            		jsonData.put("data", null);
                }
                else
                {
                    if(value.equalsIgnoreCase("1"))
                    {
                        answerService.addVote(a,curUser.getId(),true);
                    }
                    else
                    {
                        answerService.addVote(a,curUser.getId(),false);
                    }

                    jsonData.put("status", AjaxCode.successful);
            		jsonData.put("data", null);
                }
				return "json";
			}
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String addComment() throws Exception 
	{
		if(comment!=null)
		{
			if(comment.getCommentType()==1)
			{
			    Question q=questionService.getQuestionById(comment.getRefId());
			    if(q!=null)
			    {
			    	if(q.getStatus()==StatusEnum.LOCKED.getValue())
			    	{
			    		jsonData.put("status", AjaxCode.forbit);
			    		jsonData.put("data", null);

			    		return "json";
			    	}
			    }
			    else
			    {
			    throw new Exception();
			    }
			}
			else
			{
				Answer a=answerService.getAnswerById(comment.getRefId());
				if(a!=null)
				{
					 Question q=questionService.getQuestionById(a.getQuestionId());
					    if(q!=null)
					    {
					    	if(q.getStatus()==StatusEnum.LOCKED.getValue())
					    	{
					    		jsonData.put("status", AjaxCode.forbit);
					    		jsonData.put("data", null);

					    		return "json";
					    	}
					    }
					    else
					    {
					    throw new Exception();
					    }
				}
				else
			    {
			    throw new Exception();
			    }
			}
			comment.setPostedDate(new Date());
			comment.setPostedByUserId(curUser.getId());
		    questionCommentService.insertQuestionComment(comment); 
		    List<QuestionComment> comments=questionCommentService.getQuestionCommentByRefId(comment.getRefId(),comment.getCommentType());
		    List<QuestionCommentJsonObject> JSONData=new ArrayList<QuestionCommentJsonObject>(5);
		    for(QuestionComment comment :comments)
		    {
		    	QuestionCommentJsonObject json=new QuestionCommentJsonObject();
		    	json.setContent(StringConversion.generateRefererLinks(StringConversion.changeToHtml(comment.getContent()),null));
		        json.setCommentType(comment.getCommentType());
		        json.setId(comment.getId());
		        json.setPostedByUserId(comment.getPostedByUserId());
		        json.setPostedByUserNickName(comment.getPostedByUser().getNickName());
		        json.setPostedDate(comment.getPostedDateF());
		        json.setRefId(comment.getRefId());
		        json.setReplyId(comment.getReplyId());
		        JSONData.add(json);
		    }
		    jsonData.put("status", AjaxCode.successful);
    		jsonData.put("data", JSONData);
    		return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String deleteComment() throws Exception 
	{
		if(comment!=null)
		{
		   questionCommentService.deleteQuestionComment(comment.getId());   
		   jsonData.put("status", AjaxCode.successful);
   		    jsonData.put("data", null);
   			return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String listComments() throws Exception 
	{
		if(comment!=null)
		{
		    List<QuestionComment> comments=questionCommentService.getQuestionCommentByRefId(comment.getRefId(),comment.getCommentType());
		    List<QuestionCommentJsonObject> JSONData=new ArrayList<QuestionCommentJsonObject>(5);
		    for(QuestionComment comment :comments)
		    {
		    	QuestionCommentJsonObject json=new QuestionCommentJsonObject();
		    	json.setContent(StringConversion.generateRefererLinks(StringConversion.changeToHtml(comment.getContent()),null));
		        json.setCommentType(comment.getCommentType());
		        json.setId(comment.getId());
		        json.setPostedByUserId(comment.getPostedByUserId());
		        json.setPostedByUserNickName(comment.getPostedByUser().getNickName());
		        json.setPostedDate(comment.getPostedDateF());
		        json.setRefId(comment.getRefId());
		        json.setReplyId(comment.getReplyId());
		        JSONData.add(json);
		    }
		    jsonData.put("status", AjaxCode.successful);
    		jsonData.put("data", JSONData);
    		return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}

	public String doFollow() throws Exception 
	{
		if(question!=null)
		{
		    if(questionService.follow(question.getId(),curUser.getId()))
		    {
		    	jsonData.put("status", AjaxCode.successful);
	   		    jsonData.put("data", null);
	   			return "json";
		    }
		    else
		    {
		    	jsonData.put("status", AjaxCode.forbit);
				jsonData.put("data", null);
				return "json";
		    }
		    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doUnfollow() throws Exception 
	{
		if(question!=null)
		{

			questionService.unfollow(question.getId(),curUser.getId());
		    	jsonData.put("status", AjaxCode.successful);
	   		    jsonData.put("data", null);
	   			return "json";

		    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}
	public void setAnswerService(IAnswerService answerService) {
		this.answerService = answerService;
	}
	
	public void setQuestionCommentService(IQuestionCommentService questionCommentService) {
		this.questionCommentService = questionCommentService;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public QuestionComment getComment() {
		return comment;
	}

	public void setComment(QuestionComment comment) {
		this.comment = comment;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}




}
