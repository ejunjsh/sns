package com.sky.sns.web.pojo;

public class QuestionCommentJsonObject {
     private int commentType;
     private String content;
     private long id;
     private long postedByUserId;
     private String postedByUserNickName;
     private String postedDate;
     private long refId;
     private long replyId;
	public int getCommentType() {
		return commentType;
	}
	public void setCommentType(int commentType) {
		this.commentType = commentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPostedByUserId() {
		return postedByUserId;
	}
	public void setPostedByUserId(long postedByUserId) {
		this.postedByUserId = postedByUserId;
	}
	public String getPostedByUserNickName() {
		return postedByUserNickName;
	}
	public void setPostedByUserNickName(String postedByUserNickName) {
		this.postedByUserNickName = postedByUserNickName;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	public long getRefId() {
		return refId;
	}
	public void setRefId(long refId) {
		this.refId = refId;
	}
	public long getReplyId() {
		return replyId;
	}
	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}
}
