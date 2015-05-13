package com.oz.c;

import org.json.JSONException;
import org.json.JSONObject;

import com.config.api.API;
import com.main.m.StatusMessage;
import com.util.network.NetworkUtil;
import com.util.parse.JsonParse;

public class Check {

	
	//检查考生号是否已录入和是否正确
	public StatusMessage check(String examineeNumber)
	{
		String url = API.addStudentInfo.check_api(examineeNumber);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = checkParse(jsonString); 
		
		return statusMessage;
	}
	
	
	
	
	public static StatusMessage checkParse(String jsonString)
	{
		
		StatusMessage statusMessage = null;
		
		try {
		
			JSONObject jsonObject = new JSONObject(jsonString);
			
			if(!jsonObject.isNull("state")&&!jsonObject.isNull("msg"))
			{
				
				statusMessage = new StatusMessage();
				
				statusMessage.setMessage(jsonObject.getString("msg"));
				
				statusMessage.setStatus(jsonObject.getString("state"));
				
			}
			
			
			if(!jsonObject.isNull("userTel")&&!jsonObject.isNull("state")&&!jsonObject.isNull("userName"))
			{
				
				statusMessage = new StatusMessage();
				
				statusMessage.setStatus(jsonObject.getString("state"));
				
				statusMessage.setMessage("该考生号已存在，归"+jsonObject.getString("userName")+"所有!"+"联系电话："+jsonObject.getString("userTel"));
				
			}
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return statusMessage;
	}
	
	
	
	public static StatusMessage checkSchoolParse(String jsonString)
	{
		
		StatusMessage statusMessage = null;
		
		try {
		
			JSONObject jsonObject = new JSONObject(jsonString);
			
			if(!jsonObject.isNull("state")&&!jsonObject.isNull("msg"))
			{
				
				statusMessage = new StatusMessage();
				
				statusMessage.setMessage(jsonObject.getString("msg"));
				
				statusMessage.setStatus(jsonObject.getString("state"));
				
			}
			
			
			if(!jsonObject.isNull("userTel")&&!jsonObject.isNull("state")&&!jsonObject.isNull("userName"))
			{
				
				statusMessage = new StatusMessage();
				
				statusMessage.setStatus(jsonObject.getString("state"));
				
				statusMessage.setMessage("已经被"+jsonObject.getString("userName")+"选择!"+"联系电话："+jsonObject.getString("userTel"));
				
			}
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return statusMessage;
	}
	
	//检查学校是否被其他老师已选
	public StatusMessage checkSchool(String schoolId)
	{
		String url = API.addStudentInfo.checkSchool_api(schoolId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = checkSchoolParse(jsonString); 
		
		return statusMessage;
	}
	
}
