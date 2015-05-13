package com.main.v;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.main.m.StatusMessage;
import com.mian.c.MenuStringArray;
import com.oz.c.AddStudentInfo;
import com.oz.c.SubmitStudentInfo;
import com.oz.m.StudentInfo;
import com.oz.v.AddedStudentInfoFragment;
import com.oz.v.BasicFragment;
import com.oz.v.InAddFragment;
import com.oz.v.OutAddFragment;
import com.oz.v.UserInfoFragment;

public class TeacherMenuActivity extends BasicActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private DrawerLayout mDrawerLayout;
	
	private View mFragmentContainerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// 去掉信息栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.activity_teacher_menu);
		// 设置标题栏的颜色
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(
						R.color.loginBgColor));
		// 设置FrameLayout碎片
		replaceFragment(new UserInfoFragment());

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		mFragmentContainerView = findViewById(R.id.navigation_drawer);
		
		mNavigationDrawerFragment.setUp(mFragmentContainerView,mDrawerLayout);
		
		
	}

	
	
	/*返回键事件监听器*/
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch(keyCode)
		{
		
		case KeyEvent.KEYCODE_BACK:
			
			if(!mDrawerLayout.isDrawerOpen(mFragmentContainerView))
			{
				
				AlertDialog.Builder builder = new Builder(this);
				
				builder.setTitle("提示");
				
				builder.setMessage("\n\t\t\t\t\t\t\t\t\t确认退出系统吗？\n\t");
				
				builder.setPositiveButton("确认", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						System.exit(0);
						
						System.gc();
						
					}
				});
				
				builder.setNeutralButton("取消" , null);
				
				builder.show();
			}	
			else
			{
				
				mDrawerLayout.closeDrawer(mFragmentContainerView);
				
			}
			
			
			break;
		
		}
			
		
		return false;	
	};
	
	
	// 菜单分组
	String[] group = MenuStringArray.groupTeacher;
	// 分组菜单子菜单
	String[][] child = MenuStringArray.childTeacher;

	
	public ExpandableListAdapter getAdapter(){
		
		ExpandableListAdapter adapter = new ExpandableListAdapter(this,group,child);	
		return adapter;
	}
	
	
	
	@Override
	public void onNavigationDrawerItemSelected(int groupPosition) {

		switch (groupPosition) {

		case -1:

			replaceFragment(new UserInfoFragment());

			break;

		case 0:

			replaceFragment(new AddedStudentInfoFragment());

			break;

		case 1:

			/////////////////

			break;

		case 2:

			////////////////

			break;

		case 3:

			////////////////

			break;

		}

	}

	
	@Override
	public void onNavigationDrawerChildItemSelected(int groupPosition,
			int childPosition) {

		switch (groupPosition) {

		case 0:
			switch (childPosition) {

			case 0:

				replaceFragment(new InAddFragment());
				
				break;

			case 1:

				replaceFragment(new OutAddFragment());
				
				break;
			}

			break;

		case 1:

			break;

		case 2:

			break;

		case 3:

			break;
		}

	}

	private void replaceFragment(BasicFragment fragment) {

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.commit();

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.submit, menu);
				
		return true;
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		
		if (id == R.id.menu_submit) {

			new Thread(this).start();
			Log.i("debug", "debug_0");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void run() {

		StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();
		
		if(studentInfo != null)
		{
			
			if(studentInfo.getIdCard() == null)
			{		
				studentInfo.setIdCard("");			
			}
			
			if(studentInfo.getTel() == null)
			{						
				studentInfo.setTel("");			
			}
						
			if(studentInfo.getExamineeNumber() != null&&
			   studentInfo.getExamineeNumber().length() == 14&&
			   studentInfo.getStuName() != null&&
			   studentInfo.getAttendProfessional() != null&&
			   studentInfo.getVoluntarily() != null)
			{
				
				if(studentInfo.getFlag().equals("0")&&
				   studentInfo.getSchoolId() != null)
				{
					
					AddStudentInfo addInfo = new AddStudentInfo(studentInfo);
					
					Message msg = new Message();
					
					msg.obj = addInfo.postAddStudentInfo();
					
					getHandler().sendMessage(msg);
					
					return;
				}
					
				if(studentInfo.getFlag().equals("1")&&
				   studentInfo.getSchoolId() != null&&
				   studentInfo.getNetherlands() != null&&
				   studentInfo.getCounty() != null)
				{
				
					AddStudentInfo addInfo = new AddStudentInfo(studentInfo);
	
					Message msg = new Message();
					
					msg.obj = addInfo.postAddStudentInfo();
					
					getHandler().sendMessage(msg);

					
					return;
				}

				if(studentInfo.getFlag().equals("2")&&
						   studentInfo.getSchoolName() != null&&
						   studentInfo.getNetherlands() != null&&
						   studentInfo.getCounty() != null&&
						   studentInfo.getProvince() != null)
				{

					AddStudentInfo addInfo = new AddStudentInfo(studentInfo);

					Message msg = new Message();
					
					msg.obj = addInfo.postAddOutsize();
					
					getHandler().sendMessage(msg);
				
					return;
				}	
				
			}	
									
		}
		
		
		
	}
	
	@Override
	public void handlerMethod(Message msg) {
		Log.i("debug", "debug_5");
		StatusMessage statusMessage = (StatusMessage) msg.obj;
		Log.i("debug", "debug_6");
		AlertDialog.Builder builder = new Builder(this);
		
		builder.setTitle("提示");
		Log.i("debug", "debug_7");
		if(statusMessage != null)
		{
			Log.i("debug", "debug_7_1_0");
			builder.setMessage(statusMessage.getMessage());
			Log.i("debug", "debug_7_1_1");
			builder.setPositiveButton("确认", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					replaceFragment(new AddedStudentInfoFragment());
					
					StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();
					//间所有学生信息置空
					studentInfo.setEmptyAllProperty();
					
				}
			});
			
		}
		else
		{
			Log.i("debug", "debug_7_2_0");
			builder.setMessage("提交失败");
			Log.i("debug", "debug_7_2_1");
			builder.setPositiveButton("确认", null);
		}
			Log.i("debug", "debug_8");
		builder.show();
		
	}
	
}
