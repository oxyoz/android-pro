package com.oz.activity;

import com.oz.fragment.BasicFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * @author oz
 * 
 *         BasicActivity������ʵ����OnClickListener��Runnable�ӿںͷ�װ��Handlerʵ������
 *         ����������ֱ�ӷ���getHandler()������ȡHandlerʵ������дhandlerMethod(Message msg)����
 *         �ڸ÷����п�д����Ҫ�첽���������
 * 
 */

@SuppressLint("HandlerLeak")
public class BasicActivity extends FragmentActivity implements OnClickListener,
		Runnable {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// ����ʾ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	private Handler handle = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			handlerMethod(msg);

		}

	};

	// Handler(�첽��Ϣ����)������
	public void handlerMethod(Message msg) {
		// ��Ҫ����Ĵ���
	}

	// �����ṩ��ȡHandlerʵ���ķ�����ͨ���÷����ɻ��Handler����
	public Handler getHandler() {
		return this.handle;
	}

	// �̴߳�����
	@Override
	public void run() {
		// ��Ҫ����Ĵ���
	}

	// �����¼�������
	@Override
	public void onClick(View v) {
		// ��Ҫ����Ĵ���
	}

	
	@SuppressLint("CommitTransaction") 
	public void replaceFragment(int containerViewId,BasicFragment fragment) {

		FragmentManager fragmentManager = this.getSupportFragmentManager();

		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fragmentTransaction.add(containerViewId, fragment);
				
		fragmentTransaction.commit();
		
//		Toast.makeText(this, "BackStack="+fragmentManager.getBackStackEntryCount(), Toast.LENGTH_LONG).show();
		
	}
	
}
