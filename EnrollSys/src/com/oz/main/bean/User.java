package com.oz.main.bean;

public class User {

	private String name;

	private String pwd;

	private String property;

	public User(String name, String pwd, String property) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.property = property;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public User() {

	}

	public User(String name, String property) {
		super();
		this.name = name;
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
