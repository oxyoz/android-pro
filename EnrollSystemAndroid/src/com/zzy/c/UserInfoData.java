package com.zzy.c;

import com.zzy.m.UserEntity;

public class UserInfoData {

	private static UserEntity user = new UserEntity();

	public static UserEntity getUser() {
		return user;
	}

	public static void setUser(UserEntity user) {
		UserInfoData.user = user;
	}
	

}
