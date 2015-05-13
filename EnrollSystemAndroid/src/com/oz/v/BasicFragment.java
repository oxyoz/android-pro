package com.oz.v;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;


import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

@SuppressLint("HandlerLeak")
public class BasicFragment extends Fragment implements OnTouchListener,Runnable
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
	public void run() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
