package com.sky.sns.web.action;

import java.util.HashMap;
import java.util.Map;

public class AjaxAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	public Map<String,Object> getJsonData() {
		return jsonData;
	}
	public void setJsonData(Map<String,Object> jsonData) {
		this.jsonData = jsonData;
	}
	
    protected Map<String,Object> jsonData=new HashMap<String,Object>();
}
