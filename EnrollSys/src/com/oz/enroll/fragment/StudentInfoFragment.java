package com.oz.enroll.fragment;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;
import com.oz.enroll.adapter.StuInfoAdapter;
import com.oz.enroll.bean.ItAll;
import com.oz.enroll.config.Action;
import com.oz.enroll.config.ActionCode;
import com.oz.enroll.data.ListDataSet;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class StudentInfoFragment extends BasicFragment {

	private View mContainer;

	private HolderView mHolderView;
	
	StuInfoAdapter adapter;

	int what = 0;

	Animation fltFloating;

	@Override
	public void actionReceiver(Context context, Intent intent) {

		this.what = intent.getIntExtra("what", 0);

		Log.i("UIreceiver action", intent.getAction() + "---" + this.what);

		if (this.what == ActionCode.StudentInfo.SELECT_ALL_STUDENT_INFO) {
			
			List<ItAll> list = ListDataSet.getInstance().getItAll();

			if (list != null) {

				adapter = new StuInfoAdapter(context, list);

				mHolderView.listvShow.setAdapter(this.adapter);

				mHolderView.btnAdd.setEnabled(true);
				
			}

		}

	}

	
	private void sendActionReceiver(String action, int what) {
		
		Intent intent = new Intent(action);

		intent.putExtra("what", what);

		this.getActivity().sendBroadcast(intent);
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater.inflate(R.layout.fragment_student_info,
				container, false);

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
				mContainer.findViewById(R.id.fragment_student_info_list_show),
				mContainer.findViewById(R.id.fragment_student_info_btn_add));

	}

	private void updateWidgetAttr() {
		// 设置listview数据
		updateListViewAdapter();

	}

	private void updateListViewAdapter() {

		List<ItAll> list = ListDataSet.getInstance().getItAll();

		if (list != null) {			
			
			adapter = new StuInfoAdapter(this.getActivity(), list);

			mHolderView.listvShow.setAdapter(this.adapter);

			mHolderView.btnAdd.setEnabled(true);
			
		} else {

			sendActionReceiver(Action.receiver.RECEIVER_ENROLL_STUDENTINFO,
					ActionCode.StudentInfo.SELECT_ALL_STUDENT_INFO);

		}

	}

	private void addWidgetEvent() {

		mHolderView.btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				fltFloating = AnimationUtils.loadAnimation(getActivity(),
						R.anim.cycle_click_anim);

				v.startAnimation(fltFloating);

				fltFloating.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {

						sendActionReceiver(
								Action.receiver.RECEIVER_ENROLL_INADD,
								ActionCode.InAdd.PREPARE);

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {

						replaceFragment(R.id.welcome_panel_content,
								new AddStudentInfoFragment());
						
					}
				});

			}
		});

	}

	
	
	
	@Override
	public void onDestroy() {

		super.onDestroy();

		this.mContainer = null;

		this.mHolderView = null;

	}

	
	private class HolderView {

		TextView btnAdd;

		ListView listvShow;

		public HolderView() {
		}

		public HolderView(View... vs) {

			for (View v : vs) {

				switch (v.getId()) {

				case R.id.fragment_student_info_list_show:

					listvShow = (ListView) v;

					break;

				case R.id.fragment_student_info_btn_add:

					btnAdd = (TextView) v;

					break;

				}

			}

		}

	}

}
