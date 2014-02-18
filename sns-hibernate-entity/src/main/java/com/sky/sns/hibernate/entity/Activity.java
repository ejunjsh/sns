package com.sky.sns.hibernate.entity;
import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Activity")
public class Activity {
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
   private long id;
	
	 @Column(name = "createdDate")
   private Date createdDate;

	@Column(name = "title")
   private String title;
	 
	 
	 @Column(name = "description",length=java.lang.Integer.MAX_VALUE)
   private String description;
   
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
    private User user;

	 @Column(name = "activityType")
    private int activityType;
	 
	 @Column(name = "refId")
	private long refId;
	 
	 @Column(name = "pic")
	 private String pic;

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

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	
	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
