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
	private String privnceJsonString ="[{'pname':'����','pid':1,'pcode':'110000'},{'pname':'���','pid':2,'pcode':'120000'},{'pname':'�ӱ�','pid':3,'pcode':'130000'},{'pname':'ɽ��','pid':4,'pcode':'140000'},{'pname':'����','pid':5,'pcode':'150000'},{'pname':'����','pid':6,'pcode':'210000'},{'pname':'����','pid':7,'pcode':'220000'},{'pname':'������','pid':8,'pcode':'230000'},{'pname':'�Ϻ�','pid':9,'pcode':'310000'},{'pname':'����','pid':10,'pcode':'320000'},{'pname':'�㽭','pid':11,'pcode':'330000'},{'pname':'����','pid':12,'pcode':'340000'},{'pname':'����','pid':13,'pcode':'350000'},{'pname':'����','pid':14,'pcode':'360000'},{'pname':'ɽ��','pid':15,'pcode':'370000'},{'pname':'����','pid':16,'pcode':'410000'},{'pname':'����','pid':17,'pcode':'420000'},{'pname':'����','pid':18,'pcode':'430000'},{'pname':'�㶫','pid':19,'pcode':'440000'},{'pname':'����','pid':20,'pcode':'450000'},{'pname':'����','pid':21,'pcode':'460000'},{'pname':'����','pid':22,'pcode':'500000'},{'pname':'�Ĵ�','pid':23,'pcode':'510000'},{'pname':'����','pid':24,'pcode':'520000'},{'pname':'����','pid':25,'pcode':'530000'},{'pname':'����','pid':26,'pcode':'540000'},{'pname':'����','pid':27,'pcode':'610000'},{'pname':'����','pid':28,'pcode':'620000'},{'pname':'�ຣ','pid':29,'pcode':'630000'},{'pname':'����','pid':30,'pcode':'640000'},{'pname':'�½�','pid':31,'pcode':'650000'},{'pname':'̨��','pid':32,'pcode':'710000'}]"; 
	
	//��ȡ���е�ʡ
	public List<Privnce> getPrivnce() throws JSONException
	{
		
		String url = API.addStudentInfo.getPrivnce_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		List<Privnce> list = JsonParser.SearchSchool.privnceParser(jsonString);
				
		return list;
	}
	
	
	//ͨ��ʡ��Id���Ҹ�ʡ���еĵ���
	public List<Netherlands> getNetherlands(String provinceId) throws JSONException
	{
		
		String url = API.addStudentInfo.getNetherlands_api(provinceId);
		
		String jsonString = NetworkUtil.getResponseData(url);
		
		List<Netherlands> list = JsonParser.SearchSchool.netherlandsParser(jsonString);
				
		return list;
	}
	
	
	//ͨ��������Id���Ҹ�ʡ���еĵ���
	public List<County> getCounty(String netherlandsId) throws JSONException
	{
		
		String url = API.addStudentInfo.getCounty_api(netherlandsId);
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<County> list = JsonParser.SearchSchool.countyParser(jsonString);
		
		return list;
	}
	
	
	//ͨ�����е�Id���Ҹ�ʡ���еĵ���
	public List<School> getSchool(String countyId) throws JSONException
	{
		
		String url = API.addStudentInfo.getSchool_api(countyId);
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		List<School> list = JsonParser.SearchSchool.schoolParser(jsonString);
		
		return list;
	}
	
	
}
