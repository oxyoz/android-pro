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
		//���ñ���
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\t�û���Ϣ");
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
		
		
	}



	private void instanceWeiget() {
		
		
		
	}



	private ActionBar getActionBar()
	{
		return getActivity().getActionBar();
	}
	
}
