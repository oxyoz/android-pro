package com.oz.enroll.dao;

import java.util.List;

import org.json.JSONException;

import com.oz.config.API;
import com.oz.enroll.bean.ItAll;
import com.oz.tools.net.NetworkUtil;


public class FindItAll {


	//����������Դ��Ϣ
	public List<ItAll> finditall() throws JSONException
	{
		
		String url = API.addStudentInfo.finditall_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<ItAll> itAll = JsonParser.ItAllParser.itAllParser(jsonString);
		
		return itAll;
	}
	

	
}
