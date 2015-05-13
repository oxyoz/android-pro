package com.util.parse;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.main.m.StatusMessage;

public class JsonParse {

	
	public static StatusMessage statusParse(String jsonString)
	{
		
		StatusMessage statusMessage = null;
		
		try {
		
			JSONObject jsonObject = new JSONObject(jsonString);
			
			if(!jsonObject.isNull("isUsed")&&!jsonObject.isNull("property"))
			{
				
				statusMessage = new StatusMessage();
				
				statusMessage.setMessage(jsonObject.getString("property"));
				
				statusMessage.setStatus(jsonObject.getString("isUsed"));
				
			}
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return statusMessage;
	}
	
}
