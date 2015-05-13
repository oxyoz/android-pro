package com.oz.m;

public class StaffSchool  extends Entity{

	private String linkmanTel;
	private String contactId;
	private String schoolName;
	private String linkmanPost;
	private String userId;
	private String remarks;
	private String linkman;
	private String schoolId;
	private String cid;
	private InputTime inputTime;
	
	public StaffSchool() {}
	
	public StaffSchool(String linkmanTel, String contactId, String schoolName,
			String linkmanPost, String userId, String remarks, String linkman,
			String schoolId, String cid, InputTime inputTime) {
		super();
		this.linkmanTel = linkmanTel;
		this.contactId = contactId;
		this.schoolName = schoolName;
		this.linkmanPost = linkmanPost;
		this.userId = userId;
		this.remarks = remarks;
		this.linkman = linkman;
		this.schoolId = schoolId;
		this.cid = cid;
		this.inputTime = inputTime;
	}

	public String getLinkmanTel() {
		return linkmanTel;
	}

	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getLinkmanPost() {
		return linkmanPost;
	}

	public void setLinkmanPost(String linkmanPost) {
		this.linkmanPost = linkmanPost;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
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

	public InputTime getInputTime() {
		return inputTime;
	}

	public void setInputTime(InputTime inputTime) {
		this.inputTime = inputTime;
	}
	
	
	
}
