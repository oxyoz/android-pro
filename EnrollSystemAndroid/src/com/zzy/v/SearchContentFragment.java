package com.zzy.v;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.main.m.StatusMessage;
import com.main.v.R;
import com.oz.v.BasicFragment;
import com.zzy.c.ResetPassword;
import com.zzy.c.UserInfoData;

public class SearchContentFragment extends BasicFragment {

	private View viewContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		viewContainer = inflater.inflate(R.layout.fragment_search_content, container,
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

		this.txt_name.setText("������" + UserInfoData.getUser().getUserName());

		this.txt_password.setText("���룺" + UserInfoData.getUser().getPassword());

		this.txt_property.setText("ְҵ���ʣ�"
				+ UserInfoData.getUser().getUserProperty());

	}

	private void setWeigetEvent() {

		btn_resetPwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				UserInfoData.getUser().setPassword("888888");
				
				new Thread(SearchContentFragment.this).start();

			}
		});

	}

	private TextView txt_name;

	private TextView txt_password;

	private TextView txt_property;

	private TextView btn_resetPwd;

	private void instanceWeiget() {

		txt_name = (TextView) viewContainer.findViewById(R.id.txt_name);

		txt_password = (TextView) viewContainer.findViewById(R.id.txt_password);

		txt_property = (TextView) viewContainer.findViewById(R.id.txt_property);

		btn_resetPwd = (TextView) viewContainer.findViewById(R.id.btn_resetPwd);

	}

	@Override
	public void run() {

		if (UserInfoData.getUser() != null) {

			ResetPassword rp = new ResetPassword();

			StatusMessage sm = rp.resetPassword(UserInfoData.getUser()
					.getUserId());

			requestHandler(0, sm);

		}

	}

	private void requestHandler(int what) {

		Message msg = new Message();

		msg.what = what;

		getHandler().sendMessage(msg);

	}

	private void requestHandler(int what, Object obj) {

		Message msg = new Message();

		msg.what = what;

		msg.obj = obj;

		getHandler().sendMessage(msg);

	}

	@Override
	public void handlerMethod(Message msg) {

		if (msg.obj != null) {

			StatusMessage sm = (StatusMessage) msg.obj;

			if (sm.getStatus().equals("1")) {
				
				replaceFragment(new SearchContentFragment());
				
				Toast.makeText(getActivity(), sm.getMessage(), Toast.LENGTH_LONG).show();
				
			}
			else
			{
				
				Toast.makeText(getActivity(), sm.getMessage(), Toast.LENGTH_LONG).show();
				
			}
		}
	}

	private void replaceFragment(BasicFragment fragment) {

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.shwo_search_content, fragment);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.commit();

	}

}
