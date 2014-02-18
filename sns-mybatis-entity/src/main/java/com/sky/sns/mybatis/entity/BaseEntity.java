package com.sky.sns.mybatis.entity;

import com.sky.sns.common.StringConversion;

public class BaseEntity implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -328227000895479759L;

	protected String getContentNoHtml(int count,String content) {
		if(content!=null)
		{
			String r= StringConversion.removeHtmlTag(content);
			if(r!=null&&r.length()>count&&count>0)
				return r.substring(0, count)+"...";
			else
				return r;
		}
		return null;
	}
	
	protected String getContentWithAtLink(String content)
	{
		return StringConversion.generateRefererLinks(content,null);
	}
}
