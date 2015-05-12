package com.oz.main.data;

import com.oz.main.bean.User;

public class UserInfo {

	private static User info = new User();

	public static User getInstance() {

		return info;

	}

}
