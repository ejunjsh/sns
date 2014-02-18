package com.sky.sns.web.utility;

import java.util.ArrayList;
import java.util.List;

import com.sky.sns.mybatis.entity.*;
import com.sky.sns.mybatis.iService.*;

public class LuceneJob {
	
    private IBlogService blogService;
    
    private IQuestionService questionService;
    
    private IArticleService articleService;
    
    private IGroupService groupService;
    
    private IGroupPostService groupPostService;
    
    private IUserService userService;
	
	private LuceneUtil luceneUtil;
	
	public void work()
    {
         List<Blog> blogs=blogService.getAllBlogs();
         List<Article> articles=articleService.getAllArticles();
         List<Group> groups=groupService.getAllGroups();
         List<GroupPost> posts=groupPostService.getAllGroupPosts();
         List<Question> questions=questionService.getAllQuestions();
         List<User> users=userService.getAllUsers();
         List<DocumentEntity> docs=new ArrayList<DocumentEntity>();
         for(Blog blog :blogs)
         {
        	 docs.add(blog.convertToDoc());
         }
         for(Article article :articles)
         {
        	 docs.add(article.convertToDoc());
         }
         for(Group group :groups)
         {
        	 docs.add(group.convertToDoc());
         }
         for(GroupPost post :posts)
         {
        	 docs.add(post.convertToDoc());
         }
         for(Question question :questions)
         {
        	 docs.add(question.convertToDoc());
         }
         for(User user :users)
         {
        	 docs.add(user.convertToDoc());
         }
         luceneUtil.createIndex(docs);
    }


	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}


	public void setLuceneUtil(LuceneUtil luceneUtil) {
		this.luceneUtil = luceneUtil;
	}


	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}


	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}


	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}


	public void setGroupPostService(IGroupPostService groupPostService) {
		this.groupPostService = groupPostService;
	}


	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
