package com.sky.sns.mybatis.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.common.StringConversion;
import com.sky.sns.enumeration.ActivityTypeEnum;
import com.sky.sns.enumeration.NoticeTypeEnum;
import com.sky.sns.mybatis.entity.Activity;
import com.sky.sns.mybatis.entity.Answer;
import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.entity.User;
import com.sky.sns.mybatis.iService.IAnswerService;
import com.sky.sns.mybatis.mapper.ActivityMapper;
import com.sky.sns.mybatis.mapper.AnswerMapper;
import com.sky.sns.mybatis.mapper.NoticeMapper;
import com.sky.sns.mybatis.mapper.QuestionMapper;
import com.sky.sns.mybatis.mapper.UserMapper;
import com.sky.sns.mybatis.entity.Question;

public class AnswerService implements IAnswerService {
	
	 private AnswerMapper answerMapper;
	 private QuestionMapper questionMapper;
	 private ActivityMapper activityMapper;
	 private UserMapper userMapper;
	 private NoticeMapper noticeMapper;
	 
		public void setUserMapper(UserMapper userMapper) {
			this.userMapper = userMapper;
		}

		public void setNoticeMapper(NoticeMapper noticeMapper) {
			this.noticeMapper = noticeMapper;
		}
	
	    @Transactional   
    public void insertAnswer(Answer answer)
	    {
	    	Question q=questionMapper.getQuestionById(answer.getQuestionId());
	    	if(q!=null)
	    	{
		    	answerMapper.insertAnswer(answer);
		    	
		    	Activity activity =new Activity();
   				activity.setActivityType(ActivityTypeEnum.AnswerQuestion.getValue());
   				activity.setDescription(answer.getContentNoHtml100());
   				activity.setRefId(answer.getId());
   				activity.setTitle(q.getTitle());
   				activity.setUserId(answer.getCreatedByUserId());
   				activityMapper.insertActivity(activity);
   				
   				List<String> referers=new ArrayList<String>();
   				StringConversion.generateRefererLinks(answer.getContent(), referers);
   				for(String s :referers)
   				{
   				  User user=userMapper.getUserByNickName(s);
   				  if(user!=null)
   				  {
   					  Notice notice=new Notice();
   					  notice.setNoticeType(NoticeTypeEnum.AnswerAtNotice.getValue());
   					  notice.setRefId(answer.getId());
   					  notice.setUserId(user.getId());
   					  notice.setTitle(q.getTitle());
   					  noticeMapper.insertNotice(notice);
   				  }
   				}
	    	}
	    }
    
	    @Transactional(readOnly=true)
    public Answer getAnswerById(long id)
    {
    	return answerMapper.getAnswerById(id);
    }
    
	    @Transactional
    public void updateAnswer(Answer answer)
    {
    	answerMapper.updateAnswer(answer);
    }
    


    @Transactional(readOnly=true)
	public List<Answer> getAnswersByQuestionId(long id) {
		
		return answerMapper.getAnswersByQuestionId(id);
	}

    public void setAnswerMapper(AnswerMapper answerMapper) {
		this.answerMapper = answerMapper;
	}
    
	public void setQuestionMapper(QuestionMapper questionMapper) {
		this.questionMapper = questionMapper;
	}

	@Transactional
	public void addVote(Answer a, long uid,boolean isUp) {
         if(isUp)
         {
        	 answerMapper.addVote(a.getId(), uid,1);
        	 Question q=questionMapper.getQuestionById(a.getQuestionId());
        	 Activity activity =new Activity();
				activity.setActivityType(ActivityTypeEnum.UpholdAnswer.getValue());
				activity.setDescription(a.getCreatedByUser().getNickName()+" 回答："+a.getContentNoHtml100());
				activity.setRefId(a.getId());
				activity.setTitle(q.getTitle());
				activity.setUserId(uid);
				activityMapper.insertActivity(activity);
     	 }
         else
         {
        	 answerMapper.addVote(a.getId(), uid,2);
         }
	}

	@Transactional
	public void addUseless(Answer a, long uid) {
		a.setUselessCount(a.getUselessCount()+1);
		 answerMapper.updateAnswer(a);
         answerMapper.addUseless(a.getId(), uid);
	}

	@Transactional(readOnly=true)
	public boolean isVoted(long aid, long uid) {
		return answerMapper.isVoted(aid, uid)>0;
	}

	@Transactional(readOnly=true)
	public boolean isUseless(long aid, long uid) {
		return answerMapper.isUseless(aid, uid)>0;
	}

	@Transactional
	public void deleteAnswer(Answer answer) {
		Question question=questionMapper.getQuestionById(answer.getQuestionId());
		if(question!=null)
		{
			answerMapper.deleteUseless(answer.getId());
			answerMapper.deleteVote(answer.getId());
			answerMapper.deleteAnswer(answer.getId());
		}
	}

	@Transactional(readOnly=true)
	public List<Answer> getAnswersByUserId(long id, int pageStart, int pageSize) {
		return answerMapper.getAnswersByUserId(id, pageStart, pageSize);
	}

	@Transactional(readOnly=true)
	public long countAnswersByUserId(long id) {
		return answerMapper.countAnswersByUserId(id);
	}

	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}
}
