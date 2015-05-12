package com.oz.bean;

import java.io.Serializable;

public class StatusMessage implements Serializable {

	/**
	 * 公共的状态消息javabean
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	private String message;

	public StatusMessage() {
	}

	public StatusMessage(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
