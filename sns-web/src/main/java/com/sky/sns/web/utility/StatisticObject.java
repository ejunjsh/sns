package com.sky.sns.web.utility;

import java.util.List;

import com.sky.sns.mongo.entity.SearchKeyWord;

public class StatisticObject {
    private List<SearchKeyWord> keys;

	public List<SearchKeyWord> getKeys() {
		return keys;
	}

	public void setKeys(List<SearchKeyWord> keys) {
		this.keys = keys;
	}
}
