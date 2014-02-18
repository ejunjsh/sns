package com.sky.sns.mongo.entity;


import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection="User")
public class User {
	   private ObjectId id;
	   private String email;
	   private String nickName;
	   private String passWord;
	   private Date registerDate;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

   
}
