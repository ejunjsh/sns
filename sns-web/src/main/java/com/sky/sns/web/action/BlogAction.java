package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.Blog;
import com.sky.sns.mybatis.entity.BlogCategory;
import com.sky.sns.mybatis.entity.BlogComment;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.IBlogCategoryService;
import com.sky.sns.mybatis.iService.IBlogCommentService;
import com.sky.sns.mybatis.iService.IBlogService;
import com.sky.sns.mybatis.iService.ITagService;

public class BlogAction extends BasePageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	private IBlogService blogService;
	private ITagService tagService;
	private IBlogCommentService blogCommentService;
	private IBlogCategoryService blogCategoryService;
	private Blog blog;
	private BlogComment blogComment;
	private List<BlogComment> comments;
	private String tags;
	private List<BlogCategory> categories;
    private List<Blog> blogs;
    


	public String post() throws Exception {
		if (isPost()) {
			if (curUser != null) {
				if (!tags.isEmpty()) {
					List<Tag> newTags = new ArrayList<Tag>();
					String[] ts = tags.split(",");
					if (blog != null && blog.getId() == null) {
						for (String s : ts) {
							Tag tag = tagService.getTagById(Integer.parseInt(s
									.trim()));
							if (tag != null) {
								newTags.add(tag);
							}
						}
						blog.setCommentCount(0);
						blog.setPostedByUserId(curUser.getId());
						blog.setPostedDate(new Date());
						blog.setStatus(StatusEnum.NORMAL.getValue());
						blog.setUpdatedDate(new Date());
						blog.setViewCount(0);
						blog.setTags(newTags);
						if(blog.getBlogCategoryId()==0)
							blog.setBlogCategoryId(null);
						blogService.insertBlog(blog);
					} else {
						if (blog.getId() > 0) {
							Blog editBlog = blogService
									.getBlogById(blog.getId());
							if (editBlog != null) {
								if (editBlog.getPostedByUser().getId() == curUser
										.getId()) {
									
									editBlog.setTitle(blog.getTitle());
									editBlog.setContent(blog
											.getContent());
									if(blog.getBlogCategoryId()==0)
										editBlog.setBlogCategoryId(null);
									else
									editBlog.setBlogCategoryId(blog.getBlogCategoryId());
									editBlog.setUpdatedDate(new Date());
									blogService.updateBlogForTags(editBlog,ts);
								}
							}
						}
					}
				}
				return "goToDetail";
			} else {
				return ERROR;
			}

		} else {
			if (blog != null) {
				if (blog.getId() != null) {
					blog = blogService
							.getBlogById(blog.getId());
					if (blog != null) {
						if (curUser != null) {
							if (blog.getPostedByUser().getId() != curUser
									.getId()) {
								blog = null;
							}
						}
					}
				}
			}
			return "post";
		}
	}

	public String detail() throws Exception {
		if (blog.getId() != null) {
			blog = blogService.getBlogById(blog.getId());
			if (blog != null) {
				blog.setViewCount(blog.getViewCount() + 1);
				blogService.updateBlog(blog);
				comments=blogCommentService.getBlogCommentByBlogId(blog.getId());
				blogs=blogService.getOtherBlogs(blog.getId(), blog.getPostedByUserId(), 5);
				return "detail";
			}
		}
		return ERROR;
	}
	
	public String all() throws Exception{
		blogs=blogService.getAllBlogsPage((int)getPageStart(), pageSize);
		recordCount=blogService.countAllBlogs();
		return "all";
	}

	public String newComment() throws Exception {
			if (blog != null) {
				if (blog.getId() != null){
					if (blogComment != null) {
						blogComment.setBlogId(blog.getId());
						blogComment.setPostedByUserId(curUser.getId());
						blogComment.setPostedDate(new Date());
						blogCommentService.insertBlogComment(blogComment);
						return "goToComment";
					}
					else
					{
						return "goToDetail";
					}
				}
		}
		return ERROR;
	}




	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public BlogComment getBlogComment() {
		return blogComment;
	}

	public void setBlogComment(BlogComment blogComment) {
		this.blogComment = blogComment;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}

	public void setBlogCommentService(IBlogCommentService blogCommentService) {
		this.blogCommentService = blogCommentService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public List<BlogCategory> getCategories() {
		if(categories==null)
		{
			if (curUser != null) {
				categories=blogCategoryService.getBlogCategoryByUserId(curUser.getId());
				}
			else
			{
				categories=new ArrayList<BlogCategory>();
			}
		}
		return categories;
	}

	public void setCategories(List<BlogCategory> categories) {
		this.categories = categories;
	}
	public void setBlogCategoryService(IBlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}

	public List<BlogComment> getComments() {
		return comments;
	}

	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
}
