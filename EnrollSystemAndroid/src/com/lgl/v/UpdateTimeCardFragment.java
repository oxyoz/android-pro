package com.lgl.v;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.main.v.R;
import com.oz.v.BasicFragment;

public class UpdateTimeCardFragment extends BasicFragment {

	
	private View viewContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		viewContainer = inflater.inflate(R.layout.fragment_update_time_card, container,
				false);

		return viewContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		// ��ʼ�������ؼ�
		init();
	}

	private void init() {
		// ʵ���������ؼ�����
		instanceWeiget();
		// ���ø����ؼ����¼�������
		setWeigetEvent();
		// ���ÿؼ���ʾ������
		setWeigetData();
	}

	
	private void setWeigetData() {
		// TODO Auto-generated method stub
		
	}

	private void setWeigetEvent() {
		// TODO Auto-generated method stub
		
	}

	private void instanceWeiget() {
		// TODO Auto-generated method stub
		
	}

	
	
}
