package com.sky.sns.web.action;
import java.util.ArrayList;
import java.util.List;

import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.web.pojo.AjaxCode;
import com.sky.sns.web.pojo.TagComplete;



public class TagAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -1590591728655797554L;
    
	private ITagService tagService;
	private com.sky.sns.mybatis.searchEntity.Tag params;
	private List<Tag> tags;
	private Tag tag;
	private String key;

	public com.sky.sns.mybatis.searchEntity.Tag getParams() {
		return params;
	}

	public void setParams(com.sky.sns.mybatis.searchEntity.Tag params) {
		this.params = params;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public String searchTag() throws Exception{
    	if(params==null)
    		params =new com.sky.sns.mybatis.searchEntity.Tag();
    	
    	if(params.getPageIndex()>0)
    		params.setPageIndex(params.getPageIndex()-1);
         tags=tagService.searchTags(params);
         module="searchTag";
 		 return "page";
    }
	
	public String doTag() throws Exception{
		  setModule("doTag");
			if (isPost()) {
				if (curUser != null) {
					if (tag != null && tag.getId()==0){
						  if(tagService.isExisting(tag.getName(),0))
					      {
					          jsonData.put("status", AjaxCode.getFail);
					          jsonData.put("message","已经存在这个标签");
					          return "json";
					     }
						  else
						  {
							  tagService.insertTag(tag);
						  }
						} else {
							if (tag.getId() > 0) {
								 if(!tagService.isExisting(tag.getName(),tag.getId()))
							      {
								Tag editTag = tagService.getTagById(tag.getId());
								if (editTag != null) {
									editTag.setName(tag.getName());
									editTag.setCnSpell(tag.getCnSpell());
									editTag.setDescription(tag.getDescription());
									tagService.updateTag(editTag);
								}
							      }
								 else
								 {
									  jsonData.put("status", AjaxCode.getFail);
							          jsonData.put("message","已经存在这个标签");
							          return "json";
								 }
							}
						}
					jsonData.put("status", AjaxCode.successful);
					return "json";
				} else {
					return ERROR;
				}
				}
	  
else {
				if (tag != null) {
					if (tag.getId()>0) {
						tag = tagService
								.getTagById(tag.getId());
					}
				}
				return "page";
			}
	  }
	  
	
	public String getTagByKey() throws Exception{
		List<Tag> tags=tagService.getTagByName(key);
		 jsonData.put("status",AjaxCode.successful);
		if(tags!=null&&tags.size()>0){
		   List<TagComplete> tc=new ArrayList<TagComplete>();
		   for(int i=0;i<tags.size();i++){
			   TagComplete c=new TagComplete();
			   c.setId(tags.get(i).getId());
			   c.setName(tags.get(i).getName());
			   c.setFollowCount(tags.get(i).getFollowCount());
			   tc.add(c);
		   }
		   jsonData.put("data",tc);
		}
		else
		{
			jsonData.put("data",null);
		}
		return "json";
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	} 
}
