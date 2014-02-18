package com.sky.sns.mybatis.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.Question;
import com.sky.sns.mybatis.entity.Tag;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IQuestionService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.QuestionMapper;
import com.sky.sns.mybatis.mapper.TagMapper;
import com.sky.sns.mybatis.mapper.UserMapper;

public class QuestionService implements IQuestionService {
	
	 private QuestionMapper questionMapper;
	 private ActivityMapper activityMapper;
	 private TagMapper tagMapper;
	 private UserMapper userMapper;
	 private NoticeMapper noticeMapper;
	 
		public void setUserMapper(UserMapper userMapper) {
			this.userMapper = userMapper;
		}

		public void setNoticeMapper(NoticeMapper noticeMapper) {
			this.noticeMapper = noticeMapper;
		}
	 
	    public void setQuestionMapper(QuestionMapper questionMapper) {
			this.questionMapper = questionMapper;
		}
		public void setTagMapper(TagMapper tagMapper) {
			this.tagMapper = tagMapper;
		}

	
	    @Transactional   
    public void insertQuestion(Question question)
	    {
	    	questionMapper.insertQuestion(question);
	    	List<Tag> tags=question.getTags();
	    	if(tags!=null&&tags.size()>0)
	    	{
	    		for(Tag t:tags)
	    		{
	    			questionMapper.addTags(question.getId(), t.getId());
	    		}
	    	}
	    	
	    	Activity activity =new Activity();
			activity.setActivityType(ActivityTypeEnum.NewQuestion.getValue());
			activity.setDescription(question.getContentNoHtml50());
			activity.setRefId(question.getId());
			activity.setTitle(question.getTitle());
			activity.setUserId(question.getPostedByUserId());
			activityMapper.insertActivity(activity);
			
			List<String> referers=new ArrayList<String>();
			StringConversion.generateRefererLinks(question.getContent(), referers);
			for(String s :referers)
			{
			  User user=userMapper.getUserByNickName(s);
			  if(user!=null)
			  {
				  Notice notice=new Notice();
				  notice.setNoticeType(NoticeTypeEnum.QuestionAtNotice.getValue());
				  notice.setRefId(question.getId());
				  notice.setUserId(user.getId());
				  notice.setTitle(question.getTitle());
				  noticeMapper.insertNotice(notice);
			  }
			}
	    }
    
	    @Transactional(readOnly=true)
    public Question getQuestionById(long id)
    {
    	return questionMapper.getQuestionById(id);
    }
    
	    @Transactional
    public void updateQuestion(Question question)
    {
	     questionMapper.updateQuestion(question);
    }
    


    @Transactional
	public void updateQuestionForTags(Question question, String[] ts) {
		for (String s : ts) {
			boolean isNew = true;
			for (Tag t : question.getTags()) {
				if (Integer.parseInt(s.trim()) == t
						.getId()) {
					isNew = false;
					break;
				}
			}
			if (isNew) {
				Tag tag = tagMapper
						.getTagById(Integer
								.parseInt(s.trim()));
				if (tag != null) {
					questionMapper.addTags(question.getId(), tag.getId());
				}
			}
		}
		for (int i = 0; i < question.getTags()
				.size(); i++) {
			Tag t = question.getTags().get(i);
			boolean isDelete = true;
			for (String s : ts) {
				if (Integer.parseInt(s.trim()) == t
						.getId()) {
					isDelete = false;
					break;
				}
			}
			if (isDelete) {
				questionMapper.deleteTags(question.getId(), t.getId());
			}
		}
		questionMapper.updateQuestion(question);
		
		List<String> referers=new ArrayList<String>();
		StringConversion.generateRefererLinks(question.getContent(), referers);
		for(String s :referers)
		{
		  User user=userMapper.getUserByNickName(s);
		  if(user!=null)
		  {
			  Notice notice=new Notice();
			  notice.setNoticeType(NoticeTypeEnum.QuestionAtNotice.getValue());
			  notice.setRefId(question.getId());
			  notice.setUserId(user.getId());
			  notice.setTitle(question.getTitle());
			  noticeMapper.insertNotice(notice);
		  }
		}
	}

    @Transactional(readOnly=true)
   	public List<Question> searchQuestions(com.sky.sns.mybatis.searchEntity.Question question) {
   		long total=this.questionMapper.countQuestions(question);
   		question.setTotal(total);
   		return this.questionMapper.searchQuestions(question);
   	}
    
    @Transactional(readOnly=true)
	public List<Question> getPendingQuestion(int pageStart, int pageSize) {
		return questionMapper.getPendingQuestion(pageStart, pageSize);
	}
    @Transactional  
	public boolean follow(long qid, long uid) {
    	
    	if(questionMapper.isFollow(qid, uid)>0)
    		return false;
    	else
    	{
			questionMapper.follow(qid, uid);
			Question question = questionMapper.getQuestionById(qid);
			if(question!=null)
			{
				Activity activity =new Activity();
				activity.setActivityType(ActivityTypeEnum.FollowQuestion.getValue());
				activity.setDescription(question.getContentNoHtml50());
				activity.setRefId(qid);
				activity.setTitle(question.getTitle());
				activity.setUserId(uid);
				activityMapper.insertActivity(activity);
			}
			return true;
    	}
	}
	 @Transactional  
	public void unfollow(long qid, long uid) {
		questionMapper.unfollow(qid, uid);
	}
	  @Transactional(readOnly=true)
	public Question getQuestionDetailById(long id, long userId) {
		return questionMapper.getQuestionDetailById(id, userId);
	}
	  @Transactional(readOnly=true)
	public long countPendingQuestion() {
		return questionMapper.countPendingQuestion();
	}
	  @Transactional(readOnly=true)
	public List<Question> getNewestQuestion(int pageStart, int pageSize) {
		return questionMapper.getNewestQuestion(pageStart, pageSize);
	}
	  @Transactional(readOnly=true)
	public long countAllQuestion() {
		return questionMapper.countAllQuestion();
	}
	  @Transactional(readOnly=true)
	public List<Question> getHotQuestion(int pageStart, int pageSize) {
	   return questionMapper.getHotQuestion(pageStart, pageSize);
	}
	  @Transactional(readOnly=true)
	public List<Question> getQuestionByUserId(long id, int pageStart,
			int pageSize) {
		return questionMapper.getQuestionByUserId(id, pageStart, pageSize);
	}

	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}

	@Transactional(readOnly=true)
	public List<Question> getAllQuestions() {
		return questionMapper.getAllQuestions();
	}

	@Transactional(readOnly=true)
	public List<Question> getQuestionsByTagId(long id, int pageStart,
			int pageSize) {
		return questionMapper.getQuestionsByTagId(id, pageStart, pageSize);
	}

	@Transactional(readOnly=true)
	public long countQuestionsByTagId(long id) {
		return questionMapper.countQuestionsByTagId(id);
	}



}
