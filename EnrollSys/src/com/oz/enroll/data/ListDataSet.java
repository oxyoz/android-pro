package com.oz.enroll.data;

import java.util.ArrayList;
import java.util.List;

import com.oz.bean.StatusMessage;
import com.oz.enroll.bean.County;
import com.oz.enroll.bean.Entity;
import com.oz.enroll.bean.ItAll;
import com.oz.enroll.bean.Major;
import com.oz.enroll.bean.Netherlands;
import com.oz.enroll.bean.Privnce;
import com.oz.enroll.bean.School;
import com.oz.enroll.bean.StaffSchool;
import com.oz.enroll.bean.Volunteer;



public class ListDataSet {


	private ListDataSet() {
		
	}

	private static ListDataSet dataSet = null;
	
	
	public void setDataSetEmpty()
	{
		
		dataSet = null;
		
	}
	
	
	public static ListDataSet getInstance()
	{
		
		if(dataSet == null)
		{
			
			dataSet = new ListDataSet();
			
		}
			
		return dataSet;		
	}
	
	

	public List<Major> getInMajorList() {
		return inMajorList;
	}


	public void setInMajorList(List<Major> inMajorList) {
		this.inMajorList = inMajorList;
	}


	public List<StaffSchool> getStaffSchoolList() {
		return staffSchoolList;
	}


	public void setStaffSchoolList(List<StaffSchool> staffSchoolList) {
		this.staffSchoolList = staffSchoolList;
	}


	public List<Volunteer> getVolunteerList() {
		
		if(this.volunteerList == null)
		{
			
		this.volunteerList = new ArrayList<Volunteer>();
		
		this.volunteerList.add(new Volunteer("第一志愿"));
		
		this.volunteerList.add(new Volunteer("第二志愿"));
		
		this.volunteerList.add(new Volunteer("第三志愿"));
		
		this.volunteerList.add(new Volunteer("第四志愿"));
		
		this.volunteerList.add(new Volunteer("第五志愿"));
		
		}
		
		return volunteerList;
	}


	public void setVolunteerList(List<Volunteer> volunteerList) {
		
		this.volunteerList= volunteerList;
	}


	public List<StatusMessage> getPropertyList() {
		return propertyList;
	}


	public void setPropertyList(List<StatusMessage> propertyList) {
		this.propertyList = propertyList;
	}


	public List<Major> getOutMajorList() {
		return outMajorList;
	}


	public void setOutMajorList(List<Major> outMajorList) {
		this.outMajorList = outMajorList;
	}


	public List<Privnce> getPrivnceList() {
		return privnceList;
	}


	public void setPrivnceList(List<Privnce> privnceList) {
		this.privnceList = privnceList;
	}


	public List<Netherlands> getNetherlandsList() {
		return netherlandsList;
	}


	public void setNetherlandsList(List<Netherlands> netherlandsList) {
		this.netherlandsList = netherlandsList;
	}


	public List<County> getCountyList() {
		return countyList;
	}


	public void setCountyList(List<County> countyList) {
		this.countyList = countyList;
	}


	public List<School> getSchoolList() {
		return schoolList;
	}


	public void setSchoolList(List<School> schoolList) {
		this.schoolList = schoolList;
	}
	
	
	
	public static String[] getStringArray(List<? extends Entity> list)
	{
		String[] stringArray = null; 
		
		if(list!=null)
		{	
		
		if(list.get(0) instanceof Major)
		{
			
			List<Major> data = (List<Major>) list;
			
			stringArray = new String[data.size()];
			
			for(int i = 0 ; i < stringArray.length&&stringArray.length!=0 ; i++ )
			{
				
				stringArray[i] =  data.get(i).getMajorName();
				
			}
			
			return stringArray;
			
		}
			
		if(list.get(0) instanceof StaffSchool)
		{
			
			List<StaffSchool> data = (List<StaffSchool>) list;
			
			stringArray = new String[data.size()];
			
			for(int i = 0 ; i < stringArray.length&&stringArray.length!=0 ; i++ )
			{
				
				stringArray[i] =  data.get(i).getSchoolName();
				
			}
			
			return stringArray;
			
			
		}
		
		if(list.get(0) instanceof Privnce)
		{
			
			List<Privnce> data = (List<Privnce>) list;
			
			stringArray = new String[data.size()];
			
			for(int i = 0 ; i < stringArray.length&&stringArray.length!=0 ; i++ )
			{
				
				stringArray[i] =  data.get(i).getPname();
				
			}
			
			return stringArray;
			
			
			
		}
			
		if(list.get(0) instanceof Netherlands)
		{
			
			List<Netherlands> data = (List<Netherlands>) list;
			
			stringArray = new String[data.size()];
			
			for(int i = 0 ; i < stringArray.length&&stringArray.length!=0 ; i++ )
			{
				
				stringArray[i] =  data.get(i).getNname();
				
			}
			
			return stringArray;
			
			
		}
		
		if(list.get(0) instanceof County)
		{
			
			List<County> data = (List<County>) list;
			
			stringArray = new String[data.size()];
			
			for(int i = 0 ; i < stringArray.length&&stringArray.length!=0 ; i++ )
			{
				
				stringArray[i] =  data.get(i).getCname();
				
			}
			
			return stringArray;
			
		}
			
		if(list.get(0) instanceof School)
		{
			
			List<School> data = (List<School>) list;
			
			stringArray = new String[data.size()];
			
			for(int i = 0 ; i < stringArray.length&&stringArray.length!=0 ; i++ )
			{
				
				stringArray[i] =  data.get(i).getSchoolName();
				
			}
			
			return stringArray;
			
		}
		
	}
		
		return null;
	}
	
	
	public List<ItAll> getItAll() {
		return itAll;
	}


	public void setItAll(List<ItAll> itAll) {
		this.itAll = itAll;
	}

	
	
	
	
	private List<ItAll> itAll = null;
	
	private List<Major> inMajorList = null;
	
	private List<StaffSchool> staffSchoolList = null;
	
	private List<Volunteer> volunteerList = null;
	
	private List<StatusMessage> propertyList = null;
	
	private List<Major> outMajorList = null;
	
	private List<Privnce> privnceList = null;
	
	private List<Netherlands> netherlandsList = null;
	
	private List<County> countyList = null;
	
	private List<School> schoolList = null;
	
	
}
