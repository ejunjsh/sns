package com.sky.sns.web.action;

public class BaseAdminAction extends AjaxAction {

	private static final long serialVersionUID = 5528665058166576086L;
	
	protected String module;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
