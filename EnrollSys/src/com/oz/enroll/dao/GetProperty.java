package com.oz.enroll.dao;

import java.util.ArrayList;
import java.util.List;

import com.oz.bean.StatusMessage;
import com.oz.config.API;
import com.oz.tools.net.NetworkUtil;


public class GetProperty {

	//��ȡ���ѧ����Ϣ����Դ����
	public List<StatusMessage> getProperty()
	{
		String url = API.addStudentInfo.getProperty_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParser.Property.propertyParser(jsonString); 
		
		List<StatusMessage> list = new ArrayList<StatusMessage>();
		
		list.add(statusMessage);
				
		return list;
	}
	
}
