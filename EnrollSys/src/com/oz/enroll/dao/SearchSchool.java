package com.oz.enroll.dao;

import java.util.List;

import org.json.JSONException;

import com.oz.config.API;
import com.oz.enroll.bean.County;
import com.oz.enroll.bean.Netherlands;
import com.oz.enroll.bean.Privnce;
import com.oz.enroll.bean.School;
import com.oz.tools.net.NetworkUtil;


public class SearchSchool {

	@SuppressWarnings("unused")
	private String privnceJsonString ="[{'pname':'北京','pid':1,'pcode':'110000'},{'pname':'天津','pid':2,'pcode':'120000'},{'pname':'河北','pid':3,'pcode':'130000'},{'pname':'山西','pid':4,'pcode':'140000'},{'pname':'内蒙','pid':5,'pcode':'150000'},{'pname':'辽宁','pid':6,'pcode':'210000'},{'pname':'吉林','pid':7,'pcode':'220000'},{'pname':'黑龙江','pid':8,'pcode':'230000'},{'pname':'上海','pid':9,'pcode':'310000'},{'pname':'江苏','pid':10,'pcode':'320000'},{'pname':'浙江','pid':11,'pcode':'330000'},{'pname':'安徽','pid':12,'pcode':'340000'},{'pname':'福建','pid':13,'pcode':'350000'},{'pname':'江西','pid':14,'pcode':'360000'},{'pname':'山东','pid':15,'pcode':'370000'},{'pname':'河南','pid':16,'pcode':'410000'},{'pname':'湖北','pid':17,'pcode':'420000'},{'pname':'湖南','pid':18,'pcode':'430000'},{'pname':'广东','pid':19,'pcode':'440000'},{'pname':'广西','pid':20,'pcode':'450000'},{'pname':'海南','pid':21,'pcode':'460000'},{'pname':'重庆','pid':22,'pcode':'500000'},{'pname':'四川','pid':23,'pcode':'510000'},{'pname':'贵州','pid':24,'pcode':'520000'},{'pname':'云南','pid':25,'pcode':'530000'},{'pname':'西藏','pid':26,'pcode':'540000'},{'pname':'陕西','pid':27,'pcode':'610000'},{'pname':'甘肃','pid':28,'pcode':'620000'},{'pname':'青海','pid':29,'pcode':'630000'},{'pname':'宁夏','pid':30,'pcode':'640000'},{'pname':'新疆','pid':31,'pcode':'650000'},{'pname':'台湾','pid':32,'pcode':'710000'}]"; 
	
	//获取所有的省
	public List<Privnce> getPrivnce() throws JSONException
	{
		
		String url = API.addStudentInfo.getPrivnce_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		List<Privnce> list = JsonParser.SearchSchool.privnceParser(jsonString);
				
		return list;
	}
	
	
	//通过省的Id查找该省所有的地区
	public List<Netherlands> getNetherlands(String provinceId) throws JSONException
	{
		
		String url = API.addStudentInfo.getNetherlands_api(provinceId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		List<Netherlands> list = JsonParser.SearchSchool.netherlandsParser(jsonString);
				
		return list;
	}
	
	
	//通过地区的Id查找该省所有的地区
	public List<County> getCounty(String netherlandsId) throws JSONException
	{
		
		String url = API.addStudentInfo.getCounty_api(netherlandsId);
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<County> list = JsonParser.SearchSchool.countyParser(jsonString);
		
		return list;
	}
	
	
	//通过城市的Id查找该省所有的地区
	public List<School> getSchool(String countyId) throws JSONException
	{
		
		String url = API.addStudentInfo.getSchool_api(countyId);
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<School> list = JsonParser.SearchSchool.schoolParser(jsonString);
		
		return list;
	}
	
	
}
