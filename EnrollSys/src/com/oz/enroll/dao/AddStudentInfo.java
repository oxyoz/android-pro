package com.oz.enroll.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import com.oz.bean.StatusMessage;
import com.oz.config.API;
import com.oz.enroll.bean.StudentInfo;
import com.oz.tools.net.NetworkUtil;


public class AddStudentInfo {

	
	private StudentInfo studentInfo;

	public AddStudentInfo() {
	}

	public AddStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	// 省内添加学生信息，返回提示成功或失败的StatusMessage对象
	public StatusMessage addStudentInfo() {
		String url = null;
		
		if (this.studentInfo.getFlag().equals("0")) {
			
			url = API.addStudentInfo.addStudentInfo_api(
					this.studentInfo.getExamineeNumber(),
					this.studentInfo.getStuName(), this.studentInfo.getFlag(),
					this.studentInfo.getSchoolId(),
					this.studentInfo.getAttendProfessional(),
					this.studentInfo.getIdCard(), this.studentInfo.getTel(),
					this.studentInfo.getIdNumber(),
					this.studentInfo.getVoluntarily(),
					this.studentInfo.getSex(), this.studentInfo.getProperty());

			
		} else if (this.studentInfo.getFlag().equals("1")) {

			url = API.addStudentInfo.addStudentInfo_api(
					this.studentInfo.getExamineeNumber(),
					this.studentInfo.getStuName(), this.studentInfo.getFlag(),
					this.studentInfo.getSchoolId(),
					this.studentInfo.getAttendProfessional(),
					this.studentInfo.getIdCard(), this.studentInfo.getTel(),
					this.studentInfo.getIdNumber(),
					this.studentInfo.getVoluntarily(),
					this.studentInfo.getSex(), this.studentInfo.getProperty(),
					this.studentInfo.getProvince(),
					this.studentInfo.getNetherlands(),
					this.studentInfo.getCounty());

		}

		String jsonString = null;
		
		jsonString = NetworkUtil.getResponseData(url);
		
		StatusMessage statusMessage = JsonParser.Result.statusParser(jsonString);
		
		return statusMessage;
	}

	// 省内添加学生信息，返回提示成功或失败的StatusMessage对象
	public StatusMessage postAddStudentInfo() {

		String url = API.addStudentInfo.addStudentInfo_api();

		List<NameValuePair> parameters = null;

		if (this.studentInfo.getFlag().equals("0")) {

			parameters = new ArrayList<NameValuePair>();

			parameters.add(new BasicNameValuePair(
					"feedbackInfo.examineeNumber", this.studentInfo
							.getExamineeNumber()));

			parameters.add(new BasicNameValuePair("feedbackInfo.stuName",
					this.studentInfo.getStuName()));

			parameters.add(new BasicNameValuePair("feedbackInfo.flag",
					this.studentInfo.getFlag()));

			parameters.add(new BasicNameValuePair("feedbackInfo.schoolId",
					this.studentInfo.getSchoolId()));

			parameters.add(new BasicNameValuePair(
					"feedbackInfo.attendProfessional", this.studentInfo
							.getAttendProfessional()));

			parameters.add(new BasicNameValuePair("feedbackInfo.idCard",
					this.studentInfo.getIdCard()));

			parameters.add(new BasicNameValuePair("feedbackInfo.tel",
					this.studentInfo.getTel()));

			parameters.add(new BasicNameValuePair("feedbackInfo.idNumber",
					this.studentInfo.getIdNumber()));

			parameters.add(new BasicNameValuePair("feedbackInfo.voluntarily",
					this.studentInfo.getVoluntarily()));

			parameters.add(new BasicNameValuePair("feedbackInfo.sex",
					this.studentInfo.getSex()));

			parameters.add(new BasicNameValuePair("feedbackInfo.property",
					this.studentInfo.getProperty()));


		} else if (this.studentInfo.getFlag().equals("1")) {

			parameters = new ArrayList<NameValuePair>();

			parameters.add(new BasicNameValuePair(
					"feedbackInfo.examineeNumber", this.studentInfo
							.getExamineeNumber()));

			parameters.add(new BasicNameValuePair("feedbackInfo.stuName",
					this.studentInfo.getStuName()));

			parameters.add(new BasicNameValuePair("feedbackInfo.flag",
					this.studentInfo.getFlag()));

			parameters.add(new BasicNameValuePair("feedbackInfo.schoolId",
					this.studentInfo.getSchoolId()));

			parameters.add(new BasicNameValuePair(
					"feedbackInfo.attendProfessional", this.studentInfo
							.getAttendProfessional()));

			parameters.add(new BasicNameValuePair("feedbackInfo.idCard",
					this.studentInfo.getIdCard()));

			parameters.add(new BasicNameValuePair("feedbackInfo.tel",
					this.studentInfo.getTel()));

			parameters.add(new BasicNameValuePair("feedbackInfo.idNumber",
					this.studentInfo.getIdNumber()));

			parameters.add(new BasicNameValuePair("feedbackInfo.voluntarily",
					this.studentInfo.getVoluntarily()));

			parameters.add(new BasicNameValuePair("feedbackInfo.sex",
					this.studentInfo.getSex()));

			parameters.add(new BasicNameValuePair("feedbackInfo.property",
					this.studentInfo.getProperty()));

			parameters.add(new BasicNameValuePair("province", this.studentInfo
					.getProvince()));

			parameters.add(new BasicNameValuePair("netherlands",
					this.studentInfo.getNetherlands()));

			parameters.add(new BasicNameValuePair("county", this.studentInfo
					.getCounty()));


		}

		String jsonString = null;

		jsonString = NetworkUtil.postResponseData(url, parameters);

		StatusMessage statusMessage = JsonParser.Result.statusParser(jsonString);

		return statusMessage;
	}

	

