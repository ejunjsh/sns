package com.sky.sns.mongo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import com.sky.sns.mongo.entity.SearchKeyWord;
import com.sky.sns.mongo.iRepository.ISearchKeyWordRepository;

public class SearchKeyWordRepository extends BaseRepository<SearchKeyWord,ObjectId> implements ISearchKeyWordRepository {

	public List<SearchKeyWord> getTopData(Date recent, int top) {
		GroupByResults<SearchKeyWord> results= getMongoTemplate().group(Criteria.where("createdDate").gte(recent), "searchKeyWord", GroupBy.key("keyWord").initialDocument("{ count: 0 }").reduceFunction(
				"function(doc, prev){ prev.count += 1 }"), 
				SearchKeyWord.class);
		BasicDBList list = (BasicDBList)results.getRawResults().get("retval");  
		
		List<SearchKeyWord> keys=new ArrayList<SearchKeyWord>();
        for (int i = 0; i < list.size(); i ++) {  
            BasicDBObject obj = (BasicDBObject)list.get(i);  
            SearchKeyWord key=new SearchKeyWord();
            key.setCount((int)Float.parseFloat(obj.get("count").toString()) );
            key.setKeyWord(obj.get("keyWord").toString());
            keys.add(key);
        } 
        
        for(int i=0;i<keys.size();i++)
        {
        	for(int j=0;j<keys.size();j++)
        	{
        		SearchKeyWord tKey;
        		if(keys.get(i).getCount()<keys.get(j).getCount())
        		{
        			tKey=keys.get(j);
        			keys.set(j, keys.get(i));
        			keys.set(i, tKey);
        		}
        	}
        }
        
        
        int length=0;
		if(keys.size()>top)
		{
			length=top;
		}
		else
		{
			length=keys.size();
		}
        List<SearchKeyWord> rKeys=new ArrayList<SearchKeyWord>(top);
        
        for(int i=0;i<length;i++)
        {
        	rKeys.add(keys.get(length-i-1));
        }
        
		return rKeys;
	}

   

}
