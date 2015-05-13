package com.main.v;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;



/**
 * @author oz
 * 
 *BasicActivity类额外的实现了OnClickListener、Runnable接口和封装了Handler实例对象
 *该类的子类可直接访问getHandler()方法获取Handler实例，重写handlerMethod(Message msg)方法
 *在该方法中可写入需要异步处理的内容
 *
 */

@SuppressLint("HandlerLeak")
public class BasicActivity extends Activity implements OnClickListener,Runnable 
{
	
		
	private Handler handle = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			handlerMethod(msg);
			
		}
		
	}; 
	
	
	//Handler(异步消息处理)处理方法
	public void handlerMethod(Message msg)
	{
	//需要处理的代码		
	}
	
	
	
	//对外提供获取Handler实力的方法，通过该方法可获得Handler对象
	public Handler getHandler()
	{
		return this.handle;
	}
	
	
	//线程处理方法
	@Override
	public void run()
	{		
		//需要处理的代码			
	}

	
	//单击事件处理方法
	@Override
	public void onClick(View v) 
	{
		//需要处理的代码	
	}

	
}
