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
	
	
	//Handler(�첽��Ϣ����)������
	public void handlerMethod(Message msg)
	{
	//��Ҫ����Ĵ���		
	}
	
	
	
	//�����ṩ��ȡHandlerʵ���ķ�����ͨ���÷����ɻ��Handler����
	public Handler getHandler()
	{
		return this.handle;
	}
	
	
	
	
	//�̴߳�����
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
