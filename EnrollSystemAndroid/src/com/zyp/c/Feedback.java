package com.zyp.c;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.config.api.API;
import com.main.m.StatusMessage;
import com.util.network.NetworkUtil;
import com.zyp.m.FeedbackInfo;

public class Feedback {

	public FeedbackInfo searchFeedackInfo(String examineeNumber)
	{
		
		String url = API.feedbackInfo.findBySystem_api(examineeNumber);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		return searchParese(jsonString);
		
	}
	
	
	
	
	public FeedbackInfo searchParese(String jsonString)
	{
		FeedbackInfo info = null;
		
		try {
			
			JSONArray jsonArray = new JSONArray(jsonString);
		
			if(jsonArray.length() != 0)
			{
				
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			
			info = new FeedbackInfo(
					jsonObject.getString("examineeNumber"), 
					jsonObject.getString("sex"), 
					jsonObject.getString("idCard"), 
					jsonObject.getString("schoolName"),
					jsonObject.getString("attendProfessional"),
					jsonObject.getString("voluntarily"), 
					jsonObject.getString("nid"),
					jsonObject.getString("tel"),
					jsonObject.getString("pid"), 
					jsonObject.getString("property"),
					jsonObject.getString("cid"), 
					jsonObject.getString("userIdSend"), 
					jsonObject.getString("nname"), 
					jsonObject.getString("userProperty"), 
					jsonObject.getString("userId"), 
					jsonObject.getString("pname"), 
					jsonObject.getString("stuId"), 
					jsonObject.getString("userName"), 
					jsonObject.getString("schoolId"), 
					jsonObject.getString("stuName"), 
					jsonObject.getString("modifyTime"), 
					jsonObject.getString("cname"),
					jsonObject.getString("idNumber"));
			
			}
		
		} catch (JSONException e) {
	
			e.printStackTrace();
			
		}

		return info;
		
	}
	
	
	
	
	public StatusMessage deleteFeedbackInfo(String stuId)
	{
		
		String url = API.feedbackInfo.deleteFeedback_api(stuId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		return deleteParese(jsonString);
	}
	
	
	public StatusMessage deleteParese(String jsonString)
	{
		
		StatusMessage sm = null;
		
		try {
			
			JSONObject jsonObject = new JSONObject(jsonString);
		
			sm = new StatusMessage(
					jsonObject.getString("status"),
					jsonObject.getString("message"));
			
		} catch (JSONException e) {

			e.printStackTrace();
			
		}
		
		
		
		return sm;
	}
	
	
	
	
}
