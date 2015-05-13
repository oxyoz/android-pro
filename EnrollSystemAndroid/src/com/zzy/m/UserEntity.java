package com.zzy.m;

public class UserEntity {
    private String sex;
    private String isUsed;
    private String post;
    private String email;
    private String userProperty;
    private String tel;
    private String userId;
    private String userName;
    private String number;
    private String businessId;
    private String password;
	public UserEntity() {
		super();
	}
	public UserEntity(String sex, String isUsed, String post, String email,
			String userProperty, String tel, String userId, String userName,
			String number, String businessId, String password) {
		super();
		this.sex = sex;
		this.isUsed = isUsed;
		this.post = post;
		this.email = email;
		this.userProperty = userProperty;
		this.tel = tel;
		this.userId = userId;
		this.userName = userName;
		this.number = number;
		this.businessId = businessId;
		this.password = password;
	}
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserProperty() {
		return userProperty;
	}
	public void setUserProperty(String userProperty) {
		this.userProperty = userProperty;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    

	
    
    
    
}
