package com.zyp.c;

import com.zyp.m.FeedbackInfo;

public class FeedbackData {

	private static FeedbackInfo infoData = new FeedbackInfo();

	public static FeedbackInfo getInfoData() {
		return infoData;
	}

	public static void setInfoData(FeedbackInfo infoData) {
		FeedbackData.infoData = infoData;
	} 
	
	
	
	
}
