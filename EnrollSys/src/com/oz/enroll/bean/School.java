package com.oz.enroll.bean;

public class School  extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String schoolName;
	
	private String schoolCode;
	
	private String schoolId;
	
	private String cid;
	
	public School() {}

	public School(String schoolName, String schoolCode, String schoolId,
			String cid) {
		super();
		this.schoolName = schoolName;
		this.schoolCode = schoolCode;
		this.schoolId = schoolId;
		this.cid = cid;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Override
	public String getItem() {
		// TODO Auto-generated method stub
		return schoolName;
	}
	
	
	
}
