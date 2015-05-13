package com.oz.c;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.config.api.API;
import com.oz.m.Major;
import com.util.network.NetworkUtil;

public class PreAddOutsize {

	//获取所有的省外专业
	public List<Major> getMajor() throws JSONException
	{
		
		String url = API.addStudentInfo.preAddOutsize_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<Major> list = preAddOutsizeParse(jsonString);
		
		return list;
	}
	
	
	//获取省外添加学生信息的专业
	public List<Major> preAddOutsizeParse(String jsonString) throws JSONException
	{
		List<Major> list = new ArrayList<Major>();
		
		
		JSONObject jsonObject = new JSONObject(jsonString);
		
		JSONArray jsonArray = jsonObject.getJSONArray("lsMajor");
		
		for(int i = 0; i < jsonArray.length(); i++)
		{
						
			JSONObject object = jsonArray.getJSONObject(i);
			
			list.add(new Major(
					object.getString("isUsed"), 
					object.getString("mid"), 
					object.getString("majorCode"), 
					object.getString("majorName")));
			
		}
		
		
		return list;
	}
	
}
