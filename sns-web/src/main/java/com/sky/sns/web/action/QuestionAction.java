package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.Answer;
import com.sky.sns.mybatis.entity.Question;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.IAnswerService;
import com.sky.sns.mybatis.iService.IQuestionService;
import com.sky.sns.mybatis.iService.ITagService;

public class QuestionAction extends BasePageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	private IQuestionService questionService;
	private ITagService tagService;
	private IAnswerService answerService;
	private Question question;
	private Answer answer;
	private List<Answer> answers;
	private String tags;
	private List<Question> pendingQuestion;
	private List<Question> newestQuestion;
    private List<Question> hottestQuestion;




	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}
	
	public String pending() throws Exception{
		pendingQuestion=questionService.getPendingQuestion((int)getPageStart(), pageSize);
		recordCount=questionService.countPendingQuestion();
		return "pending";
	}
	
	public String newest() throws Exception{
		setNewestQuestion(questionService.getNewestQuestion((int)getPageStart(), pageSize));
		recordCount=questionService.countAllQuestion();
		return "newest";
	}
	
	public String hottest() throws Exception{
		setHottestQuestion(questionService.getHotQuestion((int)getPageStart(), pageSize));
		recordCount=questionService.countAllQuestion();
		return "hottest";
	}

	public String ask() throws Exception {

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
								if (editQuestion.getPostedByUser().getId() == curUser
										.getId()) {
									
									editQuestion.setTitle(question.getTitle());
									editQuestion.setContent(question
											.getContent());
									editQuestion.setUpdatedDate(new Date());
									questionService.updateQuestionForTags(editQuestion,ts);
								}
							}
						}
					}
				}
				return "goToDetail";
			} else {
				return ERROR;
			}

		} else {
			if (question != null) {
				if (question.getId() != null) {
					question = questionService
							.getQuestionById(question.getId());
					if (question != null) {
						if (curUser != null) {
							if (question.getPostedByUser().getId() != curUser
									.getId()) {
								question = null;
							}
						}
					}
				}
			}
			return "ask";
		}
	}

	public String detail() throws Exception {
		if (question.getId() != null) {
			question = questionService.getQuestionDetailById(question.getId(),curUser==null?0:curUser.getId());
			if (question != null) {
				question.setViewCount(question.getViewCount() + 1);
				questionService.updateQuestion(question);
				answers=answerService.getAnswersByQuestionId(question.getId());
				return "detail";
			}
		}
		return ERROR;
	}

	public String newAnswer() throws Exception {
			if (question != null) {
				if (question.getId() != null){
					if ( answer != null) {
						answer.setCreatedByUserId(curUser.getId());
						answer.setVotedDownCount(0);
						answer.setPostedDate(new Date());
						answer.setUpdatedDate(new Date());
						answer.setVotedUpCount(0);
						answer.setUselessCount(0);
						answer.setQuestionId(question.getId());
					    answerService.insertAnswer(answer);
						return "goToAnswer";
					}
					else
					{
						return "goToDetail";
					}
				}
			}
		return ERROR;
	}






	public void setQuestion(Question question) {
		this.question = question;
	}
	public Question getQuestion() {
		return question;
	}


	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public Answer getAnswer() {
		return answer;
	}
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public IQuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public IAnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(IAnswerService answerService) {
		this.answerService = answerService;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}



	public List<Question> getPendingQuestion() {
		return pendingQuestion;
	}



	public void setPendingQuestion(List<Question> pendingQuestion) {
		this.pendingQuestion = pendingQuestion;
	}



	public List<Question> getNewestQuestion() {
		return newestQuestion;
	}



	public void setNewestQuestion(List<Question> newestQuestion) {
		this.newestQuestion = newestQuestion;
	}



	public List<Question> getHottestQuestion() {
		return hottestQuestion;
	}



	public void setHottestQuestion(List<Question> hottestQuestion) {
		this.hottestQuestion = hottestQuestion;
	}
}
