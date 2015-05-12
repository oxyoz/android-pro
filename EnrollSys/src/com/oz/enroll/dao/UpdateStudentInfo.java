package com.oz.enroll.dao;

import com.oz.bean.StatusMessage;
import com.oz.config.API;
import com.oz.enroll.bean.StudentInfo;
import com.oz.tools.net.NetworkUtil;



public class UpdateStudentInfo {

	private StudentInfo studentInfo;
	
	public UpdateStudentInfo() {}
	
	public UpdateStudentInfo(StudentInfo studentInfo) 
	{
		this.studentInfo = studentInfo;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	
	//返回提示成功或失败的StatusMessage对象
	public StatusMessage update()
	{
		String url = API.addStudentInfo.updatef_api(
				this.studentInfo.getExamineeNumber(),
				this.studentInfo.getStuName(),
				this.studentInfo.getIdCard(),
				this.studentInfo.getSex(), 
				this.studentInfo.getSchoolName(),
				this.studentInfo.getTel(), 
				this.studentInfo.getAttendProfessional(),
				this.studentInfo.getProperty(), 
				this.studentInfo.getEnteringTime(), 
				this.studentInfo.getVoluntarily());
		
		String jsonString = null;
		
		jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParser.Result.statusParser(jsonString);
		
		return statusMessage;
	}
	
	
}
