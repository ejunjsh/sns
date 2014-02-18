package com.sky.sns.mybatis.entity;
import java.util.Date;

import com.sky.sns.common.DateUtils;



public class Answer extends BaseEntity{
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -4396006730684649990L;

	private long id;
	
    private long createdByUserId;
    private User createdByUser;
	
    private long questionId;
	

    private Date postedDate;
	 

    private Date updatedDate;
	 

    private int votedUpCount;
	 

    private int votedDownCount;
	
	
    private String content;
	
    private int uselessCount;
	
	private int commentCount;
	
	private Question question;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the createdByUser
	 */
	public User getCreatedByUser() {
		return createdByUser;
	}
	/**
	 * @param createdByUser the createdByUser to set
	 */
	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	/**
	 * @return the postedDate
	 */
	public Date getPostedDate() {
		return postedDate;
	}
	
	public String getPostedDateF() {
		return DateUtils.getDaysBeforeNow(postedDate);
	}
	/**
	 * @param postedDate the postedDate to set
	 */
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	 public int getVotedUpCount() {
			return votedUpCount;
		}
		public void setVotedUpCount(int votedUpCount) {
			this.votedUpCount = votedUpCount;
		}
		public int getVotedDownCount() {
			return votedDownCount;
		}
		public void setVotedDownCount(int votedDownCount) {
			this.votedDownCount = votedDownCount;
		}
		
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
		public int getUselessCount() {
			return uselessCount;
		}
		public void setUselessCount(int uselessCount) {
			this.uselessCount = uselessCount;
		}
		public long getCreatedByUserId() {
			return createdByUserId;
		}
		public void setCreatedByUserId(long createdByUserId) {
			this.createdByUserId = createdByUserId;
		}
		public long getQuestionId() {
			return questionId;
		}
		public void setQuestionId(long questionId) {
			this.questionId = questionId;
		}
		public int getCommentCount() {
			return commentCount;
		}
		public void setCommentCount(int commentCount) {
			this.commentCount = commentCount;
		}
		
		public String getContentNoHtml100() {
			return getContentNoHtml(100,content);
		}
		public Question getQuestion() {
			return question;
		}
		public void setQuestion(Question question) {
			this.question = question;
		}
		
		public String getContentWithAtLink()
		{
			return getContentWithAtLink(content);
		}
		
}
