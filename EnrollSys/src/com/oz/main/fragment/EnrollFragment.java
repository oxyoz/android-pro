package com.oz.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oz.custom.CircleImageView;
import com.oz.enroll.fragment.StudentInfoFragment;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class EnrollFragment extends BasicFragment {

	private View mContainer;

	private HolderView mHolderView;

	@Override
	public void actionReceiver(Context context, Intent intent) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater.inflate(R.layout.fragment_enroll, container,
				false);

		return this.mContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

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

		mHolderView = new HolderView(
				mContainer.findViewById(R.id.fragment_enroll_menu_layout_item_0),
				mContainer.findViewById(R.id.fragment_enroll_menu_layout_item_1),
				mContainer.findViewById(R.id.fragment_enroll_menu_layout_item_2),
				mContainer.findViewById(R.id.fragment_enroll_menu_layout_item_3),
				mContainer.findViewById(R.id.fragment_enroll_img_head),
				mContainer.findViewById(R.id.fragment_enroll_txt_head)
				);

	}

	private void updateWidgetAttr() {
	
		

	}

	
	private void addWidgetEvent() {
		
		mHolderView.txtvItem0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				
			}
		});
		
		mHolderView.txtvItem1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("runtime","txtvItem1..." );
				replaceFragment(R.id.welcome_panel_content, new StudentInfoFragment());
				
			}
		});
		
		mHolderView.txtvItem2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		mHolderView.txtvItem3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
	}

	
	
	

	@Override
	public void onDestroy() {

		super.onDestroy();
		
		this.mContainer = null;
		
		this.mHolderView = null;
		
		System.gc();
		
	}

	@SuppressWarnings("unused")
	private final class HolderView {

		CircleImageView imgvHead;

		TextView txtvHead;

		TextView txtvItem0;

		TextView txtvItem1;

		TextView txtvItem2;

		TextView txtvItem3;

		public HolderView() {
		}

		public HolderView(View... aview) {

			for (View view : aview) {
				switch (view.getId()) {

				case R.id.fragment_enroll_menu_layout_item_3:

					txtvItem3 = (TextView) view;
					
					break;

				case R.id.fragment_enroll_menu_layout_item_2:

					txtvItem2 = (TextView) view;
					
					break;
				case R.id.fragment_enroll_menu_layout_item_1:
					
					txtvItem1 = (TextView) view;
					
					break;
				case R.id.fragment_enroll_menu_layout_item_0:
					
					txtvItem0 = (TextView) view;
					
					break;

				case R.id.fragment_enroll_img_head:

					imgvHead = (CircleImageView) view;

					break;
				case R.id.fragment_enroll_txt_head:

					txtvHead = (TextView) view;

					break;

				}

			}

		}

	}

}
