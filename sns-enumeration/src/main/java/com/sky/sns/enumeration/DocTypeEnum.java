package com.sky.sns.enumeration;


import java.util.HashMap;
import java.util.Map;

public enum DocTypeEnum {
	     Blog("日志",4),Post("帖子",3),Question("问答",2),Article("文章",1),User("用户",5),Group("小组",6);
	   
	   private DocTypeEnum(String name,int value)
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
	   
	   public static DocTypeEnum valueOf(int value) {
	       switch (value) {
	       case 4:
	           return Blog;
	       case 3:
	           return Post;
	       case 2:
	    	   return Question;
	       case 1:
	    	   return Article;
	       case 5:
	    	   return User;
	       case 6:
	    	   return Group;
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
