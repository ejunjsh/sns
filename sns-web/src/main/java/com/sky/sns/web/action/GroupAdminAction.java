package com.sky.sns.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sky.sns.enumeration.StatusEnum;
import com.sky.sns.mybatis.entity.Album;
import com.sky.sns.mybatis.entity.Group;
import com.sky.sns.mybatis.entity.GroupCategory;
import com.sky.sns.mybatis.entity.GroupPost;
import com.sky.sns.mybatis.entity.Photo;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.iService.IAlbumService;
import com.sky.sns.mybatis.iService.IGroupCategoryService;
import com.sky.sns.mybatis.iService.IGroupPostService;
import com.sky.sns.mybatis.iService.IGroupService;
import com.sky.sns.mybatis.iService.IPhotoService;
import com.sky.sns.mybatis.iService.ITagService;
import com.sky.sns.web.pojo.AjaxCode;
import com.sky.sns.web.pojo.AutoComplete;
import com.sky.sns.web.utility.ImageUtils;


@Service
@Scope("prototype")
public class GroupAdminAction extends BaseAdminAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7573705239919207845L;
	
	@Autowired
	private IGroupService groupService;
	
	
	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private IPhotoService photoService;
	
	
	private com.sky.sns.mybatis.searchEntity.Group params;
	public Group getGroup() {
		return group;
	}



	public void setGroup(Group group) {
		this.group = group;
	}



	private List<Group> groups;
	private Group group;
	private GroupPost groupPost;
	private Map<Integer,String> statusMap;
	private int searchType;
	private List<GroupPost> groupPosts;
	private List<GroupCategory> groupCategorys;
	private GroupCategory groupCategory;
    private String categorys;
    
    private String tmpUrl;
    
    @Autowired
	private IGroupPostService groupPostService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private IGroupCategoryService groupCategoryService;
	
	public Map<Integer,String> getStatusMap() {
		return StatusEnum.toMap();
	}
	
	private String tags;

	private String key;



	public String searchGroup() throws Exception{
	    	if(params==null)
	    		params =new com.sky.sns.mybatis.searchEntity.Group();

	    	if(params.getPageIndex()>0)
	    		params.setPageIndex(params.getPageIndex()-1);
	         setGroups(groupService.searchGroups(params));
	         setModule("searchGroup");
	 		 return "page";
	    }
	
	public String doGroup() throws Exception{
		  setModule("doGroup");
			if (isPost()) {
				if (curUser != null) {
					if (group != null &&  group.getId() == null){
						  if(groupService.isExist(group.getName(),0))
					      {
					          jsonData.put("status", AjaxCode.getFail);
					          jsonData.put("message","已存在小组名");
					          return "json";
					     }
						  else
						  {
								if (!categorys.isEmpty()) {
									List<GroupCategory> newCategorys = new ArrayList<GroupCategory>();
									String[] ts = categorys.split(",");
									
										for (String s : ts) {
											GroupCategory g = groupCategoryService.getGroupCategoryById(Integer.parseInt(s
													.trim()));
											if (g != null) {
												newCategorys.add(g);
											}
										}
										
									group.setCategorys(newCategorys);
									}
							group.setStatus(StatusEnum.NORMAL.getValue());
							group.setCreatedByUserId(curUser.getId());
							groupService.insertGroup(group);
						  }
						} else {
							if (group.getId() > 0) {
								 if(!groupService.isExist(group.getName(),group.getId()))
							      {
								Group editGroup = groupService
										.getGroupById(group.getId());
								if (editGroup != null) {
									
									editGroup.setName(group.getName());
									editGroup.setDescription(group.getDescription());
									editGroup.setReason(group.getReason());
									if(tmpUrl!=null&&!tmpUrl.isEmpty())
									{
						                String url=ImageUtils.SaveToAvatar(tmpUrl, "article",curUser.getIsWaterMark()==1);
						    			
						                List<Album> albums=albumService.getAlbumsByUserId(curUser.getId(),0, Integer.MAX_VALUE);
										
										Album album=null;
										for(Album a : albums)
										{
											if(a.getIsFixed()==1&&a.getTitle().equalsIgnoreCase("默认相册"))
											{
												album=a;
												break;
											}
										}
										if(album==null)
										{
											album=new Album();
											album.setDescription("默认相册");
											album.setIsFixed(1);
											album.setTitle("默认相册");
											album.setUserId(curUser.getId());
											albumService.insertAlbum(album);
										}
										
										Photo photo=new Photo();
										photo.setAlbumId(album.getId());
										photo.setDescription("小组封面");
										photo.setTitle("小组封面");
										photo.setUrl(url);
										photo.setUserId(curUser.getId());
										photoService.insertPhoto(photo);
										editGroup.setPhotoId(photo.getId());
									}
									editGroup.setIsNeedValidate(group.getIsNeedValidate());
									editGroup.setIsOpenContent(group.getIsOpenContent());
									if (categorys!=null&&!categorys.isEmpty()) {
										String[] cs = categorys.split(",");
										groupService.updateGroupForCategorys(editGroup,cs);
									}
									else
									{
										groupService.updateGroupForCategorys(editGroup,null);
									}
								}
							      }
								 else
								 {
									  jsonData.put("status", AjaxCode.getFail);
							          jsonData.put("message","已存在小组名");
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
				if (group != null) {
					if (group.getId() != null) {
						group = groupService
								.getGroupById(group.getId());
					}
				}
				return "page";
			}
	  }

    
	public String searchGroupPost() throws Exception{
    	if(params==null)
    		params =new com.sky.sns.mybatis.searchEntity.Group();

    	if(params.getPageIndex()>0)
    		params.setPageIndex(params.getPageIndex()-1);
         setGroupPosts(groupPostService.searchGroupPosts(params));
         setModule("searchGroupPost");
 		 return "page";
    }

  public String doGroupPost() throws Exception{
	  setModule("doGroupPost");
		if (isPost()) {
			if (curUser != null) {
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
						groupPostService.insertGroupPost(groupPost);
					} else {
						if (groupPost.getId() > 0) {
							GroupPost editPost = groupPostService
									.getGroupPostById(groupPost.getId());
							if (editPost != null) {
								editPost.setTitle(groupPost.getTitle());
								editPost.setContent(groupPost
											.getContent());
								editPost.setUpdatedDate(new Date());
								editPost.setGroupId(groupPost.getGroupId());
								groupPostService.updateGroupPostForTags(editPost,ts);
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
			if (groupPost != null) {
				if (groupPost.getId() != null) {
					groupPost = groupPostService.getGroupPostById(groupPost.getId());
				}
			}
			return "page";
		}
  }
  
  public String searchGroupCategory() throws Exception{
  	if(params==null)
  		params =new com.sky.sns.mybatis.searchEntity.Group();
  	
  	if(params.getPageIndex()>0)
  		params.setPageIndex(params.getPageIndex()-1);
       groupCategorys=groupCategoryService.searchGroupCategorys(params);
       module="searchGroupCategory";
		 return "page";
  }
	
	public String doGroupCategory() throws Exception{
		  setModule("doGroupCategory");
			if (isPost()) {
				if (curUser != null) {
					if (groupCategory != null && groupCategory.getId()==0){
						  if(groupCategoryService.isExisting(groupCategory.getName(),0))
					      {
					          jsonData.put("status", AjaxCode.getFail);
					          jsonData.put("message","�Ѿ������������");
					          return "json";
					     }
						  else
						  {
							  groupCategoryService.insertGroupCategory(groupCategory);
						  }
						} else {
							if (groupCategory.getId() > 0) {
								 if(!groupCategoryService.isExisting(groupCategory.getName(),groupCategory.getId()))
							      {
									 GroupCategory editGroupCategory = groupCategoryService.getGroupCategoryById(groupCategory.getId());
								if (editGroupCategory != null) {
									editGroupCategory.setName(groupCategory.getName());
									editGroupCategory.setDescription(groupCategory.getDescription());
									groupCategoryService.updateGroupCategory(editGroupCategory);
								}
							      }
								 else
								 {
									  jsonData.put("status", AjaxCode.getFail);
							          jsonData.put("message","�Ѿ������������");
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
				if (groupCategory != null) {
					if (groupCategory.getId()>0) {
						groupCategory = groupCategoryService
								.getGroupCategoryById(groupCategory.getId());
					}
				}
				return "page";
			}
	  }
	
	
	public String getGroupCategoryByKey() throws Exception{
		List<GroupCategory> cs=groupCategoryService.getGroupCategoryByName(key);
		 jsonData.put("status",AjaxCode.successful);
		if(cs!=null&&cs.size()>0){
		   List<AutoComplete> tc=new ArrayList<AutoComplete>();
		   for(int i=0;i<cs.size();i++){
			   AutoComplete c=new AutoComplete();
			   c.setId(cs.get(i).getId());
			   c.setName(cs.get(i).getName());
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

	public com.sky.sns.mybatis.searchEntity.Group getParams() {
		return params;
	}



	public void setParams(com.sky.sns.mybatis.searchEntity.Group params) {
		this.params = params;
	}


	public int getSearchType() {
		return searchType;
	}



	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}







	public List<Group> getGroups() {
		return groups;
	}



	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}



	public List<GroupPost> getGroupPosts() {
		return groupPosts;
	}



	public void setGroupPosts(List<GroupPost> groupPosts) {
		this.groupPosts = groupPosts;
	}



	public String getTags() {
		return tags;
	}



	public void setTags(String tags) {
		this.tags = tags;
	}



	public GroupPost getGroupPost() {
		return groupPost;
	}



	public void setGroupPost(GroupPost groupPost) {
		this.groupPost = groupPost;
	}



	public List<GroupCategory> getGroupCategorys() {
		return groupCategorys;
	}



	public void setGroupCategorys(List<GroupCategory> groupCategorys) {
		this.groupCategorys = groupCategorys;
	}



	public GroupCategory getGroupCategory() {
		return groupCategory;
	}



	public void setGroupCategory(GroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}



	public String getCategorys() {
		return categorys;
	}



	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getTmpUrl() {
		return tmpUrl;
	}



	public void setTmpUrl(String tmpUrl) {
		this.tmpUrl = tmpUrl;
	}

}
