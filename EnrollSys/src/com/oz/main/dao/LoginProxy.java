package com.oz.main.dao;

import android.util.Log;

import com.oz.main.data.UserInfo;

public class LoginProxy implements LoginInterfaceDao {

	public final static String TYPE_ADMIN = "XLAdministrator";

	public final static String TYPE_TEACHER = "XLEnrollPerson";

	private LoginInterfaceImplDao mLoginInterfaceImplDao;

	public LoginProxy() {

		mLoginInterfaceImplDao = new LoginInterfaceImplDao();

	}

	@Override
	public String checkUserInfo(String userName, String userPwd) {

		return mLoginInterfaceImplDao.checkUserInfo(userName, userPwd);
	}

	public String login(String userName, String userPwd) {

		String checkResult = checkUserInfo(userName, userPwd);

		if (LoginProxy.TYPE_ADMIN.equals(checkResult)) {

			saveUserInfo(userName, userPwd, "admin");

		} else if (LoginProxy.TYPE_TEACHER.equals(checkResult)) {

			saveUserInfo(userName, userPwd, "非专职招生人员");

		}
		Log.i("loginproxy", "run login...");
		return checkResult;
	}

	private void saveUserInfo(String userName, String userPwd, String property) {

		UserInfo.getInstance().setName(userName);

		UserInfo.getInstance().setProperty(property);

		UserInfo.getInstance().setPwd(userPwd);

	}

}
