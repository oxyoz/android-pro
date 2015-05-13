package com.oz.v;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.main.v.R;
import com.oz.c.HandlerRequestCode;
import com.oz.c.ListDataSet;
import com.oz.c.PreAdd;
import com.oz.c.RequestCode;
import com.oz.c.SubmitStudentInfo;
import com.oz.m.StudentInfo;

public class AcquiescentSchoolFragment extends BasicFragment {

	private View viewContainer;
	
	private StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		viewContainer = inflater.inflate(R.layout.fragment_acquiescent_school, container, false);
		
		return viewContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		//设为默认添加
		studentInfo.setFlag("0");
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
		
		this.sp_schoolId.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestThread(RequestCode.GET_SCHOOL);
				//requestHandler(HandlerRequestCode.SCHOOL_SELECT);
			}
		});		
	}

	
	private TextView sp_schoolId;
	
	private void instanceWeiget() {
	
		this.sp_schoolId = (TextView) this.viewContainer.findViewById(R.id.sp_schoolId);
				
	}




	//多线程事务处理请求代码
	private  RequestCode requestCode = RequestCode.ACQUIESCE;
		
	//数据集合
	ListDataSet listDataSet = ListDataSet.getInstance();
	
	@Override
	public void run() {

		switch(this.requestCode)
		{
		
		case ACQUIESCE:
		{
			
			
		}
			break;		
			
		case GET_SCHOOL:
		{
			
			
			if(listDataSet.getStaffSchoolList() == null)
			{
					
				try {
					
					PreAdd preAdd = new PreAdd();
					
					listDataSet.setStaffSchoolList(preAdd.getStaffSchool());
				
					
				} catch (JSONException e) {
					
					e.printStackTrace();
					
				}
				
			}
						
			requestHandler(HandlerRequestCode.SCHOOL_SELECT);
			
		}
			break;				
			
		}
		
	}
	
	
	
	//请求开启多线程
	private void requestThread(RequestCode requestCode)
	{
		
		//设置请求代码
		this.requestCode = requestCode;
		//开启请求处理线程
		new Thread(this).start();
		
		
	}
	
	
	
	//请求异步处理
	private void requestHandler(HandlerRequestCode handlerRequestCode)
	{
		
		Message msg = new Message();
		//设置请求代码
		msg.what = handlerRequestCode.ordinal();
		//发送异步消息
		getHandler().sendMessage(msg);
		

	}
	
	
	//UI更新方法
	@Override
	public void handlerMethod(Message msg) {

	/*默认处理*/
	if(HandlerRequestCode.ACQUIESCE.ordinal()==msg.what)
	{
		
		
		
		return;
	}
			
	
	/*学校选择*/
	if(HandlerRequestCode.SCHOOL_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择学校");
		
		String[] data = ListDataSet.getStringArray(this.listDataSet.getStaffSchoolList());
		
		if(data != null)
		{
				
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
								
				sp_schoolId.setText(
						listDataSet.getStaffSchoolList().get(which).getSchoolName());
				
				studentInfo.setSchoolId(
						listDataSet.getStaffSchoolList().get(which).getSchoolId());
				
			}
		} );
		
		}
		
		builder.show();
		
		
		
		return;
	}
	
	
	/*设置控件数据*/
	if(HandlerRequestCode.SET_WEIGET_DATA.ordinal()==msg.what)
	{
	
		
		
		
		return;
	}
	
	
	}

	
	
}
