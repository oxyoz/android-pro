package com.oz.enroll.dao;

import com.oz.bean.StatusMessage;
import com.oz.config.API;
import com.oz.tools.net.NetworkUtil;



public class Check {

	
	//检查考生号是否已录入和是否正确
	public StatusMessage check(String examineeNumber)
	{
		String url = API.addStudentInfo.check_api(examineeNumber);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParser.Check.checkExamineeNumberParser(jsonString);
		
		return statusMessage;
	}
	
	
	
	
	//检查学校是否被其他老师已选
	public StatusMessage checkSchool(String schoolId)
	{
		String url = API.addStudentInfo.checkSchool_api(schoolId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParser.Check.checkSchoolParser(jsonString); 
		
		return statusMessage;
	}
	
}
