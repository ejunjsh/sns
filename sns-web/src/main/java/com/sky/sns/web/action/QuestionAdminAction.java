package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.Question;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.IQuestionService;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.web.pojo.AjaxCode;


public class QuestionAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -1590591728655797554L;
    
	private IQuestionService questionService;
	private ITagService tagService;
	private com.sky.sns.mybatis.searchEntity.Question params;
	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}


	private List<Question> questions;
	private String tags;
	private Question question;
	
	private Map<Integer,String> statusMap;
	public Map<Integer,String> getStatusMap() {
		return StatusEnum.toMap();
	}

	
	  public String searchQuestion() throws Exception{
	    	if(params==null)
	    		params =new com.sky.sns.mybatis.searchEntity.Question();

	    	if(params.getPageIndex()>0)
	    		params.setPageIndex(params.getPageIndex()-1);
	         setQuestions(questionService.searchQuestions(params));
	         setModule("searchQuestion");
	 		 return "page";
	    } 

	  public String doQuestion() throws Exception{
		  setModule("doQuestion");
			if (isPost()) {
				if (curUser != null) {
					if (!tags.isEmpty()) {
						List<Tag> newTags = new ArrayList<Tag>();
						String[] ts = tags.split(",");
						if (question != null && question.getId() == null) {
							for (String s : ts) {
								Tag tag = tagService.getTagById(Integer.parseInt(s
										.trim()));
								if (tag != null) {
									newTags.add(tag);
								}
							}
							question.setAnswerCount(0);
							question.setPostedByUserId(curUser.getId());
							question.setPostedDate(new Date());
							question.setStatus(StatusEnum.NORMAL.getValue());
							question.setUpdatedDate(new Date());
							question.setViewCount(0);
							question.setTags(newTags);
							questionService.insertQuestion(question);
						} else {
							if (question.getId() > 0) {
								Question editQuestion = questionService
										.getQuestionById(question.getId());
								if (editQuestion != null) {
										editQuestion.setTitle(question.getTitle());
										editQuestion.setContent(question
												.getContent());
										editQuestion.setUpdatedDate(new Date());
										questionService.updateQuestionForTags(editQuestion,ts);
								}
							}
						}
					}
					jsonData.put("status", AjaxCode.successful);
					return "json";
				} else {
					return ERROR;
				}

			} else {
				if (question != null) {
					if (question.getId() != null) {
						question = questionService.getQuestionById(question.getId());
					}
				}
				return "page";
			}
	  }

	public void setQuestionService( IQuestionService questionService) {
		this.questionService = questionService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}
	public com.sky.sns.mybatis.searchEntity.Question getParams() {
		return params;
	}

	public void setParams(com.sky.sns.mybatis.searchEntity.Question params) {
		this.params = params;
	}

	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}




	
}
