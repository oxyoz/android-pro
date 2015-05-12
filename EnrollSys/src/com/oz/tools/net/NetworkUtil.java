package com.oz.tools.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * 
 * �������繤����
 * 
 * @author oz
 * 
 */
public class NetworkUtil {

private static String JSESESSID = null;// ��̬������������jspsessid��ֵ
	
	private static DefaultHttpClient httpClient = null;
	
	public synchronized static String getResponseData(String url) 
	{
		
		Log.i("putJsonString()", "run putJsonString()......");
		Log.i("debug", "debug_1_4_1_0");
		// ���HttpPostʵ��
		HttpGet request = new HttpGet(url);
		Log.i("debug", "debug_1_4_1_1");
		System.out.println(request.getURI());
		
		String result = null;
		
		if (null != JSESESSID) 
		{
			Log.i("debug", "debug_1_4_1_1");
			// �ж�sessionid�Ƿ�Ϊ�գ���Ϊ�ս��ͽ�jspsessid��ֵ����cookie�з��͸�������			
			request.setHeader("Cookie", "JSESESSID=" + JSESESSID);
		}
		
		Log.i("debug", "debug_1_4_1_2");
		// ʵ��������
		if(httpClient == null)
		{
			Log.i("debug", "debug_1_4_1_3");
		httpClient = new DefaultHttpClient();
		
		}
		
		Log.i("debug", "debug_1_4_1_4");
		
		try {
			
			Log.i("debug", "debug_1_4_1_5");
			// ���HttpResponseʵ��
			HttpResponse response = httpClient.execute(request);
			Log.i("debug", "debug_1_4_1_6");
			if (response.getStatusLine().getStatusCode() == 200) {
				Log.i("debug", "debug_1_4_1_7");
				result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				Log.i("debug", "debug_1_4_1_8");
				CookieStore mCookieStore = httpClient.getCookieStore();// ȡ��cookiestore
				Log.i("debug", "debug_1_4_1_9");
				List<Cookie> list = mCookieStore.getCookies();
				Log.i("debug", "debug_1_4_1_10");
				for (int i = 0; i < list.size(); i++) {
					Log.i("debug", "debug_1_4_1_11");
					// ��ȡCookie['JSPSESSID']��ֵ���ھ�̬�����У���֤ÿ�ζ���ͬһ��ֵ
					
					if ("JSPSESSID".equals(list.get(i).getName())) 
					{
						Log.i("debug", "debug_1_4_1_12");
						JSESESSID = list.get(i).getValue();
					
					}
					
					Log.i("debug", "debug_1_4_1_13");
				}
				
				Log.i("JsonString_Data", result);
				
				return result;
			
			}
		
		} 
		catch (ClientProtocolException e) 
		{
		
			e.printStackTrace();
		
			result = "�����쳣";

		}
		catch (IOException e) 
		{
			
			e.printStackTrace();
			
			result = "�����쳣";

		}
		
		Log.i("����", result + "......");
		
			
		return null;
	}
	 
	
	
	public synchronized static String postResponseData(String url,List<NameValuePair> parameters)
	{
		// ���HttpPostʵ��
		HttpPost request = new HttpPost(url);	

		// System.out.println(request.getURI());
		
		try {
			
			request.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
			
			Log.i("seted...", "seted parameters......");
			
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
			
		}
	
		
		String result = null;
		if (null != JSESESSID) {
			// �ж�sessionid�Ƿ�Ϊ�գ���Ϊ�ս��ͽ�jspsessid��ֵ����cookie�з��͸�������
			request.setHeader("Cookie", "JSPSESSID=" + JSESESSID);
		}
		// ʵ��������
//		DefaultHttpClient httpClient = new DefaultHttpClient();
		if(httpClient == null)
		{
		// ʵ��������
		 httpClient = new DefaultHttpClient();

		}
		try {
			// ���HttpResponseʵ��
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				CookieStore mCookieStore = httpClient.getCookieStore();// ȡ��cookiestore
				List<Cookie> list = mCookieStore.getCookies();
				for (int i = 0; i < list.size(); i++) {
					// ��ȡCookie['JSPSESSID']��ֵ���ھ�̬�����У���֤ÿ�ζ���ͬһ��ֵ
					if ("JSPSESSID".equals(list.get(i).getName())) {
						
						JSESESSID = list.get(i).getValue();
					
					}
					
					Log.i("--------->>>>>>", list.get(i).getValue());
				}
				
				Log.i("JsonString_Data", result);
				return result;
			}
			
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
			result = "�����쳣";

		} catch (IOException e) {
			
			e.printStackTrace();
			result = "�����쳣";

		}
		
		Log.i("����", result + "......");
					
		return null;
	}
	 
	 

}
