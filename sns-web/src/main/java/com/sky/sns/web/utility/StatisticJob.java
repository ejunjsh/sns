package com.sky.sns.web.utility;

import java.util.Calendar;
import java.util.List;

import com.sky.sns.mongo.entity.SearchKeyWord;
import com.sky.sns.mongo.iRepository.ISearchKeyWordRepository;

public class StatisticJob {
	
   
	private ISearchKeyWordRepository searchKeyWordRepository;
	private StatisticObject statisticObject;
	public void work()
    {
		  Calendar c=Calendar.getInstance();
          c.add(Calendar.MONTH, -1);
          
          List<SearchKeyWord> keys=searchKeyWordRepository.getTopData(c.getTime(), 10);
          statisticObject.setKeys(keys);
    }

	public void setStatisticObject(StatisticObject statisticObject) {
		this.statisticObject = statisticObject;
	}

	public void setSearchKeyWordRepository(ISearchKeyWordRepository searchKeyWordRepository) {
		this.searchKeyWordRepository = searchKeyWordRepository;
	}


}
