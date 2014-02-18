package com.sky.sns.hibernate.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;





@Entity
@Table(name="user_answer_vote")
public class AnswerVote {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
    private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
    private User User;
	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "answerId")
    private Answer answer;
	
	@Column(name = "value")
	private int value;

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
