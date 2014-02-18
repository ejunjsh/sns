package com.sky.sns.mongo.entity;


import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class SearchKeyWord {
	
   @Id
   private ObjectId id;
   
   private Date createdDate;
   
 
   private String keyWord;
   
   @Transient
   private int count;


public ObjectId getId() {
	return id;
}



public void setId(ObjectId id) {
	this.id = id;
}



public Date getCreatedDate() {
	return createdDate;
}



public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}



public String getKeyWord() {
	return keyWord;
}



public void setKeyWord(String keyWord) {
	this.keyWord = keyWord;
}



public int getCount() {
	return count;
}



public void setCount(int count) {
	this.count = count;
}
	 
	 

   


}

