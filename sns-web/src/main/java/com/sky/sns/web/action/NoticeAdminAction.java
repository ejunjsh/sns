package com.sky.sns.web.action;

import java.util.List;

import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.iService.INoticeService;
import com.sky.sns.web.pojo.AjaxCode;


public class NoticeAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -7437758238586905088L;
    
	private INoticeService noticeService;

	private com.sky.sns.mybatis.searchEntity.Notice params;
	
	private Notice notice;
	
	private List<Notice> notices;
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	public String searchSysNotice() throws Exception
	{
		if(params==null)
    		params =new com.sky.sns.mybatis.searchEntity.Notice();
    	
    	if(params.getPageIndex()>0)
    		params.setPageIndex(params.getPageIndex()-1);
         notices=noticeService.getSysNotice(params);
         module="searchSysNotice";
 		 return "page";
	}
	
//	public String doSysNotice() throws Exception{
//		  setModule("doSysNotice");
//			if (isPost()) {
//				if (curUser != null) {
//					if (notice != null && notice.getId()==0){
//                        notice.setNoticeType(NoticeTypeEnum.SYSNOTICE.getValue());
//                        notice.setScopeType(ScopeTypeEnum.ALL.getValue());
//						noticeService.insertNotice(notice);
//						} else {
//							if(notice.getId()>0)
//							{
//							   Notice editNotice=noticeService.getNoticeById(notice.getId());
//							   if(editNotice!=null)
//							   {
//								   editNotice.setContent(notice.getContent());
//								   editNotice.setTitle(notice.getTitle());
//							   noticeService.updateNotice(editNotice);
//							   }
//							}
//						}
//					jsonData.put("status", AjaxCode.successful);
//					return "json";
//				} else {
//					return ERROR;
//				}
//				}
//	  
//else {
//				if (notice != null) {
//					if (notice.getId()>0) {
//						notice = noticeService
//								.getNoticeById(notice.getId());
//					}
//				}
//				return "page";
//			}
//	  }

	public com.sky.sns.mybatis.searchEntity.Notice getParams() {
		return params;
	}

	public void setParams(com.sky.sns.mybatis.searchEntity.Notice params) {
		this.params = params;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}
}
