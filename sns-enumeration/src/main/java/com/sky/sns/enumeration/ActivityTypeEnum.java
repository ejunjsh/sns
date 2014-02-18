package com.sky.sns.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum ActivityTypeEnum {
   FollowQuestion("关注问题",1),UpholdAnswer("支持答案",2),RecommendActicle("推荐文章",3),RecommendBlog("推荐日志",4),AnswerQuestion("回答问题",5),
   NewQuestion("发问题",6),RecommendPost("推荐帖子",7),NewPost("发帖子",8),NewBlog("发日志",9),NewArticle("发文章",10),
   CommentPost("评论帖子",11),CommentArticle("评论文章",12),CommentBlog("评论日志",13),CommentPhoto("评论图片",14),RecommendPhoto("推荐图片",15);
   
   private ActivityTypeEnum(String name,int value)
   {
	   this.value=value;
	   this.name=name;
   }
   private String name;
   private int value;
   
   public int getValue()
   {
	   return value;
   }
   
   public static ActivityTypeEnum valueOf(int value) {
       switch (value) {
       case 1:
           return FollowQuestion;
       case 2:
           return UpholdAnswer;
       case 3:
           return RecommendActicle;
       case 4:
           return RecommendBlog;
       case 5:
           return AnswerQuestion;
       case 6:
           return NewQuestion;
       case 7:
           return RecommendPost;
       case 8:
           return NewPost;
       case 9:
           return NewBlog;
       case 10:
           return NewArticle;
       case 11:
           return CommentPost;
       case 12:
           return CommentArticle;
       case 13:
           return CommentBlog;
       case 14:
           return CommentPhoto; 
       case 15:
    	   return RecommendPhoto;
       default:
           return null;
       }
   }
   
   public static Map<Integer,String> toMap() {
	  Map<Integer,String> m=new HashMap<Integer,String>();
	  for(ActivityTypeEnum s :ActivityTypeEnum.values())
	  {
		 m.put(s.getValue(),s.toString());
	  }
	  return m;
   }
   
   public String toString()
   {
	return this.name;
   }

}
