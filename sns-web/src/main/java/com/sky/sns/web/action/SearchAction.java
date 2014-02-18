package com.sky.sns.web.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.sky.sns.enumeration.DocTypeEnum;
import com.sky.sns.mybatis.entity.DocumentEntity;
import com.sky.sns.mybatis.searchEntity.DocSearchParams;
import com.sky.sns.web.utility.LuceneUtil;
import com.sky.sns.web.utility.StatisticObject;

public class SearchAction extends BasePageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	
	private int searchType;
	
	private String key;
	
	private LuceneUtil luceneUtil;
	
	private List<DocumentEntity> docs;
	
	private DocumentEntity recommendGroupDoc;
	
    private StatisticObject statisticObject;
	
	public String all() throws Exception
	{
		searchType=0;
		if(key!=null&&!key.isEmpty())
		{
            DocSearchParams params=new DocSearchParams();
            params.setKeyWord(key);
            params.setPageIndex(pageNo-1);
            params.setPageSize(pageSize);
            params.setType(searchType);
			docs=luceneUtil.searchIndex(params);
			recordCount=params.getTotal();
			
			params.setKeyWord(key);
            params.setPageIndex(0);
            params.setPageSize(1);
            params.setType(DocTypeEnum.Group.getValue());
            List<DocumentEntity> groupDocs=luceneUtil.searchIndex(params);
            if(groupDocs!=null&&groupDocs.size()>0)
            {
            	recommendGroupDoc=groupDocs.get(0);
            }
		}
		return SUCCESS;
	}
	
	public String question() throws Exception
	{
		searchType=DocTypeEnum.Question.getValue();
		if(key!=null&&!key.isEmpty())
		{
            DocSearchParams params=new DocSearchParams();
            params.setKeyWord(key);
            params.setPageIndex(pageNo-1);
            params.setPageSize(pageSize);
            params.setType(searchType);
			docs=luceneUtil.searchIndex(params);
			recordCount=params.getTotal();
		}
		return SUCCESS;
	}
	
	public String article() throws Exception
	{
		searchType=DocTypeEnum.Article.getValue();
		if(key!=null&&!key.isEmpty())
		{
            DocSearchParams params=new DocSearchParams();
            params.setKeyWord(key);
            params.setPageIndex(pageNo-1);
            params.setPageSize(pageSize);
            params.setType(searchType);
			docs=luceneUtil.searchIndex(params);
			recordCount=params.getTotal();
		}
		return SUCCESS;
	}
	
	public String post() throws Exception
	{
		searchType=DocTypeEnum.Post.getValue();;
		if(key!=null&&!key.isEmpty())
		{
            DocSearchParams params=new DocSearchParams();
            params.setKeyWord(key);
            params.setPageIndex(pageNo-1);
            params.setPageSize(pageSize);
            params.setType(searchType);
			docs=luceneUtil.searchIndex(params);
			recordCount=params.getTotal();
		}
		return SUCCESS;
	}
	
	public String blog() throws Exception
	{
		searchType=DocTypeEnum.Blog.getValue();;
		if(key!=null&&!key.isEmpty())
		{
            DocSearchParams params=new DocSearchParams();
            params.setKeyWord(key);
            params.setPageIndex(pageNo-1);
            params.setPageSize(pageSize);
            params.setType(searchType);
			docs=luceneUtil.searchIndex(params);
			recordCount=params.getTotal();
		}
		return SUCCESS;
	}
	
	public String user() throws Exception
	{
		searchType=DocTypeEnum.User.getValue();
		if(key!=null&&!key.isEmpty())
		{
            DocSearchParams params=new DocSearchParams();
            params.setKeyWord(key);
            params.setPageIndex(pageNo-1);
            params.setPageSize(pageSize);
            params.setType(searchType);
			docs=luceneUtil.searchIndex(params);
			recordCount=params.getTotal();
		}
		return SUCCESS;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String getKey() {
		return key;
	}
	
	public String getEncodeKey()
	{
		try {
			return java.net.URLEncoder.encode(key,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public void setLuceneUtil(LuceneUtil luceneUtil) {
		this.luceneUtil = luceneUtil;
	}

	public List<DocumentEntity> getDocs() {
		return docs;
	}

	public void setDocs(List<DocumentEntity> docs) {
		this.docs = docs;
	}

	public DocumentEntity getRecommendGroupDoc() {
		return recommendGroupDoc;
	}

	public void setRecommendGroupDoc(DocumentEntity recommendGroupDoc) {
		this.recommendGroupDoc = recommendGroupDoc;
	}

	public void setStatisticObject(StatisticObject statisticObject) {
		this.statisticObject = statisticObject;
	}

	public StatisticObject getStatisticObject() {
		return statisticObject;
	}
}
