package com.sky.sns.mybatis.entity;
import java.util.Date;

import com.sky.sns.common.DateUtils;



public class Photo  extends BaseEntity{
	

   /**
	 * 
	 */
	private static final long serialVersionUID = 7734257843400744251L;


private long id;
	

   private Date createdDate;
   
   private Date updatedDate;

   private String title;
	 
   private String description;
   
    private User user;
    
    private long userId;
	 
    private Album album;
    
    private long albumId;
    
    private String url;

	static String defaultUrl="/staticFile/img/defaultAvatar.gif";
	
	private int commentCount;
	
	private long viewCount;
	
	private long recommendCount;

	public long getId() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPhysicalUrlBySize(String size)
	{
		if(url==null||url.isEmpty())
		{
			return defaultUrl;
		}
		else
		{
            if(!size.equalsIgnoreCase("original"))
            {
			String url=this.url.replace(".jpg", "_"+size+".jpg");
			return url;
            }
			return url;
		}
	}
	
	public String getUrlBySize(String size)
	{
		if(url==null||url.isEmpty())
		{
			return defaultUrl;
		}
		else
		{
           return "/photo/"+size+"/"+id+".jpg";
		}
	}
	
	public String getThumbnail()
	{
		return getUrlBySize("thumbnail");
	}
	
	public String getNormal()
	{
		return getUrlBySize("normal");
	}

	public String getOriginal()
	{
		return getUrlBySize("original");
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getCreatedDateF() {
		return DateUtils.getDaysBeforeNow(createdDate);
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(long recommendCount) {
		this.recommendCount = recommendCount;
	}
	
	public String getContentNoHtml100() {
		return getContentNoHtml(100,description);
	}
}
