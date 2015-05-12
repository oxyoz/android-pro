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
 * 访问网络工具类
 * 
 * @author oz
 * 
 */
public class NetworkUtil {

private static String JSESESSID = null;// 静态变量用来保存jspsessid的值
	
	private static DefaultHttpClient httpClient = null;
	
	public synchronized static String getResponseData(String url) 
	{
		
		Log.i("putJsonString()", "run putJsonString()......");
		Log.i("debug", "debug_1_4_1_0");
		// 获得HttpPost实例
		HttpGet request = new HttpGet(url);
		Log.i("debug", "debug_1_4_1_1");
		System.out.println(request.getURI());
		
		String result = null;
		
		if (null != JSESESSID) 
		{
			Log.i("debug", "debug_1_4_1_1");
			// 判断sessionid是否为空，不为空将就将jspsessid的值放在cookie中发送给服务器			
			request.setHeader("Cookie", "JSESESSID=" + JSESESSID);
		}
		
		Log.i("debug", "debug_1_4_1_2");
		// 实例化连接
		if(httpClient == null)
		{
			Log.i("debug", "debug_1_4_1_3");
		httpClient = new DefaultHttpClient();
		
		}
		
		Log.i("debug", "debug_1_4_1_4");
		
		try {
			
			Log.i("debug", "debug_1_4_1_5");
			// 获得HttpResponse实例
			HttpResponse response = httpClient.execute(request);
			Log.i("debug", "debug_1_4_1_6");
			if (response.getStatusLine().getStatusCode() == 200) {
				Log.i("debug", "debug_1_4_1_7");
				result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				Log.i("debug", "debug_1_4_1_8");
				CookieStore mCookieStore = httpClient.getCookieStore();// 取得cookiestore
				Log.i("debug", "debug_1_4_1_9");
				List<Cookie> list = mCookieStore.getCookies();
				Log.i("debug", "debug_1_4_1_10");
				for (int i = 0; i < list.size(); i++) {
					Log.i("debug", "debug_1_4_1_11");
					// 读取Cookie['JSPSESSID']的值存在静态变量中，保证每次都是同一个值
					
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
		
			result = "网络异常";

		}
		catch (IOException e) 
		{
			
			e.printStackTrace();
			
			result = "网络异常";

		}
		
		Log.i("网络", result + "......");
		
			
		return null;
	}
	 
	
	
	public synchronized static String postResponseData(String url,List<NameValuePair> parameters)
	{
		// 获得HttpPost实例
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
			// 判断sessionid是否为空，不为空将就将jspsessid的值放在cookie中发送给服务器
			request.setHeader("Cookie", "JSPSESSID=" + JSESESSID);
		}
		// 实例化连接
//		DefaultHttpClient httpClient = new DefaultHttpClient();
		if(httpClient == null)
		{
		// 实例化连接
		 httpClient = new DefaultHttpClient();

		}
		try {
			// 获得HttpResponse实例
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				CookieStore mCookieStore = httpClient.getCookieStore();// 取得cookiestore
				List<Cookie> list = mCookieStore.getCookies();
				for (int i = 0; i < list.size(); i++) {
					// 读取Cookie['JSPSESSID']的值存在静态变量中，保证每次都是同一个值
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
			result = "网络异常";

		} catch (IOException e) {
			
			e.printStackTrace();
			result = "网络异常";

		}
		
		Log.i("网络", result + "......");
					
		return null;
	}
	 
	 

}
