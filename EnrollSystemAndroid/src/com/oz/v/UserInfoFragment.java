package com.oz.v;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.main.v.R;

public class UserInfoFragment extends BasicFragment {

	private View viewContainer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				
		viewContainer =  inflater.inflate(R.layout.fragment_user_info, container, false);
		
		return viewContainer;
	}

	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//设置标题
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\t用户信息");
		//初始化各个控件
		init();
	}

	
	private void init()
	{		
		//实例化各个控件对象
		instanceWeiget();
		//设置各个控件的事件监听器
		setWeigetEvent();	
		//设置控件显示的数据
		setWeigetData();
		
	}
	
	
	
	
	
	private void setWeigetData() {
		
		
	}



	private void setWeigetEvent() {
		
		
	}



	private void instanceWeiget() {
		
		
		
	}



	private ActionBar getActionBar()
	{
		return getActivity().getActionBar();
	}
	
}
