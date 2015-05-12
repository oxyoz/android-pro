package com.oz.enroll.data;

import com.oz.bean.StatusMessage;

public class CheckResult {

	private static StatusMessage checkResult = null;

	public static StatusMessage getCheckResult() {
		return checkResult;
	}

	public static void setCheckResult(StatusMessage checkResult) {
		CheckResult.checkResult = checkResult;
	} 
		
}
