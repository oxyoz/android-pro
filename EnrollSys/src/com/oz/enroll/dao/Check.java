package com.oz.enroll.dao;

import com.oz.bean.StatusMessage;
import com.oz.config.API;
import com.oz.tools.net.NetworkUtil;



public class Check {

	
	//��鿼�����Ƿ���¼����Ƿ���ȷ
	public StatusMessage check(String examineeNumber)
	{
		String url = API.addStudentInfo.check_api(examineeNumber);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParser.Check.checkExamineeNumberParser(jsonString);
		
		return statusMessage;
	}
	
	
	
	
	//���ѧУ�Ƿ�������ʦ��ѡ
	public StatusMessage checkSchool(String schoolId)
	{
		String url = API.addStudentInfo.checkSchool_api(schoolId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParser.Check.checkSchoolParser(jsonString); 
		
		return statusMessage;
	}
	
}
