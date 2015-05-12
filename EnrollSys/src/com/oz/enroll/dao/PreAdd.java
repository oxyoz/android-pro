package com.oz.enroll.dao;

import java.util.List;

import org.json.JSONException;

import com.oz.config.API;
import com.oz.enroll.bean.Major;
import com.oz.enroll.bean.StaffSchool;
import com.oz.tools.net.NetworkUtil;



public class PreAdd {

	
	//获取所有的省内专业
	public List<Major> getMajor() throws JSONException
	{
		
		String url = API.addStudentInfo.preAdd_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		
		List<Major> list = (List<Major>)JsonParser.PreAdd.preAddParse(jsonString, "lsMajor");
		
		return list;
	}
	
	
	
	//获取所有的默认学校
	public List<StaffSchool> getStaffSchool() throws JSONException
	{
		
		String url = API.addStudentInfo.preAdd_api();
		
		String jsonString = NetworkUtil.getResponseData(url);
				
		@SuppressWarnings("unchecked")
		List<StaffSchool> list = (List<StaffSchool>) JsonParser.PreAdd.preAddParse(jsonString, "lsStaffSchool");
		
		return list;
	}
	

	
}
