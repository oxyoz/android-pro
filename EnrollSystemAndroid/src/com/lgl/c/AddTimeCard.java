package com.lgl.c;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.config.api.API;
import com.lgl.m.Time;
import com.lgl.m.TimeCard;
import com.main.m.StatusMessage;
import com.util.network.NetworkUtil;

public class AddTimeCard {

	
	/*获取所有时间卡*/
	public List<TimeCard> getTimeCard()
	{
		
		String url = API.setTime.showAllTime_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		return parseGetTimeCard(jsonString);
	}
	
	

	public List<TimeCard> parseGetTimeCard(String jsonString)
	{
		
		if(jsonString != null)
		{
			if(jsonString.length() > 2)
			{	
			
			try {
				
				JSONArray jsonarray = new JSONArray(jsonString);
				
				List<TimeCard> list = new ArrayList<TimeCard>();
				
				for(int i = 0 ; i < jsonarray.length() ; i++)
				{
					
					JSONObject timeCard = jsonarray.getJSONObject(i);
					
					JSONObject startTime = timeCard.getJSONObject("startTime");
					
					JSONObject endTime = timeCard.getJSONObject("endTime");
					 
					list.add(new TimeCard(
								 new Time(
										 startTime.getString("nanos"),
										 startTime.getString("time"), 
										 startTime.getString("minutes"), 
										 startTime.getString("seconds"), 
										 startTime.getString("hours"), 
										 startTime.getString("month"), 
										 startTime.getString("timezoneOffset"), 
										 startTime.getString("year"), 
										 startTime.getString("day"), 
										 startTime.getString("date")),
										 timeCard.getString("remark"), 
										 timeCard.getString("year"), 
										 timeCard.getString("tid"), 
										 timeCard.getString("contact"),
								new Time(
										 endTime.getString("nanos"),
										 endTime.getString("time"), 
										 endTime.getString("minutes"), 
										 endTime.getString("seconds"), 
										 endTime.getString("hours"), 
										 endTime.getString("month"), 
										 endTime.getString("timezoneOffset"), 
										 endTime.getString("year"), 
										 endTime.getString("day"), 
										 endTime.getString("date"))));	
					
				}
				
				return list;
				
			} catch (JSONException e) {
				
				e.printStackTrace();
				
			}
			
		}	
			
	  }
		
		return null;
	} 
	
	
	/*添加时间卡*/
	public StatusMessage addTimeCard(SubmitTimeCard submitTimeCard)
	{
		
		String url = API.setTime.addTime_api();
		
		List<NameValuePair> list = new ArrayList<NameValuePair>(); 
		
		list.add(new BasicNameValuePair("setTime.startTime",submitTimeCard.getStartTime()));
		
		list.add(new BasicNameValuePair("setTime.contact",submitTimeCard.getContent()));
		
		list.add(new BasicNameValuePair("setTime.endTime",submitTimeCard.getEndTime()));
		
		String jsonString = NetworkUtil.postResponseData(url, list);
		
		return parseAddTimeCard(jsonString);
	}
	
	
	@SuppressWarnings("null")
	public StatusMessage parseAddTimeCard(String jsonString)
	{
		
		if(jsonString != null || jsonString.length() > 2)
		{
			
			try {
				
				JSONObject jsonObject = new JSONObject(jsonString);
				
				return new StatusMessage(
						jsonObject.getString("status"), 
						jsonObject.getString("message"));
				
			} catch (JSONException e) {

				e.printStackTrace();
				
			}
				
		}
		
		return null;
	} 
	
	/*更改时间卡*/
	public StatusMessage updateTimeCard(SubmitTimeCard submitTimeCard)
	{
		
		String url = API.setTime.updateTime_api();
		
		List<NameValuePair> list = new ArrayList<NameValuePair>(); 
		
		list.add(new BasicNameValuePair("setTime.startTime",submitTimeCard.getStartTime()));
		
		list.add(new BasicNameValuePair("setTime.contact",submitTimeCard.getContent()));
		
		list.add(new BasicNameValuePair("setTime.endTime",submitTimeCard.getEndTime()));
		
		String jsonString = NetworkUtil.postResponseData(url, list);
		
		return parseAddTimeCard(jsonString);
		
	}
	
	
	@SuppressWarnings("null")
	public StatusMessage parseUpdateTimeCard(String jsonString)
	{
		
		if(jsonString != null || jsonString.length() > 2)
		{
			
			try {
				
				JSONObject jsonObject = new JSONObject(jsonString);
				
				return new StatusMessage(
						jsonObject.getString("status"), 
						jsonObject.getString("message"));
				
			} catch (JSONException e) {

				e.printStackTrace();
				
			}
				
		}
				
		return null;
	} 
	
	
	/*删除时间卡*/
	public StatusMessage deleteTimeCard(String tid)
	{
		
		String url = API.setTime.deleteTime_api(tid);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		return parseDeleteTimeCard(jsonString);
	}
	
	
	
	@SuppressWarnings("null")
	public StatusMessage parseDeleteTimeCard(String jsonString)
	{
		
		if(jsonString != null || jsonString.length() > 2)
		{
			
			try {
				
				JSONObject jsonObject = new JSONObject(jsonString);
				
				return new StatusMessage(
						jsonObject.getString("status"), 
						jsonObject.getString("message"));
				
			} catch (JSONException e) {

				e.printStackTrace();
				
			}
				
		}
				
		return null;
	} 
	
}
