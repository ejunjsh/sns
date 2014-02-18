package com.sky.sns.enumeration;


import java.util.HashMap;
import java.util.Map;

public enum ScopeTypeEnum {
   ALL("全部",1),OneToOne("一对一",2);
   
   private ScopeTypeEnum(String name,int value)
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
   
   public static ScopeTypeEnum valueOf(int value) {
       switch (value) {
       case 1:
           return ALL;
       case 2:
    	   return OneToOne;
       default:
           return null;
       }
   }
   
   public static Map<Integer,String> toMap() {
	  Map<Integer,String> m=new HashMap<Integer,String>();
	  for(ScopeTypeEnum s :ScopeTypeEnum.values())
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
