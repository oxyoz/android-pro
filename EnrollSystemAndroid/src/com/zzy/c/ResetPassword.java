package com.zzy.c;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.config.api.API;
import com.main.m.StatusMessage;
import com.util.network.NetworkUtil;
import com.zzy.m.UserEntity;

public class ResetPassword {

	
	
	
	public UserEntity findSingleUserInfo(String userName)
	{
		
		
		String url = API.resetPassword.singleUserInfoAction_api(userName);
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		return findParse(jsonString);
		
	}
	
	
	
	
	public StatusMessage resetPassword(String userId)
	{
		String url=API.resetPassword.updatePassowrd_api(userId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		Log.i("jsonString", jsonString);
		
		return resetParse(jsonString); 
	}
	
	
	
	
	public UserEntity findParse(String jsonString)
	{
		
		UserEntity userInfo = null;
		
		try {
			
			JSONArray jsonArray = new JSONArray(jsonString);
			
			JSONObject obj = jsonArray.getJSONObject(0);
			
			userInfo = new UserEntity(
					obj.getString("sex"),
					obj.getString("isUsed"),
					obj.getString("post"),
					obj.getString("email"),
					obj.getString("userProperty"),
					obj.getString("tel"),
					obj.getString("userId"),
					obj.getString("userName"),
					obj.getString("number"),
					obj.getString("businessId"),
					obj.getString("password"));
			
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
			
		return userInfo;
	}
	
	
	public StatusMessage resetParse(String jsonString)
	{
		StatusMessage userInfo=null;
		
		try {
			JSONObject obj=new JSONObject(jsonString);
			
			userInfo=new StatusMessage(
					 
					obj.getString("status"),
					
					obj.getString("message")
					
					);
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return userInfo;
	}

	
	
	
}
