package com.oz.c;

import java.util.ArrayList;
import java.util.List;

import com.config.api.API;
import com.main.m.StatusMessage;
import com.util.network.NetworkUtil;
import com.util.parse.JsonParse;

public class GetProperty {

	//��ȡ���ѧ����Ϣ����Դ����
	public List<StatusMessage> getProperty()
	{
		String url = API.addStudentInfo.getProperty_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParse.statusParse(jsonString); 
		
		List<StatusMessage> list = new ArrayList<StatusMessage>();
		
		list.add(statusMessage);
				
		return list;
	}
	
}
