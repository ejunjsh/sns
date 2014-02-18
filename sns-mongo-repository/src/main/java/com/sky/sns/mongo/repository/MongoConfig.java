package com.sky.sns.mongo.repository;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.ImportResource;  
//import Idiot.Questions.Mongo.IRepository.*;


import com.mongodb.Mongo;
 
/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
@ImportResource("classpath:beans.xml") 
public class MongoConfig  {
 

	public @Bean Mongo mongo() throws Exception {
 
		return new Mongo("localhost:27017");
	}
 

	public @Bean MongoTemplate mongoTemplate() throws Exception {
 
		return new MongoTemplate(mongo(),"Sky");
	}
	
}