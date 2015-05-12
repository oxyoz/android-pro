package com.oz.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class AdminFragment extends BasicFragment {

	private View mContainer;

	@Override
	public void actionReceiver(Context context, Intent intent) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater.inflate(R.layout.fragment_enroll, container,
				false);

		return this.mContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		init();

	}

	/**
	 * init 初始化方法
	 * 
	 * */

	private void init() {
		// 实例化各个控件
		initWidget();
		// 设置各个控件的数据
		updateWidgetAttr();
		// 设置各个控件的事件监听器
		addWidgetEvent();

	}

	private void initWidget() {

	}

	private void updateWidgetAttr() {

	}

	private void addWidgetEvent() {

	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

}