	// 省外添加学生信息，返回提示成功或失败的StatusMessage对象
	public StatusMessage addInOutsize() {
		String url = null;

		url = API.addStudentInfo
				.addInOutsize_api(this.studentInfo.getExamineeNumber(),
						this.studentInfo.getStuName(),
						this.studentInfo.getSchoolName(),
						this.studentInfo.getAttendProfessional(),
						this.studentInfo.getIdCard(),
						this.studentInfo.getTel(),
						this.studentInfo.getIdNumber(),
						this.studentInfo.getVoluntarily(),
						this.studentInfo.getSex(),
						this.studentInfo.getProperty(),
						this.studentInfo.getProvince(),
						this.studentInfo.getNetherlands(),
						this.studentInfo.getCounty());

		String jsonString = null;

		jsonString = NetworkUtil.getResponseData(url);

		StatusMessage statusMessage = JsonParser.Result.statusParser(jsonString);

		return statusMessage;
	}

	// 省外添加学生信息，返回提示成功或失败的StatusMessage对象
	public StatusMessage postAddOutsize() {

		String url = API.addStudentInfo.addInOutsize_api();

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();

		parameters.add(new BasicNameValuePair("feedbackInfo.examineeNumber",
				this.studentInfo.getExamineeNumber()));

		parameters.add(new BasicNameValuePair("feedbackInfo.stuName",
				this.studentInfo.getStuName()));

		parameters.add(new BasicNameValuePair(
				"feedbackInfo.attendProfessional", this.studentInfo
						.getAttendProfessional()));

		parameters.add(new BasicNameValuePair("feedbackInfo.idCard",
				this.studentInfo.getIdCard()));

		parameters.add(new BasicNameValuePair("feedbackInfo.tel",
				this.studentInfo.getTel()));

		parameters.add(new BasicNameValuePair("feedbackInfo.idNumber",
				this.studentInfo.getIdNumber()));

		parameters.add(new BasicNameValuePair("feedbackInfo.voluntarily",
				this.studentInfo.getVoluntarily()));

		parameters.add(new BasicNameValuePair("feedbackInfo.sex",
				this.studentInfo.getSex()));

		parameters.add(new BasicNameValuePair("feedbackInfo.property",
				this.studentInfo.getProperty()));

		parameters.add(new BasicNameValuePair("province", this.studentInfo
				.getProvince()));

		parameters.add(new BasicNameValuePair("netherlands", this.studentInfo
				.getNetherlands()));

		parameters.add(new BasicNameValuePair("county", this.studentInfo
				.getCounty()));

		parameters.add(new BasicNameValuePair("schoolName", this.studentInfo
				.getSchoolName()));

		String jsonString = NetworkUtil.postResponseData(url, parameters);

		StatusMessage statusMessage = JsonParser.Result.statusParser(jsonString);

		return statusMessage;
	}

	
}
