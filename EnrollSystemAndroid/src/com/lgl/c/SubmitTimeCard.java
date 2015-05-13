package com.lgl.c;

public class SubmitTimeCard {

	private String startTime;
	
	private String content;
	
	private String endTime;

	
	private static SubmitTimeCard submitTimeCard;
	
	public static SubmitTimeCard getInstance()
	{
	
		if(submitTimeCard == null)
		{
			
			submitTimeCard = new SubmitTimeCard();
			
		}
	
		return submitTimeCard;
	}
	
	
	private SubmitTimeCard() {
	}
	
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
