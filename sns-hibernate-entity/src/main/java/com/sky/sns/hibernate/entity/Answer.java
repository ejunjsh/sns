package com.sky.sns.hibernate.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="Answer")
public class Answer {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
    private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "createdByUserId")
    private User createdByUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "questionId")
    private Question question;
	
	 @Column(name = "postedDate")
    private Date postedDate;
	 
	 @Column(name = "updatedDate")
    private Date updatedDate;
	 
	
	@Column(name = "content",length=java.lang.Integer.MAX_VALUE)
    private String content;
	
	 @ManyToMany(cascade = CascadeType.ALL)  
	 @JoinTable(name = "user_answer_useless",joinColumns = { @JoinColumn(name = "answerId")},inverseJoinColumns={@JoinColumn(name="userId") })
	private List<User> uselessUsers;
	


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
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}
	/**
	 * @param quesion the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	/**
	 * @return the postedDate
	 */
	public Date getPostedDate() {
		return postedDate;
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


		
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		

		public List<User> getUselessUsers() {
			return uselessUsers;
		}
		public void setUselessUsers(List<User> uselessUsers) {
			this.uselessUsers = uselessUsers;
		}
		

}
