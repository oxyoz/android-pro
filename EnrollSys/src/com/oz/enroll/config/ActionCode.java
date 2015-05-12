package com.oz.enroll.config;



public class ActionCode {

   public static class StudentInfo{
	   
	   public final static int SELECT_ALL_STUDENT_INFO = 0x11110;
	   	   
	   public final static int ACQUIESCE = 0x11111;
	   
	   public final static int PROMPT = 0x11112;
	   
   }
	
	
   public static class AddStudentInfo{
	   
	   public final static int PREPARE = 0x11110;
   	   
	   public final static int ACQUIESCE = 0x11111;
	   
	   public final static int PROMPT = 0x11112;
	      
   }

   public static class InAdd{
	   
	   public final static int PREPARE = 0x11110;
   	   
	   public final static int ACQUIESCE = 0x11111;
	   
	   public final static int PROMPT = 0x11112;
	   
	   public final static int MAJOR_SELECT = 0x11113;
	   
	   public final static int VOLUNTARILY_SELECT = 0x11114;
	   	
	   public final static int CHECK_SCHOOL_RESULT = 0x11118;
	   
	   public final static int CHECK_EXAMINEES_RESULT = 0x11119;
	   
	   public final static int SET_PROPERTY = 0x11120;
	   
	   public final static int SUBMIT_STUDENT_INFO = 0x11121;
   }
   
   public static class OutAdd{
	   
	   public final static int PREPARE = 0x11110;
   	   
	   public final static int ACQUIESCE = 0x11111;
	   
	   public final static int PROMPT = 0x11112;
	   
	   public final static int MAJOR_SELECT = 0x11113;
	   
	   public final static int VOLUNTARILY_SELECT = 0x11114;
	   
	   public final static int PROVINCE_SELECT = 0x11122;
	   
	   public final static int NETHERLANDS_SELECT = 0x11116;
	   
	   public final static int COUNTY_SELECT = 0x11117;
	   	
	   public final static int CHECK_SCHOOL_RESULT = 0x11118;
	   
	   public final static int CHECK_EXAMINEES_RESULT = 0x11119;
	   
	   public final static int SET_PROPERTY = 0x11120;
	   
	   public final static int SUBMIT_STUDENT_INFO = 0x11121;
	   
   }
   
   
   public static class InAddDef{
	   
	   public final static int PREPARE = 0x11110;
   	   
	   public final static int ACQUIESCE = 0x11111;
	   
	   public final static int PROMPT = 0x11112;
	   
	   public final static int SCHOOL_SELECT = 0x11115;
	   
   }
   
   public static class InAddOther{
	   
	   public final static int PREPARE = 0x11110;
   	   
	   public final static int ACQUIESCE = 0x11111;
	   
	   public final static int PROMPT = 0x11112;
	   
	   public final static int SCHOOL_SELECT = 0x11115;
	   
	   public final static int CHECK_SCHOOL_RESULT = 0x11118;
	   
	   public final static int PROVINCE_SELECT = 0x11114;
	   
	   public final static int NETHERLANDS_SELECT = 0x11116;
	   
	   public final static int COUNTY_SELECT = 0x11117;
	   
	   
   }
   
   
}


enum ActionCd{

		//设置控件数据
		SET_WEIGET_DATA,
		//默认
		ACQUIESCE,
		//查找所有学生信息
		SELECT_ALL_STUDENT_INFO,
		//专业选择
		MAJOR_SELECT,
		//志愿选择
		VOLUNTARILY_SELECT,
		//学校选择
		SCHOOL_SELECT,
		//省选择
		PROVINCE_SELECT,
		//地区选择
		NETHERLANDS_SELECT,
		//城市选择
		COUNTY_SELECT,
		//检查学校
		CHECK_SCHOOL_RESULT,
		//检查考生号
		CHECK_EXAMINEES_RESULT,
		//生源性质设置
		SET_PROPERTY
}



