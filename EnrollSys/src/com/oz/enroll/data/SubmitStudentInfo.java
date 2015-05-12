package com.oz.enroll.data;

import com.oz.enroll.bean.StudentInfo;



public class SubmitStudentInfo {

	
	private static StudentInfo studentInfo = null;
	
	public static StudentInfo getStudentInfo()
	{
		if(studentInfo == null)
		{
			
			studentInfo = new StudentInfo();
			
		}
		
		return studentInfo;
	}
	
	
}
