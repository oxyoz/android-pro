package com.oz.main.activity;

import java.util.Map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Network;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oz.activity.BasicActivity;
import com.oz.config.API;
import com.oz.main.R;
import com.oz.main.dao.FactoryLoginProxy;
import com.oz.main.dao.LoginProxy;
import com.oz.main.data.UserInfo;
import com.oz.tools.net.NetworkUtil;

/**
 * LoginActivityϵͳ��¼����
 * 
 * @author oz
 * @version 1.0, 12/17/2014
 * @since 1.0
 * 
 * */

@SuppressLint("InlinedApi")
public class LoginActivity extends BasicActivity {

	final int ACTIVITY_ID = 0x10000001;

	Map<String, String> mapUserInfo = null;

	private AutoCompleteTextView edtUserName;

	private EditText edtUserPwd;

	private TextView btnLoginIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);
		// �������뷨���Զ�����
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		// ��ʼ�������ؼ�
		init();

	}

	/**
	 * init ��ʼ������
	 * 
	 * */

	private void init() {
		// ʵ���������ؼ�
		initWidget();
		// ���ø����ؼ�������
		updateWidgetAttr();
		// ���ø����ؼ����¼�������
		addWidgetEvent();

	}

	/**
	 * updateWidgetAttr ���Ŀؼ�����
	 * 
	 * */

	private void updateWidgetAttr() {

		{
			ArrayAdapter<String> adapter = adapter();

			if (adapter != null) {

				this.edtUserName.setAdapter(adapter);

			}
		}

		{
			String[] fristUserInfo = getUserInfoFrist();

			if (fristUserInfo != null) {

				this.edtUserName.setText(fristUserInfo[0]);

				this.edtUserPwd.setText(fristUserInfo[1]);
			}
		}

	}

	/**
	 * addWidgetEvent ��ӿؼ��¼�
	 * 
	 * */

	private void addWidgetEvent() {

		btnLoginIn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				userName = edtUserName.getText().toString().trim();

				userPwd = edtUserPwd.getText().toString().trim();

				if (userName != null&&userPwd != null) {

					new Thread(LoginActivity.this).start();

				} else {

					Toast.makeText(LoginActivity.this, "�˺�/��������Ϊ�գ�",
							Toast.LENGTH_LONG).show();

				}

			}
		});

	}

	/**
	 * initWidget ʵ�����ؼ�
	 * 
	 * */

	private void initWidget() {

		btnLoginIn = (TextView) findViewById(R.id.login_btn_login_in);

		edtUserName = (AutoCompleteTextView) findViewById(R.id.login_edt_user_name);

		edtUserPwd = (EditText) findViewById(R.id.login_edt_user_pwd);

	}

	/**
	 * saveUserInfo ʹ��SharedPreferences��ʽ�洢��¼�ɹ����û���userName(�û���)
	 * ���û�userPwd(�û�����)
	 * 
	 * */

	private void saveUserInfo() {

		SharedPreferences sp = getSharedPreferences("login",
				Context.MODE_PRIVATE);

		Editor editor = sp.edit();

		editor.putString(UserInfo.getInstance().getName(), UserInfo
				.getInstance().getPwd());

		editor.commit();

	}

	/**
	 * run �������̴߳����������󣬴˴�ʵ�ֵ�¼
	 * 
	 * */
	String userName;
	String userPwd;

	@Override
	public void run() {

		login();
		//testLogin();
		this.finish();

	}

	void login() {

		LoginProxy login = FactoryLoginProxy.create();

		String result = login.login(userName, userPwd);

		if (LoginProxy.TYPE_ADMIN.equals(result)) {

			this.saveUserInfo();

			Intent intent = new Intent(LoginActivity.this,
					WelcomeActivity.class);

			intent.putExtra("TYPE_USER", LoginProxy.TYPE_ADMIN);

			startActivity(intent);

		} else if (LoginProxy.TYPE_TEACHER.equals(result)) {

			this.saveUserInfo();

			Intent intent = new Intent(LoginActivity.this,
					WelcomeActivity.class);

			intent.putExtra("TYPE_USER", LoginProxy.TYPE_TEACHER);

			startActivity(intent);

		} else {

			Message msg = new Message();

			msg.what = WHAT_LOGIN_FAILURE;

			getHandler().sendMessage(msg);

		}

	}

	void testLogin() {

		Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);

		intent.putExtra("TYPE_USER", LoginProxy.TYPE_TEACHER);

		startActivity(intent);

	}

	/**
	 * WHAT_LOGIN_FAILURE ��¼ʧ��ʱ�����첽����ʱwhat��
	 * 
	 * */
	private final int WHAT_LOGIN_FAILURE = 0x1110;

	@Override
	public void handlerMethod(Message msg) {

		switch (msg.what) {

		case WHAT_LOGIN_FAILURE:

			Toast.makeText(this, "�˺Ż��������", Toast.LENGTH_LONG).show();

			break;

		}

	}

	/**
	 * getUserInfoFrist ��ȡ֮ǰ�ɹ���¼���û��еĵ�һ���û���Ϣ
	 * 
	 * */
	private String[] getUserInfoFrist() {
		SharedPreferences sp = getSharedPreferences("login",
				Context.MODE_PRIVATE);

		Map<String, String> mapUserInfo = (Map<String, String>) sp.getAll();

		String[] fristUserFrist = new String[2];

		for (String key : mapUserInfo.keySet()) {

			fristUserFrist[0] = key;

			break;
		}

		fristUserFrist[1] = mapUserInfo.get(fristUserFrist[0]);

		return fristUserFrist;
	}

	/**
	 * getAllPwdForUserInfo ��ȡ֮ǰ��¼�ɹ��������û�
	 * 
	 * */
	private String[] getAllPwdForUserInfo() {

		SharedPreferences sp = getSharedPreferences("login",
				Context.MODE_PRIVATE);

		this.mapUserInfo = (Map<String, String>) sp.getAll();

		String[] data = new String[mapUserInfo.keySet().size()];

		int index = 0;

		for (String str : mapUserInfo.keySet()) {

			data[index] = str;

			index++;

		}

		return data;
	}

	/**
	 * adapter ����AutoCompleteTextView�ؼ�������
	 * 
	 * */
	private ArrayAdapter<String> adapter() {

		String[] data = getAllPwdForUserInfo();

		return new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == R.id.login_menu_set_ip) {

			updateIP();

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * updateIP �����Ի������IP
	 * 
	 * */
	private void updateIP() {

		AlertDialog.Builder builder = new Builder(this);

		builder.setTitle("����IP");

		View view = getLayoutInflater().inflate(R.layout.login_dialog_set_ip,
				null);

		TextView prompt = (TextView) view.findViewById(R.id.txt_showIP);

		final EditText ip = (EditText) view.findViewById(R.id.edt_ip);

		prompt.setText("��ǰIP:"
				+ API.getBasic_Url().replace("http://", "")
						.replace(":8080/EnrollSystem/", ""));

		builder.setView(view);

		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {

				if (ip.getText().toString() != null
						&& !ip.getText().toString().equals("")) {

					API.setBasic_Url(ip.getText().toString());

				}
			}
		});

		builder.setNegativeButton("ȡ��", null);

		builder.show();

	}

}
