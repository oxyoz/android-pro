package com.oz.main.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.oz.config.API;
import com.oz.tools.net.NetworkUtil;

public class LoginInterfaceImplDao implements LoginInterfaceDao {

	@Override
	public String checkUserInfo(String userName, String userPwd) {

		return check(userName, userPwd);
	}

	private String check(String userName, String userPwd) {

//		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
//
//		parameters.add(new BasicNameValuePair("userInfo.userName", userName));
//
//		parameters.add(new BasicNameValuePair("userInfo.password", userPwd));

//		String url = API.login.login_api();

		String url = API.login.login_api(userName,userPwd);
		
//		return NetworkUtil.postResponseData(url, parameters);
		
		String strTemp = NetworkUtil.getResponseData(url);
		
		return strTemp;
	}

}
