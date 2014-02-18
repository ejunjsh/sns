package com.sky.sns.enumeration;


import java.util.HashMap;
import java.util.Map;

public enum NoticeTypeEnum {
   QuestionAtNotice("问题@通知",1),AnswerAtNotice("回答@通知",2),
   BlogAtNotice("日志@通知",3),BlogCommentAtNotice("日志评论@通知",4),
   ArticleAtNotice("文章@通知",5),ArticleCommentAtNotice("文章评论@通知",6),
   PostAtNotice("帖子@通知",7),PostCommentAtNotice("帖子评论@通知",8),
   QuestionCommentAtNotice("问题评论@通知",9),AnswerCommentAtNotice("回答评论@通知",10),
   PhotoCommentAtNotice("图片评论@通知",11);
   private NoticeTypeEnum(String name,int value)
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
   
   public static NoticeTypeEnum valueOf(int value) {
       switch (value) {
       case 1:
           return QuestionAtNotice;
       case 2:
    	   return AnswerAtNotice;
       case 3:
           return BlogAtNotice;
       case 4:
    	   return BlogCommentAtNotice;
       case 5:
           return ArticleAtNotice;
       case 6:
    	   return ArticleCommentAtNotice;
       case 7:
           return PostAtNotice;
       case 8:
    	   return PostCommentAtNotice;
       case 9:
           return QuestionCommentAtNotice;
       case 10:
    	   return AnswerCommentAtNotice;
       case 11:
    	   return PhotoCommentAtNotice;
       default:
           return null;
       }
   }
   
   public static Map<Integer,String> toMap() {
	  Map<Integer,String> m=new HashMap<Integer,String>();
	  for(NoticeTypeEnum s :NoticeTypeEnum.values())
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
