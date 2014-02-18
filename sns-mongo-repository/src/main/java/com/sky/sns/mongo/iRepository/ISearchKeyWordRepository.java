package com.sky.sns.mongo.iRepository;


import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.sky.sns.mongo.entity.SearchKeyWord;

public interface ISearchKeyWordRepository extends IBaseRepository<SearchKeyWord,ObjectId> {
	  //to do something.
	
	   List<SearchKeyWord> getTopData(Date recent,int top);
	}
