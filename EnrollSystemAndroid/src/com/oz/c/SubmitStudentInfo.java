package com.oz.c;

import com.oz.m.StudentInfo;

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
