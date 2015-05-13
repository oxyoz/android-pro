package com.oz.v;

import org.json.JSONException;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.main.m.StatusMessage;
import com.main.v.R;
import com.oz.c.AddressId;
import com.oz.c.Check;
import com.oz.c.GetProperty;
import com.oz.c.HandlerRequestCode;
import com.oz.c.ListDataSet;
import com.oz.c.PreAddOutsize;
import com.oz.c.RequestCode;
import com.oz.c.SearchSchool;
import com.oz.c.StudentInfoView;
import com.oz.c.SubmitStudentInfo;
import com.oz.m.StudentInfo;

public class OutAddFragment extends BasicFragment{

	private View viewContainer;
	
	private StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				
		viewContainer = inflater.inflate(R.layout.fragment_out_add, container, false);
		
		return viewContainer;
	}

	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//设置省外添加
		studentInfo.setFlag("2");
		//设置标示
		studentInfo.setIdNumber("省外");
		//设置性别
		studentInfo.setSex("男");
		//设置标题
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\t省外添加");
		//ListDataSet设为空
		ListDataSet.getInstance().setDataSetEmpty();
		//AddressId设为空
		AddressId.getInstance().setAddressIdEmpty();
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


	/*触摸事件*/
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		studentInfo.setSchoolName(
				studentInfoView.getEdt_schoolName()
				.getText().toString());
		
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
		studentInfoView.getEdt_county().setOnTouchListener(this);
		studentInfoView.getEdt_id().setOnTouchListener(this);
		studentInfoView.getEdt_major().setOnTouchListener(this);
		studentInfoView.getEdt_netherlands().setOnTouchListener(this);
		studentInfoView.getEdt_province().setOnTouchListener(this);
		studentInfoView.getEdt_schoolName().setOnTouchListener(this);
		studentInfoView.getEdt_stuName().setOnTouchListener(this);
		studentInfoView.getEdt_tel().setOnTouchListener(this);
		studentInfoView.getEdt_volunteer().setOnTouchListener(this);
		studentInfoView.getSex().setOnTouchListener(this);

		
		//设置焦点事件
		studentInfoView.getEdt_schoolName().setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				studentInfo.setSchoolName(
						studentInfoView.getEdt_schoolName()
						.getText().toString());
				
			}
		});
		
		
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
		
		
		this.studentInfoView.getSex().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
		
			
		this.studentInfoView.getEdt_major().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestHandler(HandlerRequestCode.MAJOR_SELECT);
				
			}
		});
		
		this.studentInfoView.getEdt_volunteer().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestHandler(HandlerRequestCode.VOLUNTARILY_SELECT);
				
			}
		});
		
		
		this.studentInfoView.getEdt_province().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestThread(RequestCode.GET_PROVINCE);
				//requestHandler(HandlerRequestCode.PROVINCE_SELECT);
				
			}
		});
		
		
		this.studentInfoView.getEdt_netherlands().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestThread(RequestCode.GET_NETHERLANDS);
				//requestHandler(HandlerRequestCode.NETHERLANDS_SELECT);
				
			}
		});
		
		
		this.studentInfoView.getEdt_county().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestThread(RequestCode.GET_COUNTY);
				//requestHandler(HandlerRequestCode.COUNTY_SELECT);
				
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
				this.viewContainer.findViewById(R.id.edt_province), 
				this.viewContainer.findViewById(R.id.edt_netherlands),
				this.viewContainer.findViewById(R.id.edt_county),
				this.viewContainer.findViewById(R.id.edt_schoolName));
			
	}



	//多线程事务处理请求代码
	private  RequestCode requestCode = RequestCode.ACQUIESCE;
		
	//数据集合
	private ListDataSet listDataSet = ListDataSet.getInstance();
		
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
			
		case GET_COUNTY:
		{
			
			if(this.addressId.getnId() != null)
			{
							
				try {
					
					SearchSchool searchSchool = new SearchSchool();
					
					this.listDataSet.setCountyList(searchSchool.getCounty(this.addressId.getnId()));
					
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
			}
			
			requestHandler(HandlerRequestCode.COUNTY_SELECT);
			
		}
		
			break;	
			
			
		case GET_PROVINCE:
		{
			
			if(this.listDataSet.getPrivnceList() == null)
			{
							
				try {
					
					SearchSchool searchSchool = new SearchSchool();
					
					Log.i("debug", "debug_0");
					this.listDataSet.setPrivnceList(searchSchool.getPrivnce());
						
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
			}
			
			requestHandler(HandlerRequestCode.PROVINCE_SELECT);
			
		}
			break;
			
		case GET_NETHERLANDS:
		{
			
			if(this.addressId.getpId()!=null){
							
				try {
					
					SearchSchool searchSchool = new SearchSchool();
					
					this.listDataSet.setNetherlandsList(searchSchool.getNetherlands(this.addressId.getpId()));
				
										
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
			
			requestHandler(HandlerRequestCode.NETHERLANDS_SELECT);
			
		}
			break;		
			
			
			
		case PRAPARE_ADD_DATA:
		{
				
			
			try {
				
				if(listDataSet.getOutMajorList()==null)
				{
					PreAddOutsize preAddOutsize = new PreAddOutsize();
					
					listDataSet.setOutMajorList(preAddOutsize.getMajor());
					
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
	
	
	
	//AddressId中有省id、地区id、城市id、学校id
	private AddressId addressId = AddressId.getInstance();
	
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
	
	
	/*城市选择*/
	if(HandlerRequestCode.COUNTY_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择城市");
		
		String[] data = ListDataSet.getStringArray(this.listDataSet.getCountyList());
		
		if(data != null)
		{
				
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				studentInfoView.getEdt_county().setText(
						listDataSet.getCountyList().get(which).getCname());
				
				addressId.setcId(
						listDataSet.getCountyList().get(which).getCid());
			
				studentInfo.setCounty(listDataSet.getCountyList().get(which).getCid());
				
			}
		} );
		
		}
		
		builder.show();
		
		
		return;
	}
	
	/*专业选择*/
	if(HandlerRequestCode.MAJOR_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择专业");
		
		String[] data = ListDataSet.getStringArray(this.listDataSet.getOutMajorList());
		
		if(data != null)
		{
				
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				studentInfoView.getEdt_major().setText(
						listDataSet.getOutMajorList().get(which).getMajorName());
				
				studentInfo.setAttendProfessional(listDataSet.getOutMajorList().get(which).getMajorName());
				
			}
		} );
		
		}
		
		builder.show();
		
		
		return;
	}
	
	
	/*地区选择*/
	if(HandlerRequestCode.NETHERLANDS_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择地区");
		
		String[] data = ListDataSet.getStringArray(this.listDataSet.getNetherlandsList());
		
		if(data != null)
		{
				
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
				studentInfoView.getEdt_netherlands().setText(
						listDataSet.getNetherlandsList().get(which).getNname());
				
				addressId.setnId(
						listDataSet.getNetherlandsList().get(which).getNid());
				
				studentInfo.setNetherlands(listDataSet.getNetherlandsList().get(which).getNid());
				
			}
		} );
		
		}
		
		builder.show();
		
		
		return;
	}
	
	/*省选择*/
	if(HandlerRequestCode.PROVINCE_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择省");
		
		String[] data = ListDataSet.getStringArray(this.listDataSet.getPrivnceList());
		
		if(data != null)
		{
				
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
				studentInfoView.getEdt_province().setText(
						listDataSet.getPrivnceList().get(which).getPname());
				
				addressId.setpId(
						listDataSet.getPrivnceList().get(which).getPid());
				
				studentInfo.setProvince(listDataSet.getPrivnceList().get(which).getPid());
				
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
				
				studentInfo.setVoluntarily(listDataSet.getVolunteerArray()[which]);
				
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

	
	private ActionBar getActionBar()
	{
		
		return getActivity().getActionBar();
		
	}
	
	
	
	
}
