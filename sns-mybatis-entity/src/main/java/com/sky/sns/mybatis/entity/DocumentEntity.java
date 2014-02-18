package com.sky.sns.mybatis.entity;

import java.util.Date;

import com.sky.sns.common.DateUtils;

public class DocumentEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6476112471191019893L;
	
	private String id;
	
	private String title;
	
	private String content;
	
	private String tagString;
	
	private int type;
	
	private Date date;
	
	private long fromValue;
	
	private String fromName;
	
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}
	
	public String getDateF() {
		return DateUtils.getDaysBeforeNow(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getFromValue() {
		return fromValue;
	}

	public void setFromValue(long fromValue) {
		this.fromValue = fromValue;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
