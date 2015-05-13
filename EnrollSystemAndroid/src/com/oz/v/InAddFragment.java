package com.oz.v;

import org.json.JSONException;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.main.m.StatusMessage;
import com.main.v.R;
import com.oz.c.Check;
import com.oz.c.GetProperty;
import com.oz.c.HandlerRequestCode;
import com.oz.c.ListDataSet;
import com.oz.c.PreAdd;
import com.oz.c.RequestCode;
import com.oz.c.StudentInfoView;
import com.oz.c.SubmitStudentInfo;
import com.oz.m.StudentInfo;

public class InAddFragment extends BasicFragment {

	private View viewContainer;
	
	private StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				
		viewContainer =  inflater.inflate(R.layout.fragment_in_add, container, false);
		
		return viewContainer;
		
	}

	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//设为省内默认添加
		studentInfo.setFlag("0");
		//设置标示
		studentInfo.setIdNumber("省内");
		//设置性别
		studentInfo.setSex("男");
		//设置标题
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\t省内添加");
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		//ListDataSet设为空
		ListDataSet.getInstance().setDataSetEmpty();
		//设置默认学校的碎片
		replaceFragment(new AcquiescentSchoolFragment());
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
		
		this.requestThread(RequestCode.PRAPARE_ADD_DATA);
		
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
			
		case CHECK_EXAMINEES:
		{
			
			String examineeNumber = studentInfoView.getEdt_examinees().getText().toString();
			
			if(!examineeNumber.trim().equals("")&&examineeNumber.trim().length() == 14)
			{	
			
			Check check = new Check();
			
			StatusMessage statusMessage = check.check(examineeNumber);
			
			Message msg = new Message();
			
			msg.what = HandlerRequestCode.CHECK_EXAMINEES_RESULT.ordinal();
			
			msg.obj = statusMessage;
			// 1为满足考生号格式
			msg.arg1 = 1;
			
			getHandler().sendMessage(msg);
			
			}
			else
			{
				
				Message msg = new Message();
				
				msg.what = HandlerRequestCode.CHECK_EXAMINEES_RESULT.ordinal();
				// 0为不满足考生号格式
				msg.arg1 = 0;
				
				getHandler().sendMessage(msg);	
							
			}
				
			
			
		}	
			break;
			
		case PRAPARE_ADD_DATA:
		{
				
			
			try {
				
				PreAdd preAdd = new PreAdd();
				
				if(listDataSet.getInMajorList()==null)
				{
					
					listDataSet.setInMajorList(preAdd.getMajor());
					
				}
					
				
				if(listDataSet.getPropertyList()==null)
				{
					
					GetProperty getProperty = new GetProperty();
					
					listDataSet.setPropertyList(getProperty.getProperty());
					
					studentInfo.setProperty(listDataSet.getPropertyList().get(0).getMessage());	
									
				}
				
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}
			break;
			
		case SUBMIT_STUDENT_INFO:
		{
			
			
			
		}

			break;
		default:
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
	
	/*考生号检查*/
	if(HandlerRequestCode.CHECK_EXAMINEES_RESULT.ordinal()==msg.what)
	{
		
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("检查结果");
		
		builder.setPositiveButton("确认", null);
		
		if(msg.arg1 == 1 )
		{
			
			StatusMessage sm = (StatusMessage) msg.obj;
			
			if(!sm.getStatus().equals("1"))
			{
				
				studentInfoView.getEdt_examinees().setBackgroundResource(R.drawable.err_edit);
				
				builder.setMessage(sm.getMessage());
				
				builder.show();
				
			}
			else
			{
				
				studentInfo.setExamineeNumber(
						studentInfoView.getEdt_examinees()
						.getText().toString());
				
			}
						
		}
		else
		{
		
			builder.setMessage("考生号必须为14位数字");
			
			builder.show();
			
		}
			
	}
	
	/*专业选择*/
	if(HandlerRequestCode.MAJOR_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择专业");
		
		String[] data = ListDataSet.getStringArray(this.listDataSet.getInMajorList());
		
		if(data!=null)
		{
				
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				studentInfoView.getEdt_major().setText(
						listDataSet.getInMajorList().get(which).getMajorName());
					
				studentInfo.setAttendProfessional(
						listDataSet.getInMajorList().get(which).getMajorName());
				
			}
		} );
		
		}
		
		builder.show();
		
		return;
	}
	
	
	/*志愿选择*/
	if(HandlerRequestCode.VOLUNTARILY_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择志愿");
		
		if(!listDataSet.getPropertyList().get(0).getMessage().equals("单招"))
		{	
		
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),this.listDataSet.getVolunteerArray());
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				studentInfoView.getEdt_volunteer().
				setText(listDataSet.getVolunteerArray()[which]);
				
				studentInfo.setVoluntarily(
						listDataSet.getVolunteerArray()[which]);
				
			}
		} );
		
		
	}
	else	
	{
		
		studentInfo.setVoluntarily("");
		
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

	
	/*触摸事件*/
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		studentInfo.setExamineeNumber(
				studentInfoView.getEdt_examinees()
				.getText().toString());
		
		studentInfo.setIdCard(
				studentInfoView.getEdt_id()
				.getText().toString());
		
		studentInfo.setStuName(
				studentInfoView.getEdt_stuName()
				.getText().toString());
		
		studentInfo.setStuName(
				studentInfoView.getEdt_stuName()
				.getText().toString());
			
		return false;
	}
	
	
	private void setWeigetEvent() {
		//设置触摸事件
		viewContainer.setOnTouchListener(this);		
		studentInfoView.getEdt_examinees().setOnTouchListener(this);
		studentInfoView.getEdt_id().setOnTouchListener(this);
		studentInfoView.getEdt_major().setOnTouchListener(this);
		studentInfoView.getEdt_stuName().setOnTouchListener(this);
		studentInfoView.getEdt_tel().setOnTouchListener(this);
		studentInfoView.getEdt_volunteer().setOnTouchListener(this);
		studentInfoView.getSchool_type().setOnTouchListener(this);
		studentInfoView.getSex().setOnTouchListener(this);
		
		/*设置焦点事件*/
		studentInfoView.getEdt_examinees().setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				if(!hasFocus)
				{
					
					requestThread(RequestCode.CHECK_EXAMINEES);
					
				}
				else
				{
					
					studentInfoView.getEdt_examinees().setBackgroundResource(R.drawable.edt_selector);
					
				}
			}
		});
		
		
		studentInfoView.getEdt_id().setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				studentInfo.setIdCard(
						studentInfoView.getEdt_id()
						.getText().toString());
				
			}
		});
		
		
		studentInfoView.getEdt_stuName().setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				studentInfo.setStuName(
						studentInfoView.getEdt_stuName()
						.getText().toString());
				
			}
		});
		
		
		studentInfoView.getEdt_tel().setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				studentInfo.setTel(
						studentInfoView.getEdt_tel()
						.getText().toString());	
			
			}
		});
		
		
		studentInfoView.getSchool_type().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch(checkedId)
				{
				case R.id.school_acquiescent:
					
					studentInfo.setFlag("0");
					
					replaceFragment(new AcquiescentSchoolFragment());
					
					break;
					
				case R.id.school_other:
					
					studentInfo.setFlag("1");
					
					replaceFragment(new OtherSchoolFragment());
					
					break;	
					
				}

			}
		});
		
		
		studentInfoView.getSex().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch(checkedId)
				{
				
				case R.id.man:
					
					studentInfo.setSex("男");
					
					break;
					
				case R.id.woman:
					
					studentInfo.setSex("女");
					
					break;
				
				}

			}
		});
		
		
		studentInfoView.getEdt_major().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestHandler(HandlerRequestCode.MAJOR_SELECT);
				
			}
		});
		
		studentInfoView.getEdt_volunteer().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
							
				requestHandler(HandlerRequestCode.VOLUNTARILY_SELECT);	
						
			}
		});
		
		
	}


	private StudentInfoView studentInfoView;

	private void instanceWeiget() {
		
		studentInfoView = 
		new StudentInfoView(
				this.viewContainer.findViewById(R.id.edt_examinees), 
				this.viewContainer.findViewById(R.id.edt_stuName), 
				this.viewContainer.findViewById(R.id.edt_id), 
				this.viewContainer.findViewById(R.id.edt_tel), 
				this.viewContainer.findViewById(R.id.sex), 
				this.viewContainer.findViewById(R.id.edt_major), 
				this.viewContainer.findViewById(R.id.edt_volunteer), 
				this.viewContainer.findViewById(R.id.school_type));
		
		
	}


	

	private ActionBar getActionBar()
	{
		
		return getActivity().getActionBar();
	
	}
	
	
	
	
	private void replaceFragment(BasicFragment fragment) {

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.school_container, fragment);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.commit();

	}
	
		
}
