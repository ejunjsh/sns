package com.sky.sns.mybatis.entity;
import java.util.Date;
import java.util.List;

import com.sky.sns.common.DateUtils;
import com.sky.sns.enumeration.*;

public class Group  extends BaseEntity{
	

   /**
	 * 
	 */
	private static final long serialVersionUID = -7195646299575255576L;


private Long id;
	

   private Date createdDate;
	 
   private Date updatedDate;

   private String name;
	 

   private String description;
   

	private String reason;

     private User createdByUser;
	 
     private long createdByUserId;

	  private int status;
	  
		 private int isOpenContent;
		 
		 private int isNeedValidate;
		 
		 private int postCount;
		 
		 private int joinedUserCount;
		 
		 private int isJoined;
		 
		    private Photo cover;
		    
		    private Long photoId;
        
		 private List<GroupCategory> categorys;
		public int getIsOpenContent() {
			return isOpenContent;
		}
		
		public String getIsOpenContentName() {
			if(isOpenContent==1)
			    return "是";
			else
				return "否";
		}

		public void setIsOpenContent(int isOpenContent) {
			this.isOpenContent = isOpenContent;
		}

		public int getIsNeedValidate() {
			return isNeedValidate;
		}
		
		public String getIsNeedValidateName() {
			if(isNeedValidate==1)
			    return "是";
			else
				return "否";
		}

	public void setIsNeedValidate(int isNeedValidate) {
			this.isNeedValidate = isNeedValidate;
		}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	
	public String getCreatedDateF() {
		return DateUtils.getDaysBeforeNow(createdDate);
	}
	
	public String getUpdatedDateF() {
		return DateUtils.getDaysBeforeNow(updatedDate);
	}
	
	public String getStatusName()
    {
		 if(status>0)
  	      return StatusEnum.valueOf(status).toString();
  	 else
  		 return null;
    }

	public List<GroupCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<GroupCategory> categorys) {
		this.categorys = categorys;
	}
	
	public String getCategoryString() {
		if(categorys!=null&&categorys.size()>0)
		{
			String s="";
			for(GroupCategory c: categorys)
			{
				s+=c.getName()+",";
			}
			if(!s.isEmpty())
			{
				s.substring(0, s.length()-1);
				return s;
			}
		}
		return null;
	}

	public int getJoinedUserCount() {
		return joinedUserCount;
	}

	public void setJoinedUserCount(int joinedUserCount) {
		this.joinedUserCount = joinedUserCount;
	}

	public int getIsJoined() {
		return isJoined;
	}

	public void setIsJoined(int isJoined) {
		this.isJoined = isJoined;
	}
	
	
	public String getDescription30() {
		return getContentNoHtml(30,description);
	}
	
	public String getDescription10() {
		return getContentNoHtml(10,description);
	}
	
	public String getDescriptionNoHtml() {
		return getContentNoHtml(-1,description);
	}
	
	public DocumentEntity convertToDoc()
	{
		if(this!=null)
		{
			DocumentEntity doc=new DocumentEntity();
			doc.setContent(this.getDescriptionNoHtml());
			doc.setDate(this.getUpdatedDate());
			doc.setFromName("");
			doc.setFromValue(0);
			doc.setId("G"+this.getId());
			doc.setRemark(this.getCover48());
			doc.setTagString(this.getCategoryString());
			doc.setTitle(this.getName());
			doc.setType(DocTypeEnum.Group.getValue());
			return doc;
		}
		return null;
	}

	public Photo getCover() {
		return cover;
	}

	public void setCover(Photo cover) {
		this.cover = cover;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	
	public String getCover160() {
		if(cover!=null)
        return cover.getUrlBySize("160");
		else
        return Photo.defaultUrl;
	}
	
	
	public String getCover48() {
		if(cover!=null)
	        return cover.getUrlBySize("48");
			else
	        return Photo.defaultUrl;
	}
	
	public String getCover24() {
		if(cover!=null)
	        return cover.getUrlBySize("24");
			else
	        return Photo.defaultUrl;
	}
}
