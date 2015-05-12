package com.oz.enroll.data;

import com.oz.bean.StatusMessage;

public class SubmitResult {

	private static StatusMessage submitResult = null;

	public static StatusMessage getSubmitResult() {
		return submitResult;
	}

	public static void setSubmitResult(StatusMessage checkResult) {
		SubmitResult.submitResult = checkResult;
	} 
	
}
