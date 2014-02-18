package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.Group;
import com.sky.sns.mybatis.entity.GroupCategory;
import com.sky.sns.mybatis.entity.GroupPost;
import com.sky.sns.mybatis.entity.GroupPostComment;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IGroupCategoryService;
import com.sky.sns.mybatis.iService.IGroupPostCommentService;
import com.sky.sns.mybatis.iService.IGroupPostService;
import com.sky.sns.mybatis.iService.IGroupService;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.mybatis.iService.IUserService;

public class GroupAction extends BasePageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	private IUserService userService;
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
	private IGroupService groupService;
	private IGroupPostService groupPostService;
	private IGroupPostCommentService groupPostCommentService;
	
	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}
	private ITagService tagService;
	private Group group;
	
	private GroupPost groupPost;
	
	private List<GroupPostComment> comments;
	
	private List<GroupPost> recommendPosts;
	
	public GroupPostComment getGroupPostComment() {
		return groupPostComment;
	}
	public void setGroupPostComment(GroupPostComment groupPostComment) {
		this.groupPostComment = groupPostComment;
	}
	private GroupPostComment groupPostComment;
	
	private List<Group> allGroups;
	private List<GroupCategory> allCategorys;
	private List<Group> hotGroups;
	private List<Group> newGroups;
	private List<Group> relatedGroups;
	private GroupCategory curCategory;
	
	private String tags;
	
	private List<GroupPost> postInGroup;
	private List<GroupPost> hotPosts;
	
	private List<Group> searchGroups;
	
	private List<User> members;
	
	private List<User> activeMembers;
	
	private String key;
	
	private int isBest;
	
	private IGroupCategoryService groupCategoryService;
	public String apply ()throws Exception
	{
		if (isPost()) {
			if(curUser!=null)
			{
			    if(group!=null)
			    {
			    	if(group.getIsNeedValidate()!=1)
			    	{
			    		group.setIsNeedValidate(2);
			    	}
			    	if(group.getIsOpenContent()!=1)
			    	{
			    		group.setIsOpenContent(2);
			    	}
			    	group.setCreatedByUserId(curUser.getId());
			    	groupService.insertGroup(group);
			    }
				return "success";
			}
			return ERROR;
		}
		else
		return "apply";
	}
	
	public String groupDetail ()throws Exception
	{
		if(group!=null&&group.getId()>0)
		{
			group=groupService.getGroupDetailById(group.getId(),curUser==null?0:curUser.getId());
			if(group!=null)
			{
				postInGroup=groupPostService.getGroupPostInGroup(group.getId(), 0, pageSize,(int)getPageStart());
				recordCount=groupPostService.countGroupPostInGroup(group.getId(), 0);
				
				relatedGroups=groupService.getRelatedGroup(group.getId(), 10, 0);
				
				activeMembers=groupService.getActiveMember(group.getId(),8,0);
			}
		}
		return "groupDetail";
	}
	
	public String members() throws Exception
	{
		if(group!=null&&group.getId()>0)
		{
			group=groupService.getGroupById(group.getId());
			members=groupService.getGroupMembers(key, group.getId(), 72, (int)getPageStart());
		    recordCount=groupService.countGroupMembers(key, group.getId());
		    return "members";
		}
		return ERROR;
	}
	
	public String search ()throws Exception
	{
	   if(key!=null&&!key.isEmpty())
	   {
		   if(curUser!=null)
		   {
		   searchGroups=groupService.getGroupByKey(curUser.getId(), key);
		   }
		   else
		   {
			   searchGroups=groupService.getGroupByKey(0, key);
		   }
	   }
       return "search";		 
	}
	
	
	public String posts ()throws Exception
	{
		if(group!=null&&group.getId()>0)
		{
			group=groupService.getGroupById(group.getId());
			if(group!=null)
			{
				postInGroup=groupPostService.getGroupPostInGroup(group.getId(), isBest, pageSize,(int)getPageStart());
				recordCount=groupPostService.countGroupPostInGroup(group.getId(), isBest);
			}
		}
		return "posts";
	}
	
	public String hotPosts ()throws Exception
	{
		Calendar cal = Calendar.getInstance();
		Date now=cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date before=cal.getTime();
		
	    setHotPosts(groupPostService.getHottestPostsInPeriod(now, before,pageSize,(int)getPageStart()));
	    recordCount=groupPostService.countHottestPostsInPeriod(now, before);

		return "hotPosts";
	}
	
	public String all ()throws Exception
	{
		allCategorys=groupCategoryService.getAllGroupCategory();
		if(curCategory==null)
		{

			if(allCategorys!=null&&allCategorys.size()>0)
			{
				curCategory=allCategorys.get(0);
			}
		}

			if(curCategory!=null&&curCategory.getId()>0)
			{
				if(curUser!=null)
				{
				allGroups=groupService.getGroupByCategory(curCategory.getId(), curUser.getId(), pageSize, (int)getPageStart());
				}
				else
				{
					allGroups=groupService.getGroupByCategory(curCategory.getId(), 0, pageSize, (int)getPageStart());
				}
				recordCount=groupService.countGroupByCategory(curCategory.getId());
				hotGroups=groupService.getHottestGroup(pageSize, 0);
				newGroups=groupService.getNewGroup(pageSize, 0);
			}
		

		
		return "all";
	}
	
	public String postDetail() throws Exception {
		if (groupPost.getId() != null) {
			groupPost = groupPostService.getGroupPostById(groupPost.getId());
			if (groupPost != null) {
				groupPost.setViewCount(groupPost.getViewCount() + 1);
				groupPostService.updateGroupPost(groupPost);
				comments=groupPostCommentService.getGroupPostCommentByGroupPostId(groupPost.getId(),(int)getPageStart(),pageSize);
				this.recordCount=groupPostCommentService.countGroupPostCommentByGroupPostId(groupPost.getId());
				recommendPosts=groupPostService.getRecommendPosts(groupPost.getGroupId(), groupPost.getId(),0,5);
				return "postDetail";
			}
		}
		return ERROR;
	}
	
	public String newPostComment() throws Exception {
		if(curUser!=null)
		{
		if (groupPost != null) {
			if (groupPost.getId() != null){
				if (groupPostComment != null) {
					groupPostComment.setGroupPostId(groupPost.getId());
					groupPostComment.setPostedByUserId(curUser.getId());
					groupPostComment.setPostedDate(new Date());
					groupPostCommentService.insertGroupPostComment(groupPostComment);
					this.recordCount=groupPostCommentService.countGroupPostCommentByGroupPostId(groupPost.getId());
					this.pageNo=(int) ((this.recordCount+this.pageSize-1)/this.pageSize);
					return "goToComment";
				}
			}
				
			}
	}
		else
		{
			return "goToPostDetail";
		}
	return ERROR;
}
	
	public String post() throws Exception {
		if (isPost()) {
			if (curUser != null&&group!=null&&group.getId()>0) {
				group=groupService.getGroupById(group.getId());
				if(group!=null)
				{
				if (!tags.isEmpty()) {
					List<Tag> newTags = new ArrayList<Tag>();
					String[] ts = tags.split(",");
					if (groupPost != null && groupPost.getId() == null) {
						for (String s : ts) {
							Tag tag = tagService.getTagById(Integer.parseInt(s
									.trim()));
							if (tag != null) {
								newTags.add(tag);
							}
						}
						groupPost.setPostedByUserId(curUser.getId());
						groupPost.setPostedDate(new Date());
						groupPost.setStatus(StatusEnum.NORMAL.getValue());
						groupPost.setUpdatedDate(new Date());
						groupPost.setViewCount(0);
						groupPost.setTags(newTags);
						groupPost.setIsBest(0);
						groupPost.setIsTop(0);
						groupPost.setGroupId(group.getId());
						groupPostService.insertGroupPost(groupPost);
					} else {
						if (groupPost.getId() > 0) {
							GroupPost editGroupPost = groupPostService
									.getGroupPostById(groupPost.getId());
							if (editGroupPost != null) {
								if (editGroupPost.getPostedByUser().getId() == curUser
										.getId()) {
									
									editGroupPost.setTitle(groupPost.getTitle());
									editGroupPost.setContent(groupPost
											.getContent());
									editGroupPost.setUpdatedDate(new Date());
									groupPostService.updateGroupPostForTags(editGroupPost,ts);
								}
							}
						}
					}
				}
				
				}
				return "goToPostDetail";
			} else {
				return ERROR;
			}

		} else {
			if (groupPost != null) {
				if (groupPost.getId() != null) {
					groupPost = groupPostService
							.getGroupPostById(groupPost.getId());
					group=groupService.getGroupById(groupPost.getGroupId());
					if (group != null&&groupPost!=null) {
						if (curUser != null) {
							if (groupPost.getPostedByUser().getId() != curUser
									.getId()) {
								groupPost = null;
							}
						}
					}
				}

			}
			else
			{
				if(group!=null&&group.getId()!=null)
				group=groupService.getGroupById(group.getId());
			}
			return "post";
		}
	}

	
	public List<Group> getAllGroups() {
		return allGroups;
	}
	public void setAllGroups(List<Group> allGroups) {
		this.allGroups = allGroups;
	}
	public List<GroupCategory> getAllCategorys() {
		return allCategorys;
	}
	public void setAllCategorys(List<GroupCategory> allCategorys) {
		this.allCategorys = allCategorys;
	}
	public List<Group> getHotGroups() {
		return hotGroups;
	}
	public void setHotGroups(List<Group> hotGroups) {
		this.hotGroups = hotGroups;
	}
	public List<Group> getNewGroups() {
		return newGroups;
	}
	public void setNewGroups(List<Group> newGroups) {
		this.newGroups = newGroups;
	}
	public GroupCategory getCurCategory() {
		return curCategory;
	}
	public void setCurCategory(GroupCategory curCategory) {
		this.curCategory = curCategory;
	}

	public void setGroupCategoryService(IGroupCategoryService groupCategoryService) {
		this.groupCategoryService = groupCategoryService;
	}

	public void setGroupPostService(IGroupPostService groupPostService) {
		this.groupPostService = groupPostService;
	}

	public void setGroupPostCommentService(IGroupPostCommentService groupPostCommentService) {
		this.groupPostCommentService = groupPostCommentService;
	}
	public GroupPost getGroupPost() {
		return groupPost;
	}
	public void setGroupPost(GroupPost groupPost) {
		this.groupPost = groupPost;
	}
	public List<GroupPostComment> getComments() {
		return comments;
	}
	public void setComments(List<GroupPostComment> comments) {
		this.comments = comments;
	}
	public List<GroupPost> getRecommendPosts() {
		return recommendPosts;
	}
	public void setRecommendPosts(List<GroupPost> recommendPosts) {
		this.recommendPosts = recommendPosts;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<GroupPost> getPostInGroup() {
		return postInGroup;
	}
	public void setPostInGroup(List<GroupPost> postInGroup) {
		this.postInGroup = postInGroup;
	}
	public int getIsBest() {
		return isBest;
	}
	public void setIsBest(int isBest) {
		this.isBest = isBest;
	}
	public List<GroupPost> getHotPosts() {
		return hotPosts;
	}
	public void setHotPosts(List<GroupPost> hotPosts) {
		this.hotPosts = hotPosts;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Group> getSearchGroups() {
		return searchGroups;
	}
	public void setSearchGroups(List<Group> searchGroups) {
		this.searchGroups = searchGroups;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public List<Group> getRelatedGroups() {
		return relatedGroups;
	}
	public void setRelatedGroups(List<Group> relatedGroups) {
		this.relatedGroups = relatedGroups;
	}
	public List<User> getActiveMembers() {
		return activeMembers;
	}
	public void setActiveMembers(List<User> activeMembers) {
		this.activeMembers = activeMembers;
	}
}
