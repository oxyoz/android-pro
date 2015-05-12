package com.oz.fragment;

import com.oz.main.R;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public abstract class BasicFragment extends Fragment implements
		OnClickListener, Runnable {

	private Handler handle = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			handlerMethod(msg);

		}

	};

	// Handler(异步消息处理)处理方法
	public void handlerMethod(Message msg) {
		// 需要处理的代码
	}

	// 对外提供获取Handler实力的方法，通过该方法可获得Handler对象
	public Handler getHandler() {
		return this.handle;
	}

	// 线程处理方法
	@Override
	public void run() {
		// 需要处理的代码
	}

	// 单击事件处理方法
	@Override
	public void onClick(View v) {
		// 需要处理的代码
	}

	@SuppressLint("CommitTransaction")
	public void replaceFragment(int containerViewId, BasicFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();

		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fragmentTransaction.replace(containerViewId, fragment);

		fragmentTransaction.addToBackStack(fragment.getClass().getName());

		fragmentTransaction.commit();

		// Toast.makeText(this.getActivity(),
		// "BackStack="+fragmentManager.getBackStackEntryCount(),
		// Toast.LENGTH_LONG).show();
	}

	private ActionReceiver receiver;

	@Override
	public void onStart() {

		super.onStart();

		IntentFilter filter = new IntentFilter(this.getClass().getName());

		receiver = new ActionReceiver();

		getActivity().registerReceiver(receiver, filter);

	}

	@Override
	public void onStop() {

		super.onStop();

		getActivity().unregisterReceiver(receiver);

	}

	public abstract void actionReceiver(Context context, Intent intent);

	@SuppressWarnings("unused")
	private class ActionReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			actionReceiver(context, intent);

		}

	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {

		return enter ? AnimationUtils.loadAnimation(getActivity(),
				R.anim.fragment_input) : AnimationUtils.loadAnimation(
				getActivity(), R.anim.fragment_output);
	}

}
