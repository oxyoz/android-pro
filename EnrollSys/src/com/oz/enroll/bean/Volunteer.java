package com.oz.enroll.bean;

public class Volunteer extends Entity {

	private String volunteer;
	
	
	
	public Volunteer(String volunteer) {
		super();
		this.volunteer = volunteer;
	}



	public String getVolunteer() {
		return volunteer;
	}



	public void setVolunteer(String volunteer) {
		this.volunteer = volunteer;
	}



	@Override
	public String getItem() {
		
		return volunteer;
	}

	
	
}
