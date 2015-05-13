package com.zzy.v;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.main.v.R;
import com.oz.v.BasicFragment;
import com.zzy.c.ResetPassword;
import com.zzy.c.UserInfoData;

public class ResetPasswordFragment extends BasicFragment {

	private View viewContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		viewContainer = inflater.inflate(R.layout.fragment_search, container,
				false);

		return viewContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		getActivity().getActionBar().setTitle("密码重置");
		// 初始化各个控件
		init();
	}

	private void init() {
		// 实例化各个控件对象
		instanceWeiget();
		// 设置各个控件的事件监听器
		setWeigetEvent();
		// 设置控件显示的数据
		setWeigetData();

	}

	private void setWeigetData() {

		edt_ky.setText("用户名...");

	}

	private void setWeigetEvent() {

		edt_ky.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					if (edt_ky.getText().toString().equals("用户名...")) {

						edt_ky.setText("");
						
					}

				}
				else
				{
					if (edt_ky.getText().toString().equals("")) {

						edt_ky.setText("用户名...");
						
					}
					
				}

			}
		});

		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new Thread(ResetPasswordFragment.this).start();

			}
		});

	}

	private EditText edt_ky;

	private TextView btn_search;

	private void instanceWeiget() {

		edt_ky = (EditText) viewContainer.findViewById(R.id.edt_key);

		btn_search = (TextView) viewContainer.findViewById(R.id.btn_search);

	}

	@Override
	public void run() {

		ResetPassword rp = new ResetPassword();

		UserInfoData
				.setUser(rp.findSingleUserInfo(edt_ky.getText().toString()));

		requestHandler(0);

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

		if (UserInfoData.getUser() != null) {

			replaceFragment(new SearchContentFragment());

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
