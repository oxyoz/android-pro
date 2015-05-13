package com.oz.c;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.config.api.API;
import com.main.m.StatusMessage;
import com.oz.m.StudentInfo;
import com.util.network.NetworkUtil;

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
		Log.i("debug", "debug_1_4_0");
		if (this.studentInfo.getFlag().equals("0")) {
			Log.i("debug", "debug_1_4_1_0");
			url = API.addStudentInfo.addStudentInfo_api(
					this.studentInfo.getExamineeNumber(),
					this.studentInfo.getStuName(), this.studentInfo.getFlag(),
					this.studentInfo.getSchoolId(),
					this.studentInfo.getAttendProfessional(),
					this.studentInfo.getIdCard(), this.studentInfo.getTel(),
					this.studentInfo.getIdNumber(),
					this.studentInfo.getVoluntarily(),
					this.studentInfo.getSex(), this.studentInfo.getProperty());

			Log.i("debug", "debug_1_4_1_1");
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
		Log.i("debug", "debug_1_4_1");
		jsonString = NetworkUtil.getResponseData(url);
		Log.i("debug", "debug_1_4_2");
		StatusMessage statusMessage = statusParse(jsonString);
		Log.i("debug", "debug_1_4_3");
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

			// parameters.add(new BasicNameValuePair("province",
			// this.studentInfo.getProvince()));

			// parameters.add(new BasicNameValuePair("netherlands",
			// this.studentInfo.getNetherlands()));

			// parameters.add(new BasicNameValuePair("county",
			// this.studentInfo.getCounty()));

			// parameters.add(new BasicNameValuePair("schoolName",
			// this.studentInfo.getSchoolName()));

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

			// parameters.add(new BasicNameValuePair("schoolName",
			// this.studentInfo.getSchoolName()));

		}

		String jsonString = null;

		jsonString = NetworkUtil.postResponseData(url, parameters);

		StatusMessage statusMessage = statusParse(jsonString);

		return statusMessage;
	}

	private StatusMessage statusParse(String jsonString) {

		StatusMessage statusMessage = null;

		try {

			JSONObject jsonObject = new JSONObject(jsonString);

			if (jsonObject != null) {

				if (!jsonObject.isNull("state") && !jsonObject.isNull("msg")) {

					statusMessage = new StatusMessage();

					statusMessage.setStatus(jsonObject.getString("state"));

					statusMessage.setMessage(jsonObject.getString("msg"));

				}

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

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

		StatusMessage statusMessage = statusParse(jsonString);

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

		// parameters.add(new BasicNameValuePair("feedbackInfo.flag",
		// this.studentInfo.getFlag()));

		// parameters.add(new BasicNameValuePair("feedbackInfo.schoolId",
		// this.studentInfo.getSchoolId()));

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

		StatusMessage statusMessage = statusParse(jsonString);

		return statusMessage;
	}

}
