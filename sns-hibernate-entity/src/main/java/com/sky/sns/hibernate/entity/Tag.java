package com.sky.sns.hibernate.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Tag")
public class Tag {

	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "updatedDate")
	private Date updatedDate;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "cnSpell", unique = true)
	private String cnSpell;

	@Column(name = "description", length = java.lang.Integer.MAX_VALUE)
	private String description;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
	private List<Question> questions;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
	private List<User> users;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photoId")
	private Photo cover;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdByUser
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the letter
	 */
	public String getCnSpell() {
		return cnSpell;
	}

	/**
	 * @param letter
	 *            the letter to set
	 */
	public void setCnSpell(String cnSpell) {
		this.cnSpell = cnSpell;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQustions() {
		return questions;
	}

	/**
	 * @param qustions
	 *            the questions to set
	 */
	public void setQustions(List<Question> questions) {
		this.questions = questions;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
