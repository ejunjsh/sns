package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.Group;
import com.sky.sns.mybatis.entity.GroupCategory;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IGroupService;
import com.sky.sns.mybatis.mapper.GroupCategoryMapper;
import com.sky.sns.mybatis.mapper.GroupMapper;

public class GroupService implements IGroupService {
	
	 private GroupMapper groupMapper;


	private GroupCategoryMapper groupCategoryMapper;
	
	 public void setGroupCategoryMapper(GroupCategoryMapper groupCategoryMapper) {
		this.groupCategoryMapper = groupCategoryMapper;
	}

	   @Transactional   
	public void insertGroup(Group group) {
		groupMapper.insertGroup(group);
    	List<GroupCategory> categorys=group.getCategorys();
    	if(categorys!=null&&categorys.size()>0)
    	{
    		for(GroupCategory c:categorys)
    		{
    			groupMapper.addCategory(group.getId(), c.getId());
    		}
    	}
	}

	 
	@Transactional(readOnly=true)   
	public Group getGroupById(long id) {
		
		return groupMapper.getGroupById(id);
	}

	   @Transactional
	public void updateGroup(Group group) {
        groupMapper.updateGroup(group);
	}
	   
	   @Transactional
		public void updateGroupForCategorys(Group group, String[] cs) {
		   if(cs!=null)
		   {
			for (String c : cs) {
				boolean isNew = true;
				for (GroupCategory gc : group.getCategorys()) {
					if (Integer.parseInt(c.trim()) == gc
							.getId()) {
						isNew = false;
						break;
					}
				}
				if (isNew) {
					GroupCategory category = groupCategoryMapper.getGroupCategoryById(Integer.parseInt(c.trim()));
					if (category != null) {
						groupMapper.addCategory(group.getId(), category.getId());
					}
				}
			}
		   
			for (int i = 0; i <group.getCategorys()
					.size(); i++) {
				GroupCategory category = group.getCategorys().get(i);
				boolean isDelete = true;
				for (String c : cs) {
					if (Integer.parseInt(c.trim()) ==category
							.getId()) {
						isDelete = false;
						break;
					}
				}
				if (isDelete) {
					groupMapper.deleteCategory(group.getId(), category.getId());
				}
			}
		   }
		   else
		   {
			   for (int i = 0; i <group.getCategorys()
						.size(); i++) {
				   GroupCategory category = group.getCategorys().get(i);
				   groupMapper.deleteCategory(group.getId(), category.getId());
			   }
		   }
			groupMapper.updateGroup(group);
			
		}
	   
	    
	    @Transactional(readOnly=true)
	   	public List<Group> searchGroups(com.sky.sns.mybatis.searchEntity.Group group) {
	   		long total=this.groupMapper.countGroups(group);
	   		group.setTotal(total);
	   		return this.groupMapper.searchGroups(group);
	   	}
	
	   public void setGroupMapper(GroupMapper groupMapper) {
			this.groupMapper = groupMapper;
		}
	   
	   @Transactional(readOnly=true)
		public Boolean isExist(String name,long id) {
			 return groupMapper.getGroupByName(name,id)>0;
		}

	   @Transactional(readOnly=true)
	public List<Group> getGroupByCategory(long categoryId, long userId,
			int pageSize, int pageStart) {
		
		return groupMapper.getGroupByCategory(categoryId, userId, pageSize, pageStart);
	}

	   @Transactional(readOnly=true)
	public long countGroupByCategory(long categoryId) {
		return groupMapper.countGroupByCategory(categoryId);
	}

	   @Transactional
	public boolean joinGroup(long groupId, long userId) {
		
		 if(groupMapper.isJoinGroup(groupId, userId)>0)
			 return false;
		 else
		 {
		groupMapper.joinGroup(groupId, userId);
		return true;
		 }
	}

	   @Transactional(readOnly=true)
	public List<Group> getHottestGroup(int pageSize, int pageStart) {
		return groupMapper.getHottestGroup(pageSize, pageStart);
	}

	   @Transactional(readOnly=true)
	public List<Group> getNewGroup(int pageSize, int pageStart) {
		return groupMapper.getNewGroup(pageSize, pageStart);
	}

	   @Transactional(readOnly=true)
	public Group getGroupDetailById(long id, long userId) {
		return groupMapper.getGroupDetailById(id,userId);
	}

	   @Transactional(readOnly=true)
	public List<Group> getGroupByKey(long userId, String key) {
		return groupMapper.getGroupByKey(userId, key);
	}

	   @Transactional
	public void quitGroup(long groupId, long userId) {
		groupMapper.quitGroup(groupId, userId);
	}

	   @Transactional(readOnly=true)
	public List<User> getGroupMembers(String name, long groupId, int pageSize,
			int pageStart) {
		return groupMapper.getGroupMembers(name, groupId, pageSize, pageStart);
	}

	   @Transactional(readOnly=true)
	public long countGroupMembers(String name, long groupId) {
		return groupMapper.countGroupMembers(name, groupId);
	}

	   @Transactional(readOnly=true) 
	public List<Group> getRelatedGroup(long id, int pageSize, int pageStart) {
		return groupMapper.getRelatedGroup(id, pageSize, pageStart);
	}

	@Transactional(readOnly=true) 
	public List<User> getActiveMember(long id,int pageSize, int pageStart) {
		return groupMapper.getActiveMember(id,pageSize, pageStart);
	}

	@Transactional(readOnly=true) 
	public List<Group> getMyJoinedGroups(long id) {
		return groupMapper.getMyJoinedGroups(id);
	}

	@Transactional(readOnly=true) 
	public List<Group> getAllGroups() {
		return groupMapper.getAllGroups();
	}



}
