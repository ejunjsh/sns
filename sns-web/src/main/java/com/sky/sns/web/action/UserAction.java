package com.sky.sns.web.action;

import java.util.List;

import com.sky.sns.mybatis.entity.*;
import com.sky.sns.mybatis.iService.*;
import com.sky.sns.web.utility.ImageUtils;
import com.sky.sns.web.utility.WebContext;

public class UserAction extends BasePageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2618506034475021502L;
	
	
	private IUserService userService;
	
	private IAnswerService answerService;
	
	private IQuestionService questionService;
	
	private IGroupPostService groupPostService;
	
    private IActivityService activityService;
    
    private IGroupService groupService;
    
    private IBlogService blogService;
    
    private IBlogCategoryService blogCategoryService;
    
    private INoticeService noticeService;
    
    private ITagService tagService;
    
    private IMessageService messageService;
    
    private IAlbumService albumService;
    
    private IPhotoService photoService;
	

	private User modifiedUser;
    
	private int errorIndex;
	
	private User spaceUser;
	
	private List<Answer> myAnswers;
	
	private List<Question> myQuestions;
	
	private List<GroupPost> myPosts;
	
	private List<Activity> myActivities;
	
	private List<Group> myGroups;
	
	private List<Blog> myBlogs;
	
	private List<BlogCategory> myBlogCategories;
	
	private List<Album> myAlbums;
	
	private List<Photo> myPhotos;
	
	private long categoryId=-1;
	
	private List<User> sideFollowingUsers;
	
	private List<User> sideFollowedUsers;
	
    private List<User> followingUsers;
	
	private List<User> followedUsers;
	
	private String curPage;
	
	private List<Notice> notices;
	
	private List<Tag> myTags;
	
	private User toUser;

	private Message message;
	
	private List<Message> messages;
	
    private Album album;
    
    private String tmpUrl;
	
   
	
	private void initialize()
	{
		this.spaceUser=userService.getUserDetailById(spaceUser.getId(),curUser==null?0:curUser.getId());
		this.sideFollowingUsers=userService.getFollowing(spaceUser.getId(), 0, 0, 8);
		this.sideFollowedUsers=userService.getFollowed(spaceUser.getId(), 0, 0, 8);
	}
	
	public String notice() throws Exception
	{
		if(curUser!=null)
		{
			notices=noticeService.getNoticeByUserId(curUser.getId(), (int)getPageStart(), pageSize);
			recordCount=noticeService.countNoticeByUserId(curUser.getId());
			noticeService.updateNoticeToRead(curUser.getId());
		}
		return "notice";
	}
	
	public String message() throws Exception
	{
		if(curUser!=null)
		{
			messages=messageService.getMessageByGroup(curUser.getId(), (int)getPageStart(), pageSize);
			recordCount=messageService.countMessageByGroup(curUser.getId());
		}
		return "message";
	}
	
	public String sendMessage() throws Exception
	{
		if(curUser!=null)
		{
		    if(toUser!=null)
		    {
		    	if(toUser.getId()>0)
        		{
            		toUser=userService.getUserById(toUser.getId());
        		}
	            else
	            {
	            	if(toUser.getNickName()!=null&&!toUser.getNickName().isEmpty())
	                {
	            	toUser=userService.getUserByNickName(toUser.getNickName());
	                }
	            	else
	            	{
	            		toUser=null;
	            	}
	            }
	            
			if(toUser!=null)
			{
				    messages=messageService.getMessageByUserId(curUser.getId(),toUser.getId(), (int)getPageStart(), pageSize);
                    recordCount= messageService.countMessageByUserId(curUser.getId(),toUser.getId());
			        messageService.updateMessageToRead(toUser.getId(), curUser.getId());
			}	            
		    }
            if(isPost())
            {
	            if(toUser!=null)
	            {
	            	if(toUser.getId()!=curUser.getId())
	            	{
	            	if(message!=null)
	            	{
	            		if(!message.getContent().isEmpty())
	            		{
	            			message.setFromUserId(curUser.getId());
	            			String group=messageService.getGroupByUserId(curUser.getId(), toUser.getId());
	            		    if(group!=null&&!group.isEmpty())
	            		    {
	            		    	message.setGroup(group);
	            		    }
	            		    else
	            		    {
	            		    	message.setGroup(java.util.UUID.randomUUID().toString());
	            		    }
	            		    message.setToUserId(toUser.getId());
	            		    messageService.insertMessage(message);
	            		    setPrompt("发送成功");
	            		    return "goToSendMessage";
	            		}
	            		else
	            		{
	            			setPrompt("请输入内容");
	            		}
	            	}
	            	}
	            	else
	            	{
	            		setPrompt("不能发给自己哦");
	            	}
	            }
	            else
	            {

	            	setPrompt("昵称不存在");
	            }
            }
           
		}
		return "sendMessage";
	}
	
	public String answer() throws Exception
	{
	   if(spaceUser!=null&&spaceUser.getId()>0)
	   {
		   initialize();
		   myAnswers=answerService.getAnswersByUserId(spaceUser.getId(), (int)getPageStart(), pageSize);
	   }
	   curPage="answer";
	   return curPage;
	}
	
	public String question() throws Exception
	{
	   if(spaceUser!=null&&spaceUser.getId()>0)
	   {
		   initialize();
		   myQuestions=questionService.getQuestionByUserId(spaceUser.getId(), (int)getPageStart(), pageSize);
	   }
	   curPage="question";
	   return curPage;
	}
	
	public String post() throws Exception
	{
	   if(spaceUser!=null&&spaceUser.getId()>0)
	   {
		   initialize();
		   myPosts=groupPostService.getGroupPostByUserId(spaceUser.getId(), pageSize,(int)getPageStart());
	   }
	   curPage="post";
	   return curPage;
	}
	
	public String activity() throws Exception
	{
	   if(spaceUser!=null&&spaceUser.getId()>0)
	   {
		   initialize();
		   myActivities=activityService.getActivityByUserId(spaceUser.getId(), (int)getPageStart(), pageSize);
	   }
	   curPage="activity";
	   return curPage;
	}
	
	public String blog() throws Exception
	{
	   if(spaceUser!=null&&spaceUser.getId()>0)
	   {
		   initialize();
		   myBlogs=blogService.getMyBlogByCategoryId(spaceUser.getId(), categoryId==0?null:categoryId, (int)getPageStart(), pageSize);
		   recordCount=blogService.countMyBlogByCategoryId(spaceUser.getId(), categoryId==0?null:categoryId);
		   myBlogCategories=blogCategoryService.getBlogCategoryByUserId(spaceUser.getId());
	   }
	   curPage="blog";
	   return curPage;
	}
	
	public String album() throws Exception
	{
		if(album==null||album.getId()==0)
		{
		   if(spaceUser!=null&&spaceUser.getId()>0)
		   {
			   initialize();
			   myAlbums=albumService.getAlbumsByUserId(spaceUser.getId(), (int)getPageStart(), pageSize);
			   recordCount=albumService.countAlbumsByUserId(spaceUser.getId());
		   }
		   curPage="albumList";
		}
		else
		{
			  if(spaceUser!=null&&spaceUser.getId()>0)
			   {
				   initialize();
				   album=albumService.getAlbumById(album.getId());
				   myPhotos=photoService.getPhotosByAlbumId(album.getId(),(int)getPageStart(), pageSize);
				   recordCount=photoService.countPhotosByAlbumId(album.getId());
			   }
			  curPage="albumDetail"; 
		}
	   return curPage;
	}
	
	public String following() throws Exception
	{
	   if(spaceUser!=null&&spaceUser.getId()>0)
	   {
		   initialize();
           followingUsers=userService.getFollowing(spaceUser.getId(), curUser==null?0:curUser.getId(), (int)getPageStart(), pageSize);
	   }
	   curPage="following";
		 return curPage;
	}
	
	public String follower() throws Exception
	{
	   if(spaceUser!=null&&spaceUser.getId()>0)
	   {
		   initialize();
           followedUsers=userService.getFollowed(spaceUser.getId(), curUser==null?0:curUser.getId(), (int)getPageStart(), pageSize);
	   }
	   curPage="follower";
		 return curPage;
	}
	
	public String group() throws Exception
	{
		 if(spaceUser!=null&&spaceUser.getId()>0)
		   {
			   initialize();
			   myGroups=groupService.getMyJoinedGroups(spaceUser.getId());
		   }
		 curPage="group";
		 return curPage;
	}
	
	public String tag() throws Exception
	{
		 if(spaceUser!=null&&spaceUser.getId()>0)
		   {
			   initialize();
			   myTags=tagService.getMyTags(spaceUser.getId(), curUser==null?0:curUser.getId(), (int)getPageStart(), pageSize);
		   }
		 curPage="tag";
		 return curPage;
	}
	
	public void setAnswerService(IAnswerService answerService) {
		this.answerService = answerService;
	}

	public String avatar() throws Exception
	{
		if(isPost())
    	{
    		if(tmpUrl!=null&&!tmpUrl.isEmpty()&&curUser!=null)
    		{
    			User savedUser=userService.getUserById(curUser.getId());
    			
    			String url=ImageUtils.SaveToAvatar(tmpUrl, "avatar/"+curUser.getId(),curUser.getIsWaterMark()==1);
    			
                List<Album> albums=albumService.getAlbumsByUserId(curUser.getId(),0, Integer.MAX_VALUE);
				
				Album album=null;
				for(Album a : albums)
				{
					if(a.getIsFixed()==1&&a.getTitle().equalsIgnoreCase("头像相册"))
					{
						album=a;
						break;
					}
				}
				if(album==null)
				{
					album=new Album();
					album.setDescription("头像相册");
					album.setIsFixed(1);
					album.setTitle("头像相册");
					album.setUserId(curUser.getId());
					albumService.insertAlbum(album);
				}
				
				Photo photo=new Photo();
				photo.setAlbumId(album.getId());
				photo.setDescription("头像");
				photo.setTitle("头像");
				photo.setUrl(url);
				photo.setUserId(curUser.getId());
				photoService.insertPhoto(photo);
				
    			savedUser.setPhotoId(photo.getId());
    			userService.updateUser(savedUser);

    			//refresh session
    			WebContext.removeCurrentUser(request);
    			WebContext.setCurrentUser(request, savedUser);
    			setPrompt("保存成功");
    		}
    	}
		modifiedUser=userService.getUserById(curUser.getId());
		return "avatar";
	}
	
	public String index() throws Exception
	{
		if(spaceUser!=null&&spaceUser.getId()>0)
		   {
			   initialize();
			   myActivities=activityService.getActivityByUserId(spaceUser.getId(),0, 5);
			   myBlogs=blogService.getMyBlogByCategoryId(spaceUser.getId(),(long) -1, 0, 5);
		   }
		   curPage="index";
		   return curPage;
	}
	

    public String profile() throws Exception
    {
    	if(isPost())
    	{
    		if(modifiedUser!=null&&curUser!=null)
    		{
    			if(modifiedUser.getNickName()==null||modifiedUser.getNickName().isEmpty())
    			{
    				errorIndex=2;
    			}
    			else if(userService.getUserByNickNameExceptCurUser(modifiedUser.getNickName(),curUser.getId())!=null)
		    	{
		    		errorIndex=1;
		    	}
    			else if(modifiedUser.getTitle()!=null&&!modifiedUser.getTitle().isEmpty()&&modifiedUser.getTitle().length()>100)
		    	{
		    		errorIndex=3;
		    	}
    			else if(modifiedUser.getBlogUrl()!=null&&!modifiedUser.getBlogUrl().isEmpty()&&modifiedUser.getBlogUrl().length()>200)
		    	{
		    		errorIndex=4;
		    	}
    			else if(modifiedUser.getDescription()!=null&&!modifiedUser.getDescription().isEmpty()&&modifiedUser.getDescription().length()>650)
		    	{
		    		errorIndex=5;
		    	}
    			if(errorIndex>0)
		    	{

		    	     return "profile";
		    	}
		    	else
		    	{
    				User savedUser=userService.getUserById(curUser.getId());
    				savedUser.setBlogUrl(modifiedUser.getBlogUrl());
    				savedUser.setDescription(modifiedUser.getDescription());
    				savedUser.setGender(modifiedUser.getGender());
    				savedUser.setIsWaterMark(modifiedUser.getIsWaterMark());
    				savedUser.setNickName(modifiedUser.getNickName());
    				savedUser.setTitle(modifiedUser.getTitle());
    				userService.updateUser(savedUser);
    				//refresh session
    				WebContext.removeCurrentUser(request);
    				WebContext.setCurrentUser(request, savedUser);
        			curUser=savedUser;
    				setPrompt("保存成功");
		    	}
    		}
    	}

    		modifiedUser=userService.getUserById(curUser.getId());
    		return "profile";
    	
    }
    
    public String redirect() throws Exception
    {
    	if(spaceUser!=null)
    	{
    		User user=userService.getUserByNickName(spaceUser.getNickName());
    		if(user!=null)
    		{
    			spaceUser.setId(user.getId());
    		}
    	}
    	return "redirect";
    }

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(User modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public int getErrorIndex() {
		return errorIndex;
	}

	public User getSpaceUser() {

		return spaceUser;
	}

	public void setSpaceUser(User spaceUser) {
		this.spaceUser = spaceUser;
	}



	public List<Answer> getMyAnswers() {
		return myAnswers;
	}

	public void setMyAnswers(List<Answer> myAnswers) {
		this.myAnswers = myAnswers;
	}

	public List<User> getSideFollowingUsers() {
		return sideFollowingUsers;
	}

	public void setSideFollowingUsers(List<User> sideFollowingUsers) {
		this.sideFollowingUsers = sideFollowingUsers;
	}

	public List<User> getSideFollowedUsers() {
		return sideFollowedUsers;
	}

	public void setSideFollowedUsers(List<User> sideFollowedUsers) {
		this.sideFollowedUsers = sideFollowedUsers;
	}

	public List<User> getFollowingUsers() {
		return followingUsers;
	}

	public void setFollowingUsers(List<User> followingUsers) {
		this.followingUsers = followingUsers;
	}

	public List<User> getFollowedUsers() {
		return followedUsers;
	}

	public void setFollowedUsers(List<User> followedUsers) {
		this.followedUsers = followedUsers;
	}

	public List<Question> getMyQuestions() {
		return myQuestions;
	}

	public void setMyQuestions(List<Question> myQuestions) {
		this.myQuestions = myQuestions;
	}
	
	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}
	
	public void setGroupPostService(IGroupPostService groupPostService) {
		this.groupPostService = groupPostService;
	}

	public List<GroupPost> getMyPosts() {
		return myPosts;
	}

	public void setMyPosts(List<GroupPost> myPosts) {
		this.myPosts = myPosts;
	}

	public List<Activity> getMyActivities() {
		return myActivities;
	}

	public void setMyActivities(List<Activity> myActivities) {
		this.myActivities = myActivities;
	}


	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}

	public List<Group> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<Group> myGroups) {
		this.myGroups = myGroups;
	}


	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public List<Blog> getMyBlogs() {
		return myBlogs;
	}

	public void setMyBlogs(List<Blog> myBlogs) {
		this.myBlogs = myBlogs;
	}

	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public List<BlogCategory> getMyBlogCategories() {
		return myBlogCategories;
	}

	public void setMyBlogCategories(List<BlogCategory> myBlogCategories) {
		this.myBlogCategories = myBlogCategories;
	}

	public void setBlogCategoryService(IBlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}


	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public List<Tag> getMyTags() {
		return myTags;
	}

	public void setMyTags(List<Tag> tags) {
		this.myTags = tags;
	}


	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}


	public void setAlbumService(IAlbumService albumService) {
		this.albumService = albumService;
	}


	public void setPhotoService(IPhotoService photoService) {
		this.photoService = photoService;
	}

	public List<Album> getMyAlbums() {
		return myAlbums;
	}

	public void setMyAlbums(List<Album> myAlbums) {
		this.myAlbums = myAlbums;
	}

	public List<Photo> getMyPhotos() {
		return myPhotos;
	}

	public void setMyPhotos(List<Photo> myPhotos) {
		this.myPhotos = myPhotos;
	}


	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getTmpUrl() {
		return tmpUrl;
	}

	public void setTmpUrl(String tmpUrl) {
		this.tmpUrl = tmpUrl;
	}
}
