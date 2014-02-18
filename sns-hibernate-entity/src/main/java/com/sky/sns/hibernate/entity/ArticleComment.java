package com.sky.sns.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ArticleComment")
public class ArticleComment {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
     private long id;
	@Column(name = "content",length=java.lang.Integer.MAX_VALUE)
     private String content;
     
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "postedByUserId")
     private User postedByUser;
     
	 @Column(name = "postedDate")
     private Date postedDate;
     


	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "articleId")
    private Article article;

     
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getPostedByUser() {
		return postedByUser;
	}
	public void setPostedByUser(User postedByUser) {
		this.postedByUser = postedByUser;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}


}
