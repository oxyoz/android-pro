package com.oz.c;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class StudentInfoView {

	private EditText edt_examinees;
	
	private EditText edt_stuName;
	
	private EditText edt_id;
	
	private EditText edt_tel;
	
	private RadioGroup sex;
	
	private TextView edt_major;
	
	private TextView edt_volunteer;
	
	private RadioGroup school_type;

	private TextView edt_province;
	
	private TextView edt_netherlands;
	
	private TextView edt_county;
	
	private EditText edt_schoolName;
	
	/*省内添加*/
	public StudentInfoView(View edt_examinees, View edt_stuName,
			View edt_id, View edt_tel, View sex,
			View edt_major, View edt_volunteer, View school_type) {
		super();
		this.edt_examinees = (EditText) edt_examinees;
		this.edt_stuName = (EditText) edt_stuName;
		this.edt_id = (EditText) edt_id;
		this.edt_tel = (EditText) edt_tel;
		this.sex = (RadioGroup) sex;
		this.edt_major = (TextView) edt_major;
		this.edt_volunteer = (TextView) edt_volunteer;
		this.school_type = (RadioGroup) school_type;
	}
	
	
	
	/*省外添加*/
	public StudentInfoView(View edt_examinees, View edt_stuName,
			View edt_id, View edt_tel, View sex,
			View edt_major, View edt_volunteer, View edt_province,
			View edt_netherlands, View edt_county,
			View edt_schoolName) {
		super();
		this.edt_examinees = (EditText) edt_examinees;
		this.edt_stuName = (EditText) edt_stuName;
		this.edt_id = (EditText) edt_id;
		this.edt_tel = (EditText) edt_tel;
		this.sex = (RadioGroup) sex;
		this.edt_major = (TextView) edt_major;
		this.edt_volunteer = (TextView) edt_volunteer;
		this.edt_province = (TextView) edt_province;
		this.edt_netherlands = (TextView) edt_netherlands;
		this.edt_county = (TextView) edt_county;
		this.edt_schoolName = (EditText) edt_schoolName;
	}







	public StudentInfoView() {
	}

	
	
	

	public TextView getEdt_province() {
		return edt_province;
	}







	public void setEdt_province(TextView edt_province) {
		this.edt_province = edt_province;
	}







	public TextView getEdt_netherlands() {
		return edt_netherlands;
	}







	public void setEdt_netherlands(TextView edt_netherlands) {
		this.edt_netherlands = edt_netherlands;
	}







	public TextView getEdt_county() {
		return edt_county;
	}







	public void setEdt_county(TextView edt_county) {
		this.edt_county = edt_county;
	}







	public EditText getEdt_schoolName() {
		return edt_schoolName;
	}







	public void setEdt_schoolName(EditText edt_schoolName) {
		this.edt_schoolName = edt_schoolName;
	}







	public EditText getEdt_examinees() {
		return edt_examinees;
	}


	public void setEdt_examinees(EditText edt_examinees) {
		this.edt_examinees = edt_examinees;
	}


	public EditText getEdt_stuName() {
		return edt_stuName;
	}


	public void setEdt_stuName(EditText edt_stuName) {
		this.edt_stuName = edt_stuName;
	}


	public EditText getEdt_id() {
		return edt_id;
	}


	public void setEdt_id(EditText edt_id) {
		this.edt_id = edt_id;
	}


	public EditText getEdt_tel() {
		return edt_tel;
	}


	public void setEdt_tel(EditText edt_tel) {
		this.edt_tel = edt_tel;
	}


	public RadioGroup getSex() {
		return sex;
	}


	public void setSex(RadioGroup sex) {
		this.sex = sex;
	}


	public TextView getEdt_major() {
		return edt_major;
	}


	public void setEdt_major(TextView edt_major) {
		this.edt_major = edt_major;
	}


	public TextView getEdt_volunteer() {
		return edt_volunteer;
	}


	public void setEdt_volunteer(TextView edt_volunteer) {
		this.edt_volunteer = edt_volunteer;
	}


	public RadioGroup getSchool_type() {
		return school_type;
	}


	public void setSchool_type(RadioGroup school_type) {
		this.school_type = school_type;
	}
	
	
	
	
}
