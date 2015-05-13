package com.oz.m;

public class Major  extends Entity{

	
	private String isUsed;
	private String mid;
	private String majorCode;
	private String majorName;
	
	public Major() {}
	
	public Major(String isUsed, String mid, String majorCode, String majorName) {
		super();
		this.isUsed = isUsed;
		this.mid = mid;
		this.majorCode = majorCode;
		this.majorName = majorName;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	
	
}
