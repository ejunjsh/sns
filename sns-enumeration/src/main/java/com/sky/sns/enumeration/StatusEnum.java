package com.sky.sns.enumeration;


import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {
   NORMAL("正常",1),BANNED("禁用",2),LOCKED("锁定",3);
   
   private StatusEnum(String name,int value)
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
   
   public static StatusEnum valueOf(int value) {
       switch (value) {
       case 1:
           return NORMAL;
       case 2:
           return BANNED;
       case 3:
           return LOCKED;
       default:
           return null;
       }
   }
   
   public static Map<Integer,String> toMap() {
	  Map<Integer,String> m=new HashMap<Integer,String>();
	  for(StatusEnum s :StatusEnum.values())
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
