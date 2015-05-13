package com.oz.v;

import java.util.List;

import org.json.JSONException;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.main.v.R;
import com.oz.c.FindItAll;
import com.oz.m.ItAll;

public class AddedStudentInfoFragment extends BasicFragment {
	
	private View viewContainer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				
		viewContainer = inflater.inflate(R.layout.fragment_added_student_info, container, false);
		
		return viewContainer;
		
	}

	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//设置标题
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\t学生信息");
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
			
		new Thread(this).start();
		
	}


	@Override
	public void run() {

		
		
		try {
			
			FindItAll itAll = new FindItAll(); 
			
			List<ItAll> data = itAll.finditall();
			
			Message msg = new Message();
			
			msg.what = SET_WEIGET_DATA;
			
			msg.obj = data;
			
			getHandler().sendMessage(msg);
			
			Log.i(getClass().getName()+"thread run", "获取所有生源信息");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private final int SET_WEIGET_DATA = 0x1;
	
	@Override
	public void handlerMethod(Message msg) {

		switch(msg.what)
		{
		
		case SET_WEIGET_DATA:
			
			List<ItAll> data = (List<ItAll>) msg.obj;
			
			StudentInfoAdpater adapter = new StudentInfoAdpater(getActivity(), data);
			
			this.studentInfoList.setAdapter(adapter);
			
			break;
		
		}
		
		
		
		
	}
	
	

	private void setWeigetEvent() {
		
		
	}


	private ListView studentInfoList;

	private void instanceWeiget() {
		
		studentInfoList = (ListView) this.viewContainer.findViewById(R.id.list_student_info);
		
	}
	
	
	private ActionBar getActionBar()
	{
		return getActivity().getActionBar();
	}
	
	
}
