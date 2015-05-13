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

import com.main.m.StatusMessage;
import com.main.v.R;
import com.oz.c.AddressId;
import com.oz.c.Check;
import com.oz.c.HandlerRequestCode;
import com.oz.c.ListDataSet;
import com.oz.c.RequestCode;
import com.oz.c.SearchSchool;
import com.oz.c.SubmitStudentInfo;
import com.oz.m.StudentInfo;

public class OtherSchoolFragment extends BasicFragment {

	
	private View viewContainer;
	
	private StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		viewContainer = inflater.inflate(R.layout.fragment_other_school, container, false);
		
		return viewContainer;
	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		//设为其他添加
		studentInfo.setFlag("1");
		//设置省
		studentInfo.setProvince("18");		
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
	
		
	}

	private void setWeigetEvent() {
	
		this.edt_province.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
			}
		});
		
		
		this.edt_netherlands.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestThread(RequestCode.GET_NETHERLANDS);
				//requestHandler(HandlerRequestCode.NETHERLANDS_SELECT);
				
			}
		});
		
		
		this.edt_county.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestThread(RequestCode.GET_COUNTY);
				//requestHandler(HandlerRequestCode.COUNTY_SELECT);
				
			}
		});
		
		
		this.edt_schoolId.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				requestThread(RequestCode.GET_SCHOOL);
				//requestHandler(HandlerRequestCode.SCHOOL_SELECT);	
				
			}
		});
		
	}

	
	
	private TextView edt_province;
	
	private TextView edt_netherlands;
	
	private TextView edt_county;
	
	private TextView edt_schoolId;
	
	private void instanceWeiget() {
	
	this.edt_province = (TextView) this.viewContainer.findViewById(R.id.edt_province);
		
	this.edt_netherlands = (TextView) this.viewContainer.findViewById(R.id.edt_netherlands);
	
	this.edt_county = (TextView) this.viewContainer.findViewById(R.id.edt_county);
	
	this.edt_schoolId = (TextView) this.viewContainer.findViewById(R.id.edt_schoolId);
		
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
		
		case CHECK_SCHOOL:
		{	
			
			Check check = new Check();
			
			StatusMessage statusMessage = check.checkSchool(addressId.getSchoolId());
			
			Message msg = new Message();
			
			msg.what = HandlerRequestCode.CHECK_SCHOOL_RESULT.ordinal();
			
			msg.obj = statusMessage;
			
			getHandler().sendMessage(msg);
			
			
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
			
		case GET_SCHOOL:
		{
			
			if(this.addressId.getcId() != null)
			{
							
				try {
					
					SearchSchool searchSchool = new SearchSchool();
					
					this.listDataSet.setSchoolList(searchSchool.getSchool(this.addressId.getcId()));
				
					
					
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
			}
			
			requestHandler(HandlerRequestCode.SCHOOL_SELECT);
			
		}
			break;
			
			
		case GET_NETHERLANDS:
		{
			
			if(this.listDataSet.getNetherlandsList() == null)
			{
							
				try {
					
					SearchSchool searchSchool = new SearchSchool();
					
					this.listDataSet.setNetherlandsList(searchSchool.getNetherlands("18"));
				
					
					
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
			}
			
			requestHandler(HandlerRequestCode.NETHERLANDS_SELECT);
			
		}
		
			break;		
			
			
		case PRAPARE_ADD_DATA:
		{
				

			
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
	
	
	/*检查学校的归属*/
	if(msg.what == HandlerRequestCode.CHECK_SCHOOL_RESULT.ordinal())
	{
		
		StatusMessage sm = (StatusMessage) msg.obj;
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("检查结果");
		
		if(sm != null)
		{	
			
		if(!sm.getStatus().equals("1"))
		{
		
			this.edt_schoolId.setText("请选择学校...");
			
			studentInfo.setSchoolId("");
			
			builder.setMessage(sm.getMessage());
			
			builder.show();
			
		}	
		else
		{
			
			studentInfo.setSchoolId(addressId.getSchoolId());
			
		}
		
		
	}
	else
	{
		
		this.edt_schoolId.setText("请选择学校...");
		
		studentInfo.setSchoolId("");
		
		builder.setMessage("检验接口异常，请重新选择学校");
		
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
				
				edt_county.setText(
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
				
				
				edt_netherlands.setText(
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
	

	/*学校选择*/
	if(HandlerRequestCode.SCHOOL_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("选择学校");
		
		String[] data = ListDataSet.getStringArray(
				    this.listDataSet.getSchoolList());
		
		if(data != null)
		{
				
		MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
		
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
				edt_schoolId.setText(
						listDataSet.getSchoolList().get(which).getSchoolName());
				
				addressId.setSchoolId(
						listDataSet.getSchoolList().get(which).getSchoolId());
								
				//开启线程检查学校的归属人
				requestThread(RequestCode.CHECK_SCHOOL);
				
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
