package com.sky.sns.mongo.repository;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import com.sky.sns.mongo.iRepository.*;

public  class RepositoryFactory {
	private static ApplicationContext ctx;
	
	static
	{
		ctx= new AnnotationConfigApplicationContext(MongoConfig.class);
	}
	
	
	public static IUserRepository getUserRepository()
	{
		IUserRepository p = (IUserRepository) ctx
				.getBean("userRepository");
		return p;
	}
	
	public static IQuestionRepository getQuestionRepository()
	{
		IQuestionRepository p = (IQuestionRepository) ctx
				.getBean("questionRepository");
		return p;
	}
}
