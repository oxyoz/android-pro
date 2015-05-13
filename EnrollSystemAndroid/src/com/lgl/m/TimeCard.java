package com.lgl.m;

public class TimeCard {

	private Time startTime;
	private String remark;
	private String year;
	private String tid;
	private String contact;
	private Time endTime;

	public TimeCard() {
	}

	public TimeCard(Time startTime, String remark, String year, String tid,
			String contact, Time endTime) {
		super();
		this.startTime = startTime;
		this.remark = remark;
		this.year = year;
		this.tid = tid;
		this.contact = contact;
		this.endTime = endTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

}

