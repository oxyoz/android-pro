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

		//���ÿؼ�����
		SET_WEIGET_DATA,
		//Ĭ��
		ACQUIESCE,
		//��������ѧ����Ϣ
		SELECT_ALL_STUDENT_INFO,
		//רҵѡ��
		MAJOR_SELECT,
		//־Ըѡ��
		VOLUNTARILY_SELECT,
		//ѧУѡ��
		SCHOOL_SELECT,
		//ʡѡ��
		PROVINCE_SELECT,
		//����ѡ��
		NETHERLANDS_SELECT,
		//����ѡ��
		COUNTY_SELECT,
		//���ѧУ
		CHECK_SCHOOL_RESULT,
		//��鿼����
		CHECK_EXAMINEES_RESULT,
		//��Դ��������
		SET_PROPERTY
}



