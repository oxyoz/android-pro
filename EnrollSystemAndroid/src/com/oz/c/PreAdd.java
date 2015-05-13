package com.oz.c;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.config.api.API;
import com.oz.m.Entity;
import com.oz.m.InputTime;
import com.oz.m.Major;
import com.oz.m.StaffSchool;
import com.util.network.NetworkUtil;

public class PreAdd {

	
	//获取所有的省内专业
	public List<Major> getMajor() throws JSONException
	{
		
		String url = API.addStudentInfo.preAdd_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<Major> list = (List<Major>) preAddParse(jsonString, "lsMajor");
		
		return list;
	}
	
	//获取所有的默认学校
	public List<StaffSchool> getStaffSchool() throws JSONException
	{
		
		String url = API.addStudentInfo.preAdd_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<StaffSchool> list = (List<StaffSchool>) preAddParse(jsonString, "lsStaffSchool");
		
		return list;
	}
	
	
	//专业实体的json解析器
	public List<? extends Entity> preAddParse(String jsonString, String type) throws JSONException
	{
		
				
		JSONObject jsonObject = new JSONObject(jsonString);
		
		if(type.equals("lsMajor"))
		{
			
			List<Major> list = null;
			
			list = new ArrayList<Major>();
			
			JSONArray jsonArray = jsonObject.getJSONArray(type);
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
			
				JSONObject jsonMajor = jsonArray.getJSONObject(i);
				
				list.add(new Major(
						jsonMajor.getString("isUsed"),
						jsonMajor.getString("mid"),
						jsonMajor.getString("majorCode"),
						jsonMajor.getString("majorName")));
				
			}
			
			Log.i("preAddParse", "List<Major>.size = "+ list.size());
			
			return list;
			
		}
		else if(type.equals("lsStaffSchool"))
		{			
			List<StaffSchool> list = null;
			
			list = new ArrayList<StaffSchool>();
			
			JSONArray jsonArray = jsonObject.getJSONArray(type);
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				
				JSONObject jsonStaffSchool = jsonArray.getJSONObject(i);
				
				JSONObject jsonInputTime = jsonStaffSchool.getJSONObject("inputTime");
				
				InputTime inputTime = new InputTime(
						jsonInputTime.getString("nanos"),
						jsonInputTime.getString("time"), 
						jsonInputTime.getString("minutes"),
						jsonInputTime.getString("seconds"),
						jsonInputTime.getString("hours"),
						jsonInputTime.getString("month"),
						jsonInputTime.getString("timezoneOffset"),
						jsonInputTime.getString("year"),
						jsonInputTime.getString("day"),
						jsonInputTime.getString("date"));
				
				list.add(new StaffSchool(
						jsonStaffSchool.getString("linkmanTel"),
						jsonStaffSchool.getString("contactId"), 
						jsonStaffSchool.getString("schoolName"), 
						jsonStaffSchool.getString("linkmanPost"), 
						jsonStaffSchool.getString("userId"), 
						jsonStaffSchool.getString("remarks"), 
						jsonStaffSchool.getString("linkman"), 
						jsonStaffSchool.getString("schoolId"),
						jsonStaffSchool.getString("cid"), 
						inputTime));
								
			}			
				
			Log.i("preAddParse", "List<StaffSchool>.size = "+ list.size());
			
			return list;
			
		}
				
		return null;
	}
	
	
	
}
