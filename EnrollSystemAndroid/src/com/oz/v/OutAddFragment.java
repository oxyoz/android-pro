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
		//����ʡ�����
		studentInfo.setFlag("2");
		//���ñ�ʾ
		studentInfo.setIdNumber("ʡ��");
		//�����Ա�
		studentInfo.setSex("��");
		//���ñ���
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\tʡ�����");
		//ListDataSet��Ϊ��
		ListDataSet.getInstance().setDataSetEmpty();
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
		
		this.requestThread(RequestCode.PRAPARE_ADD_DATA);
		
	}


	/*�����¼�*/
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
		//���ô����¼�
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

		
		//���ý����¼�
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
					
					studentInfo.setSex("��");
					
					break;
					
				case R.id.woman:
					
					studentInfo.setSex("Ů");
					
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



	//���߳��������������
	private  RequestCode requestCode = RequestCode.ACQUIESCE;
		
	//���ݼ���
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
			// 1Ϊ���㿼���Ÿ�ʽ
			msg.arg1 = 1;
			
			getHandler().sendMessage(msg);
			
			}
			else
			{
				
				Message msg = new Message();
				
				msg.what = HandlerRequestCode.CHECK_EXAMINEES_RESULT.ordinal();
				// 0Ϊ�����㿼���Ÿ�ʽ
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
	
	
	/*�����ż��*/
	if(HandlerRequestCode.CHECK_EXAMINEES_RESULT.ordinal()==msg.what)
	{
		
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("�����");
		
		builder.setPositiveButton("ȷ��", null);
		
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
		
			builder.setMessage("�����ű���Ϊ14λ����");
			
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
	
	/*רҵѡ��*/
	if(HandlerRequestCode.MAJOR_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ��רҵ");
		
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
	
	/*ʡѡ��*/
	if(HandlerRequestCode.PROVINCE_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ��ʡ");
		
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
	
	
	/*־Ըѡ��*/
	if(HandlerRequestCode.VOLUNTARILY_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ��־Ը");
		
		if(!listDataSet.getPropertyList().get(0).getMessage().equals("����"))
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
		
	
	/*���ÿؼ�����*/
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
