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
		//��ΪĬ�����
		studentInfo.setFlag("0");
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
			
	
	/*ѧУѡ��*/
	if(HandlerRequestCode.SCHOOL_SELECT.ordinal()==msg.what)
	{
	
		AlertDialog.Builder builder = new Builder(getActivity());
		
		builder.setTitle("ѡ��ѧУ");
		
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
	
	
	/*���ÿؼ�����*/
	if(HandlerRequestCode.SET_WEIGET_DATA.ordinal()==msg.what)
	{
	
		
		
		
		return;
	}
	
	
	}

	
	
}
