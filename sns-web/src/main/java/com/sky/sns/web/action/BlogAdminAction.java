package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.Blog;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.IBlogService;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.web.pojo.AjaxCode;

public class BlogAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -1591591128655797554L;
    
	private IBlogService blogService;
	private com.sky.sns.mybatis.searchEntity.Blog params;
	private List<Blog> blogs;
	private ITagService tagService;
	private Map<Integer,String> statusMap;
	private String tags;
	private Blog blog;
	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public Map<Integer,String> getStatusMap() {
		return StatusEnum.toMap();
	}

	
	  public com.sky.sns.mybatis.searchEntity.Blog getParams() {
		return params;
	}


	public void setParams(com.sky.sns.mybatis.searchEntity.Blog params) {
		this.params = params;
	}


	public List<Blog> getBlogs() {
		return blogs;
	}


	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}


	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}

	public String searchBlog() throws Exception{
	    	if(params==null)
	    		params =new com.sky.sns.mybatis.searchEntity.Blog();

	    	if(params.getPageIndex()>0)
	    		params.setPageIndex(params.getPageIndex()-1);
	         setBlogs(blogService.searchBlogs(params));
	         setModule("searchBlog");
	 		 return "page";
	    }

	  public String doBlog() throws Exception{
		  setModule("doBlog");
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
							blog.setRecommendCount(0);
							blog.setPostedByUserId(curUser.getId());
							blog.setPostedDate(new Date());
							blog.setStatus(StatusEnum.NORMAL.getValue());
							blog.setUpdatedDate(new Date());
							blog.setViewCount(0);
							blog.setTags(newTags);
							blogService.insertBlog(blog);
						} else {
							if (blog.getId() > 0) {
								Blog editBlog = blogService
										.getBlogById(blog.getId());
								if (editBlog != null) {
									editBlog.setTitle(blog.getTitle());
									editBlog.setContent(blog
												.getContent());
									editBlog.setUpdatedDate(new Date());
										blogService.updateBlogForTags(editBlog,ts);
								}
							}
						}
					}
					jsonData.put("status", AjaxCode.successful);
					return "json";
				} else {
					return ERROR;
				}

			} else {
				if (blog != null) {
					if (blog.getId() != null) {
						blog = blogService.getBlogById(blog.getId());
					}
				}
				return "page";
			}
	  }
	  
	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}


	public Blog getBlog() {
		return blog;
	}


	public void setBlog(Blog blog) {
		this.blog = blog;
	}



	
}
