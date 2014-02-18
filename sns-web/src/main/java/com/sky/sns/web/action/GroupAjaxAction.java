package com.sky.sns.web.action;




import com.sky.sns.mybatis.entity.Group;
import com.sky.sns.mybatis.entity.GroupPost;
import com.sky.sns.mybatis.entity.GroupPostComment;
import com.sky.sns.mybatis.iService.IGroupPostCommentService;
import com.sky.sns.mybatis.iService.IGroupPostService;
import com.sky.sns.mybatis.iService.IGroupService;
import com.sky.sns.web.pojo.AjaxCode;

public class GroupAjaxAction extends AjaxAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	private IGroupPostCommentService groupPostCommentService;
	private IGroupPostService groupPostService;
	private IGroupService groupService;
	
	private GroupPostComment comment;
	
	private GroupPost grouPost;
	
	private Group group;
    
	private int isCancel;

	
	public String deleteComment() throws Exception 
	{
		if(comment!=null)
		{
			groupPostCommentService.deleteGroupPostComment(comment.getId());   
		   jsonData.put("status", AjaxCode.successful);
   		    jsonData.put("data", null);
   			return "json";
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doRecommend() throws Exception 
	{
		if(grouPost!=null)
		{
		    if(groupPostService.recommendGroupPost(grouPost.getId(),curUser.getId()))
		    {
		    	jsonData.put("status", AjaxCode.successful);
	   		    jsonData.put("data", null);
	   			return "json";
		    }
		    else
		    {
		    	jsonData.put("status", AjaxCode.forbit);
				jsonData.put("data", null);
				return "json";
		    }
		    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doJoin() throws Exception 
	{
		if(group!=null)
		{
		    if(groupService.joinGroup(group.getId(),curUser.getId()))
		    {
		    	jsonData.put("status", AjaxCode.successful);
	   		    jsonData.put("data", null);
	   			return "json";
		    }
		    else
		    {
		    	jsonData.put("status", AjaxCode.forbit);
				jsonData.put("data", null);
				return "json";
		    }
		    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doQuit() throws Exception 
	{
		if(group!=null)
		{

		    	groupService.quitGroup(group.getId(),curUser.getId());
		    	jsonData.put("status", AjaxCode.successful);
	   		    jsonData.put("data", null);
	   			return "json";

		    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doBest() throws Exception 
	{
		if(group!=null&&grouPost!=null)
		{
			group=groupService.getGroupById(group.getId());
		    if(group!=null&&group.getCreatedByUserId()==curUser.getId())
		    {
		    	grouPost=groupPostService.getGroupPostById(grouPost.getId());
		    	if(grouPost!=null)
		    	{
		    		if(isCancel==1)
		    		{
		    			grouPost.setIsBest(0);
		    		}
		    		else
		    		{
		    			grouPost.setIsBest(1);
		    		}
		    		groupPostService.updateGroupPost(grouPost);
			    	jsonData.put("status", AjaxCode.successful);
		   		    jsonData.put("data", null);
		   			return "json";
		    	}
		    }	    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}
	
	public String doTop() throws Exception 
	{
		if(group!=null&&grouPost!=null)
		{
			group=groupService.getGroupById(group.getId());
		    if(group!=null&&group.getCreatedByUserId()==curUser.getId())
		    {
		    	grouPost=groupPostService.getGroupPostById(grouPost.getId());
		    	if(grouPost!=null)
		    	{
		    		if(isCancel==1)
		    		{
		    			grouPost.setIsTop(0);
		    		}
		    		else
		    		{
		    			grouPost.setIsTop(1);
		    		}
		    		groupPostService.updateGroupPost(grouPost);
			    	jsonData.put("status", AjaxCode.successful);
		   		    jsonData.put("data", null);
		   			return "json";
		    	}
		    }	    
		}
		jsonData.put("status", AjaxCode.getFail);
		jsonData.put("data", null);

		return "json";
	}

	public void setGroupPostCommentService(IGroupPostCommentService groupPostCommentService) {
		this.groupPostCommentService = groupPostCommentService;
	}


	public void setGroupPostService(IGroupPostService groupPostService) {
		this.groupPostService = groupPostService;
	}

	public GroupPost getGroupPost() {
		return grouPost;
	}

	public void setGroupPost(GroupPost groupPost) {
		this.grouPost = groupPost;
	}

	public GroupPostComment getComment() {
		return comment;
	}

	public void setComment(GroupPostComment comment) {
		this.comment = comment;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public int getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}
}
