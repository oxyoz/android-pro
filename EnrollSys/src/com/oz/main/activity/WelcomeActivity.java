package com.oz.main.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.oz.activity.BasicActivity;
import com.oz.custom.CircleImageView;
import com.oz.custom.ResideLayout;
import com.oz.enroll.service.HandlerNetworkService;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;
import com.oz.main.adapter.ResideMenuAdapter;
import com.oz.main.dao.LoginProxy;
import com.oz.main.data.UserInfo;
import com.oz.main.fragment.AdminFragment;
import com.oz.main.fragment.EnrollFragment;

public class WelcomeActivity extends BasicActivity {

	final int ACTIVITY_ID = 0x10000002;

	private ResideLayout mResideLayout;
	
	private LinearLayout mPanelContent;

	private HolderView mHolderView;

	private TextView btnOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.welcome);

		init();
	
	}

	/**
	 * init 初始化方法
	 * 
	 * */

	private void init() {
		// 实例化各个控件
		initWidget();
		// 设置各个控件的数据
		updateWidgetAttr();
		// 设置各个控件的事件监听器
		addWidgetEvent();

	}

	private void initWidget() {

		mResideLayout = (ResideLayout) findViewById(R.id.welcome_layout_reside);
		
		mPanelContent = (LinearLayout) this.mResideLayout.getChildAt(1);

		btnOpen = (TextView) findViewById(R.id.welcome_txt_drawer);
		
		mHolderView = new HolderView(
				this.findViewById(R.id.welcome_menu_img_head),
				this.findViewById(R.id.welcome_menu_list),
				this.findViewById(R.id.welcome_menu_txt_head));

	}

	
	private void updateWidgetAttr() {
		
		ResideMenuAdapter adapter = new ResideMenuAdapter(this);
		
		this.mHolderView.listvMenu.setAdapter(adapter);
		
		this.mHolderView.txtvHead.setText(UserInfo.getInstance().getName());

		if(LoginProxy.TYPE_ADMIN.equals(getIntent().getStringExtra("TYPE_USER")))
		{
			
			replaceFragment(mPanelContent.getId(), new AdminFragment());
			
		}
		else if(LoginProxy.TYPE_TEACHER.equals(getIntent().getStringExtra("TYPE_USER")))
		{
			
			replaceFragment(mPanelContent.getId(), new EnrollFragment());
			
		}
			
	}

	
	
	private void addWidgetEvent() {

		btnOpen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(mResideLayout.isOpen())
				{
					
					mResideLayout.closePane();
					
				}
				else
				{
					
					mResideLayout.openPane();
					
				}
				
			}
		});
				
		
		this.mHolderView.listvMenu.setOnItemClickListener(new OnItemClickListener() {

			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				
			}
		});
		
		
	}

	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch(keyCode)
		{
		
		case KeyEvent.KEYCODE_BACK:
			
			if(!mResideLayout.isOpen()&&this.getSupportFragmentManager().getBackStackEntryCount()== 0)
			{
				
				AlertDialog.Builder builder = new Builder(this);
				
				builder.setTitle("提示");
				
				builder.setMessage("\n\t\t\t\t\t\t\t\t\t确认退出系统吗？\n\t");
				
				builder.setPositiveButton("确认", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						finish();
						
					}
				});
				
				builder.setNeutralButton("取消" , null);
				
				builder.show();
			}	
			else
			{
				
				mResideLayout.closePane();
				
			}
			
			
			break;
		
		}
		
		return super.onKeyDown(keyCode, event);
	}

		
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		this.stopService(new Intent(this, HandlerNetworkService.class));
		
		System.gc();
		
	}
	
	
	@SuppressWarnings("unused")
	private final class HolderView {

		CircleImageView imgvHead;

		TextView txtvHead;

		ListView listvMenu;

		public HolderView() {
		}

		public HolderView(View... aview) {

			for (View view : aview) {
				switch (view.getId()) {

				case R.id.welcome_menu_img_head:

					this.imgvHead = (CircleImageView) view;

					break;

				case R.id.welcome_menu_txt_head:

					this.txtvHead = (TextView) view;

					break;
				case R.id.welcome_menu_list:

					this.listvMenu = (ListView) view;

					break;

				}

			}

		}

	}

}
