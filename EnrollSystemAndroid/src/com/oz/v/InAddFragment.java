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
		//��Ϊʡ��Ĭ�����
		studentInfo.setFlag("0");
		//���ñ�ʾ
		studentInfo.setIdNumber("ʡ��");
		//�����Ա�
		studentInfo.setSex("��");
		//���ñ���
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\tʡ�����");
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		//ListDataSet��Ϊ��
		ListDataSet.getInstance().setDataSetEmpty();
		//����Ĭ��ѧУ����Ƭ
		replaceFragment(new AcquiescentSchoolFragment());
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
	
	/*רҵѡ��*/
	if(HandlerRequestCode.MAJOR_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ��רҵ");
		
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
		
	
	/*���ÿؼ�����*/
	if(HandlerRequestCode.SET_WEIGET_DATA.ordinal()==msg.what)
	{
	
		return;
	}
	
	
	}

	
	/*�����¼�*/
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
		//���ô����¼�
		viewContainer.setOnTouchListener(this);		
		studentInfoView.getEdt_examinees().setOnTouchListener(this);
		studentInfoView.getEdt_id().setOnTouchListener(this);
		studentInfoView.getEdt_major().setOnTouchListener(this);
		studentInfoView.getEdt_stuName().setOnTouchListener(this);
		studentInfoView.getEdt_tel().setOnTouchListener(this);
		studentInfoView.getEdt_volunteer().setOnTouchListener(this);
		studentInfoView.getSchool_type().setOnTouchListener(this);
		studentInfoView.getSex().setOnTouchListener(this);
		
		/*���ý����¼�*/
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
					
					studentInfo.setSex("��");
					
					break;
					
				case R.id.woman:
					
					studentInfo.setSex("Ů");
					
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
