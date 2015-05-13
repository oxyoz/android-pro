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
		//��Ϊ�������
		studentInfo.setFlag("1");
		//����ʡ
		studentInfo.setProvince("18");		
		//AddressId��Ϊ��
		AddressId.getInstance().setAddressIdEmpty();
		//��ʼ�������ؼ�
		init();
	}
	
	
	
	private void init()
	{		
		//ʵ���������ؼ�����
		instanceWeiget();
		//���ø����ؼ����¼�������
		setWeigetEvent();	
		//���ÿؼ���ʾ������
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




	//���߳��������������
	private  RequestCode requestCode = RequestCode.ACQUIESCE;
		
	//���ݼ���
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
	
	
	
	//���������߳�
	private void requestThread(RequestCode requestCode)
	{
		
		//�����������
		this.requestCode = requestCode;
		//�����������߳�
		new Thread(this).start();
		
		
	}
	
	
	
	//�����첽����
	private void requestHandler(HandlerRequestCode handlerRequestCode)
	{
		
		Message msg = new Message();
		//�����������
		msg.what = handlerRequestCode.ordinal();
		//�����첽��Ϣ
		getHandler().sendMessage(msg);
		

	}
	
	
	
	//AddressId����ʡid������id������id��ѧУid
	private AddressId addressId = AddressId.getInstance();
		
	//UI���·���
	@Override
	public void handlerMethod(Message msg) {

	/*Ĭ�ϴ���*/
	if(HandlerRequestCode.ACQUIESCE.ordinal()==msg.what)
	{
		
		
		
		return;
	}
	
	
	/*���ѧУ�Ĺ���*/
	if(msg.what == HandlerRequestCode.CHECK_SCHOOL_RESULT.ordinal())
	{
		
		StatusMessage sm = (StatusMessage) msg.obj;
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("�����");
		
		if(sm != null)
		{	
			
		if(!sm.getStatus().equals("1"))
		{
		
			this.edt_schoolId.setText("��ѡ��ѧУ...");
			
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
		
		this.edt_schoolId.setText("��ѡ��ѧУ...");
		
		studentInfo.setSchoolId("");
		
		builder.setMessage("����ӿ��쳣��������ѡ��ѧУ");
		
		builder.show();
		
	}	
			
 }
	
	/*����ѡ��*/
	if(HandlerRequestCode.COUNTY_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ�����");
		
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
	
	
	/*����ѡ��*/
	if(HandlerRequestCode.NETHERLANDS_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ�����");
		
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
	

	/*ѧУѡ��*/
	if(HandlerRequestCode.SCHOOL_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ��ѧУ");
		
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
								
				//�����̼߳��ѧУ�Ĺ�����
				requestThread(RequestCode.CHECK_SCHOOL);
				
			}
		} );
		
		}
		
		builder.show();
		
		
		
		
		return;
	}
	
		
	
	/*���ÿؼ�����*/
	if(HandlerRequestCode.SET_WEIGET_DATA.ordinal()==msg.what)
	{
	
		
		
		
		return;
	}
	
	
	}

	
	
}
