package com.sky.sns.web.action;

import java.util.List;

import com.sky.sns.mybatis.entity.Article;
import com.sky.sns.mybatis.entity.Blog;
import com.sky.sns.mybatis.entity.GroupPost;
import com.sky.sns.mybatis.entity.Question;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IArticleService;
import com.sky.sns.mybatis.iService.IBlogService;
import com.sky.sns.mybatis.iService.IGroupPostService;
import com.sky.sns.mybatis.iService.IQuestionService;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.mybatis.iService.IUserService;

public class TagAction extends BasePageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1567909995460604325L;
    
	private String tabName;
	
	private Tag tag;
	
	private ITagService tagService;
	
	private IQuestionService questionService;
	
	private IGroupPostService groupPostService;
	
	private IBlogService blogService;
	
	private IArticleService articleService;
	
	private IUserService userService;
	
	private long id;
	
	private String spell;
	
	private List<Question> questions;
	
	private List<GroupPost> posts;
	
	private List<Blog> blogs;
	
	private List<Article> articles;
	
	private List<User> users;
	
	private List<Tag> tags;
	
	private String key;
	
	

	public String all() throws Exception{
	    tags=tagService.getAllTags(key, curUser!=null?curUser.getId():0, (int)getPageStart(), pageSize);
		recordCount=tagService.countAllTags(key);
		return "all";
	}
	
	public String detail() throws Exception{
		if(id>0)
		{
			tag=tagService.getTagById(id);
		}
		else if(spell!=null&&!spell.isEmpty())
		{
			tag=tagService.getTagBySpell(spell,curUser!=null?curUser.getId():0);
		}
		if(tag!=null)
		{
			if(tabName==null||tabName=="")
			{
				questions=questionService.getQuestionsByTagId(tag.getId(),(int)getPageStart(), pageSize);
				recordCount=questionService.countQuestionsByTagId(tag.getId());
			}
			
			if(tabName!=null&&tabName.equalsIgnoreCase("post"))
			{
				posts=groupPostService.getGroupPostsByTagId(tag.getId(),(int)getPageStart(), pageSize);
				recordCount=groupPostService.countGroupPostsByTagId(tag.getId());
			}
			if(tabName!=null&&tabName.equalsIgnoreCase("blog"))
			{
				blogs=blogService.getBlogsByTagId(tag.getId(),(int)getPageStart(), pageSize);
				recordCount=blogService.countBlogsByTagId(tag.getId());
			}
			if(tabName!=null&&tabName.equalsIgnoreCase("article"))
			{
				articles=articleService.getArticlesByTagId(tag.getId(),(int)getPageStart(), pageSize);
				recordCount=articleService.countArticlesByTagId(tag.getId());
			}
			if(tabName!=null&&tabName.equalsIgnoreCase("user"))
			{
				setUsers(userService.getUsersByTagId(tag.getId(),curUser==null?0:curUser.getId(),(int)getPageStart(), pageSize));
				recordCount=userService.countUsersByTagId(tag.getId());
			}
		}
		return "detail";
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}


	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<GroupPost> getPosts() {
		return posts;
	}

	public void setPosts(List<GroupPost> posts) {
		this.posts = posts;
	}


	public void setGroupPostService(IGroupPostService groupPostService) {
		this.groupPostService = groupPostService;
	}



	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
