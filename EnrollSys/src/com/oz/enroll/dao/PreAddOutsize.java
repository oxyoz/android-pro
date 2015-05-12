package com.oz.enroll.dao;

import java.util.List;

import org.json.JSONException;

import com.oz.config.API;
import com.oz.enroll.bean.Major;
import com.oz.tools.net.NetworkUtil;


public class PreAddOutsize {

	//获取所有的省外专业
	public List<Major> getMajor() throws JSONException
	{
		
		String url = API.addStudentInfo.preAddOutsize_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<Major> list = JsonParser.PreAddOut.preAddOutsizeParse(jsonString);
		return list;
	}
	
	
}
