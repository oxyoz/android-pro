package com.oz.c;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.config.api.API;
import com.oz.m.County;
import com.oz.m.Netherlands;
import com.oz.m.Privnce;
import com.oz.m.School;
import com.util.network.NetworkUtil;

public class SearchSchool {

	private String privnceJsonString ="[{'pname':'北京','pid':1,'pcode':'110000'},{'pname':'天津','pid':2,'pcode':'120000'},{'pname':'河北','pid':3,'pcode':'130000'},{'pname':'山西','pid':4,'pcode':'140000'},{'pname':'内蒙','pid':5,'pcode':'150000'},{'pname':'辽宁','pid':6,'pcode':'210000'},{'pname':'吉林','pid':7,'pcode':'220000'},{'pname':'黑龙江','pid':8,'pcode':'230000'},{'pname':'上海','pid':9,'pcode':'310000'},{'pname':'江苏','pid':10,'pcode':'320000'},{'pname':'浙江','pid':11,'pcode':'330000'},{'pname':'安徽','pid':12,'pcode':'340000'},{'pname':'福建','pid':13,'pcode':'350000'},{'pname':'江西','pid':14,'pcode':'360000'},{'pname':'山东','pid':15,'pcode':'370000'},{'pname':'河南','pid':16,'pcode':'410000'},{'pname':'湖北','pid':17,'pcode':'420000'},{'pname':'湖南','pid':18,'pcode':'430000'},{'pname':'广东','pid':19,'pcode':'440000'},{'pname':'广西','pid':20,'pcode':'450000'},{'pname':'海南','pid':21,'pcode':'460000'},{'pname':'重庆','pid':22,'pcode':'500000'},{'pname':'四川','pid':23,'pcode':'510000'},{'pname':'贵州','pid':24,'pcode':'520000'},{'pname':'云南','pid':25,'pcode':'530000'},{'pname':'西藏','pid':26,'pcode':'540000'},{'pname':'陕西','pid':27,'pcode':'610000'},{'pname':'甘肃','pid':28,'pcode':'620000'},{'pname':'青海','pid':29,'pcode':'630000'},{'pname':'宁夏','pid':30,'pcode':'640000'},{'pname':'新疆','pid':31,'pcode':'650000'},{'pname':'台湾','pid':32,'pcode':'710000'}]"; 
	
	//获取所有的省
	public List<Privnce> getPrivnce() throws JSONException
	{
		Log.i("debug", "debug_1");
		String url = API.addStudentInfo.getPrivnce_api();
		Log.i("debug", "debug_1_URL="+url);
		String jsonString = NetworkUtil.getResponseData(url);
		Log.i("debug", "debug_1_Json="+jsonString);		
		List<Privnce> list = privnceParse(jsonString);
		//List<Privnce> list = privnceParse(privnceJsonString);
				
		return list;
	}
	
	
	//省实体的json解析器
	public List<Privnce> privnceParse(String jsonString) throws JSONException
	{
		
		Log.i("debug", "debug_2");
		List<Privnce> list = new ArrayList<Privnce>();
		
		JSONArray jsonArray = new JSONArray(jsonString);
		Log.i("debug", "debug_3");
		for(int i = 0; i < jsonArray.length(); i++)
		{
			Log.i("debug", "debug_4_"+i);
			JSONObject jsonObject = jsonArray.getJSONObject(i);	
			
			list.add(new Privnce(
					jsonObject.getString("pname"),
					jsonObject.getString("pid"), 
					jsonObject.getString("pcode")));
			
		}
		
		return list;
	}
	
	
	
	
	//通过省的Id查找该省所有的地区
	public List<Netherlands> getNetherlands(String provinceId) throws JSONException
	{
		
		String url = API.addStudentInfo.getNetherlands_api(provinceId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		List<Netherlands> list = netherlandsParse(jsonString);
				
		return list;
	}
	
	
	//地区的实体的json解析器
	public List<Netherlands> netherlandsParse(String jsonString) throws JSONException
	{
		List<Netherlands> list = new ArrayList<Netherlands>();
		
		JSONArray jsonArray = new JSONArray(jsonString);
		
		for(int i = 0; i < jsonArray.length()&&jsonArray.length()!=0; i++)
		{
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);	
			
			list.add(new Netherlands(
					jsonObject.getString("nname"),
					jsonObject.getString("nid"),
					jsonObject.getString("ncode"),
					jsonObject.getString("pid")));
			
		}
		
		return list;
	}
	
	
	
	
	//通过地区的Id查找该省所有的地区
	public List<County> getCounty(String netherlandsId) throws JSONException
	{
		
		String url = API.addStudentInfo.getCounty_api(netherlandsId);
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<County> list = countyParse(jsonString);
		
		return list;
	}
	
	
	//地区的实体的json解析器
	public List<County> countyParse(String jsonString) throws JSONException
	{
		List<County> list = new ArrayList<County>();
		
		JSONArray jsonArray = new JSONArray(jsonString);
		
		for(int i = 0; i < jsonArray.length()&&jsonArray.length()!=0; i++)
		{
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);	
			
			list.add(new County(
					jsonObject.getString("cname"),
					jsonObject.getString("ccode"),
					jsonObject.getString("cid"),
					jsonObject.getString("nid")));
		}
		
		return list;
	}
	
	
	
	
	//通过城市的Id查找该省所有的地区
	public List<School> getSchool(String countyId) throws JSONException
	{
		
		String url = API.addStudentInfo.getSchool_api(countyId);
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<School> list = schoolParse(jsonString);
		
		return list;
	}
	
	
	//城市的实体的json解析器
	public List<School> schoolParse(String jsonString) throws JSONException
	{
		List<School> list = new ArrayList<School>();
		
		JSONArray jsonArray = new JSONArray(jsonString);
		
		for(int i = 0; i < jsonArray.length()&&jsonArray.length()!=0; i++)
		{
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);	
			
			list.add(new School(
					jsonObject.getString("schoolName"), 
					jsonObject.getString("schoolCode"), 
					jsonObject.getString("schoolId"), 
					jsonObject.getString("cid")));
			
		}
		
		return list;
	}
	
	
}
