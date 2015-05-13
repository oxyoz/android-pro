package com.oz.c;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.config.api.API;
import com.oz.m.EnteringTime;
import com.oz.m.ItAll;
import com.util.network.NetworkUtil;

public class FindItAll {


	//����������Դ��Ϣ
	public List<ItAll> finditall() throws JSONException
	{
		
		String url = API.addStudentInfo.finditall_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<ItAll> itAll = jsonParse(jsonString);
		
		return itAll;
	}
	
	
	
	//������������Դ��Ϣjson�ַ���ת��ΪList<ItAll>����
	public List<ItAll> jsonParse(String jsonString) throws JSONException
	{
		List<ItAll> list = new ArrayList<ItAll>();
		
		ItAll itAll = null;
		
		EnteringTime enteringTime = null;
		
		JSONObject jsonObject = new JSONObject(jsonString);
		
		JSONArray jsonArray = jsonObject.getJSONArray("listvwf");
		
		for(int i = 0; i < jsonArray.length(); i++ )
		{
			
			JSONObject object = jsonArray.getJSONObject(i);
			
			JSONObject time = object.getJSONObject("enteringTime");
			
			enteringTime = new EnteringTime(
					time.getString("nanos"),
					time.getString("time"), 
					time.getString("minutes"),
					time.getString("seconds"),
					time.getString("hours"),
					time.getString("month"), 
					time.getString("timezoneOffset"),
					time.getString("year"),
					time.getString("day"), 
					time.getString("date"));
			
			itAll = new ItAll(
					object.getString("examineeNumber"), 
					object.getString("idCard"), 
					object.getString("sex"), 
					object.getString("schoolName"), 
					object.getString("attendProfessional"),
					object.getString("voluntarily"), 
					object.getString("expr1"), 
					object.getString("tel"), 
					object.getString("expr2"), 
					enteringTime,
					object.getString("property"), 
					object.getString("userIdSend"), 
					object.getString("userId"), 
					object.getString("stuId"), 
					object.getString("userName"), 
					object.getString("schoolId"), 
					object.getString("stuName"), 
					object.getString("modifyTime"), 
					object.getString("idNumber"));
			
			list.add(itAll);
		}
		
		
		return list;
	}
	
}
