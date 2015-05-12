package com.oz.enroll.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oz.bean.StatusMessage;
import com.oz.custom.DialogPropmt;
import com.oz.enroll.adapter.ItemAdapter;
import com.oz.enroll.bean.County;
import com.oz.enroll.bean.Entity;
import com.oz.enroll.bean.Netherlands;
import com.oz.enroll.bean.School;
import com.oz.enroll.bean.StudentInfo;
import com.oz.enroll.config.Action;
import com.oz.enroll.config.ActionCode;
import com.oz.enroll.data.AddressId;
import com.oz.enroll.data.CheckResult;
import com.oz.enroll.data.ListDataSet;
import com.oz.enroll.data.SubmitStudentInfo;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class InAddOtherFragment extends BasicFragment {

	private View mContainer;

	private HolderView mHolderView;

	StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();

	AddressId ids = AddressId.getInstance();

	@Override
	public void actionReceiver(Context context, Intent intent) {

		int what = intent.getIntExtra("what", 0);

		switch (what) {
		
		case ActionCode.InAddOther.CHECK_SCHOOL_RESULT:{
		
			StatusMessage sm = CheckResult.getCheckResult();

			if (sm != null) {

				if (sm.getStatus().equals("0")) {

					DialogPropmt dp = new DialogPropmt(getActivity());

					dp.builderPrompt(sm.getMessage());

					mHolderView.txtvSchoolId.setText("请选择学校……");
					
					studentInfo.setSchoolId(null);

					ids.setSchoolId(null);
					
				}

			}
			
	
		}break;
		
		
		case ActionCode.InAddOther.COUNTY_SELECT: {

			List<County> list = ListDataSet.getInstance().getCountyList();

			if (list != null) {

				DialogPropmt<County> items = new DialogPropmt<County>(
						getActivity(), mHolderView.txtvCounty, list,
						DialogPropmt.INT_C);

				items.builderListDialog("市/县");

			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_INADDOTHER,
						ActionCode.InAddOther.COUNTY_SELECT);

			}

		}
			break;

		case ActionCode.InAddOther.NETHERLANDS_SELECT: {

			List<Netherlands> list = ListDataSet.getInstance()
					.getNetherlandsList();

			if (list != null) {

				DialogPropmt<Netherlands> items = new DialogPropmt<Netherlands>(
						getActivity(), mHolderView.txtvNetherlands, list,
						DialogPropmt.INT_N);

				items.builderListDialog("地区");

			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_INADDOTHER,
						ActionCode.InAddOther.NETHERLANDS_SELECT);

			}
		}
			break;

		case ActionCode.InAddOther.SCHOOL_SELECT: {

			List<School> list = ListDataSet.getInstance().getSchoolList();

			if (list != null) {

				DialogPropmt<School> items = new DialogPropmt<School>(
						getActivity(), mHolderView.txtvSchoolId, list,
						DialogPropmt.INT_S);

				items.builderListDialog("学校");

			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_INADDOTHER,
						ActionCode.InAddOther.SCHOOL_SELECT);

			}

		}
			break;

		}

	}

	private class DialogPropmt<E extends Entity> {

		final static int INT_N = 0x0;
		final static int INT_C = 0x1;
		final static int INT_S = 0x2;
		Context context;

		TextView textView;

		List<E> list;

		int changeType;

		public DialogPropmt(Context context) {

			this.context = context;

		}

		public DialogPropmt(Context context, TextView textView, List<E> list,
				int changeType) {

			this(context);

			this.textView = textView;

			this.list = list;

			this.changeType = changeType;

		}

		public void builderPrompt(String msg) {

			AlertDialog.Builder builder = new Builder(this.context);

			builder.setTitle("提示");

			builder.setMessage(msg);

			builder.setPositiveButton("OK", null);

			builder.show();

		}

		public void builderListDialog(String title) {

			AlertDialog.Builder builder = new Builder(this.context);

			builder.setTitle(title);

			ItemAdapter<E> adapter = new ItemAdapter<E>(context, this.list);

			builder.setAdapter(adapter, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					textView.setText(list.get(which).getItem());

					if (changeType == DialogPropmt.INT_S) {

						String id = ListDataSet.getInstance()
								.getSchoolList().get(which).getSchoolId();
						
						studentInfo.setSchoolId(id);

						ids.setSchoolId(id);
						
						sendActionReceiver(Action.receiver.RECEIVER_ENROLL_INADDOTHER,
								ActionCode.InAddOther.CHECK_SCHOOL_RESULT,ids.getSchoolId());

					}

					if (changeType == DialogPropmt.INT_C) {

						String id = ListDataSet.getInstance()
								.getCountyList().get(which).getCid();
						
						studentInfo.setCounty(id);

						ids.setcId(id);

						mHolderView.txtvSchoolId.setEnabled(true);
						
						mHolderView.txtvSchoolId.setText("请选择学校……");
						
					}

					if (changeType == DialogPropmt.INT_N) {

						String id = ListDataSet.getInstance()
								.getNetherlandsList().get(which).getNid();
						
						studentInfo.setNetherlands(id);

						ids.setnId(id);
						
						mHolderView.txtvCounty.setEnabled(true);

						mHolderView.txtvCounty.setText("请选择市/县……");
						
						mHolderView.txtvSchoolId.setText(null);
						
						mHolderView.txtvSchoolId.setEnabled(false);
						
					}

				}

			});

			builder.show();
		}

	}

	private void sendActionReceiver(String action, int what) {

		Intent intent = new Intent(action);

		intent.putExtra("what", what);

		this.getActivity().sendBroadcast(intent);

	}

	private void sendActionReceiver(String action, int what,String checkContent) {

		Intent intent = new Intent(action);

		intent.putExtra("what", what);

		intent.putExtra("check", checkContent);
		
		this.getActivity().sendBroadcast(intent);

	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater
				.inflate(
						R.layout.fragment_add_student_info_fragment_in_add_other_school,
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

		initStudentInfo();
		// 实例化各个控件
		initWidget();
		// 设置各个控件的数据
		updateWidgetAttr();
		// 设置各个控件的事件监听器
		addWidgetEvent();

	}

	private void initStudentInfo() {
		
		Toast.makeText(getActivity(), "InAdd_initStudentInfo", Toast.LENGTH_LONG).show();
		
		studentInfo.setProvince("18");

		ids.setpId("18");

		ids.setnId("185");

		ids.setcId("1235");

		ids.setSchoolId("926");

	}

	private void initWidget() {

		mHolderView = new HolderView(
				this.mContainer.findViewById(R.id.other_school_txt_county),
				this.mContainer.findViewById(R.id.other_school_txt_schoolId),
				this.mContainer.findViewById(R.id.other_school_txt_netherlands),
				this.mContainer.findViewById(R.id.other_school_txt_province));

	}

	private void updateWidgetAttr() {

		this.mHolderView.txtvNetherlands.setText("请选择地区……");
		
		this.mHolderView.txtvCounty.setText(null);
		
		this.mHolderView.txtvCounty.setEnabled(false);
		
		this.mHolderView.txtvSchoolId.setText(null);
		
		this.mHolderView.txtvSchoolId.setEnabled(false);
		
	}

	private void addWidgetEvent() {

		mHolderView.txtvNetherlands
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						sendActionReceiver(
								Action.receiver.RECEIVER_ENROLL_INADDOTHER,
								ActionCode.InAddOther.NETHERLANDS_SELECT);

					}
				});

		mHolderView.txtvCounty.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_INADDOTHER,
						ActionCode.InAddOther.COUNTY_SELECT);

			}
		});

		mHolderView.txtvSchoolId.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_INADDOTHER,
						ActionCode.InAddOther.SCHOOL_SELECT);

			}
		});

	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	private class HolderView {

		TextView txtvCounty;

		TextView txtvSchoolId;

		TextView txtvNetherlands;

		TextView txtvProvince;

		public HolderView() {
		}

		public HolderView(View... vs) {

			for (View v : vs) {

				switch (v.getId()) {

				case R.id.other_school_txt_county:

					txtvCounty = (TextView) v;

					break;

				case R.id.other_school_txt_schoolId:

					txtvSchoolId = (TextView) v;

					break;

				case R.id.other_school_txt_netherlands:

					txtvNetherlands = (TextView) v;

					break;

				case R.id.other_school_txt_province:

					txtvProvince = (TextView) v;

					break;

				}

			}

		}

	}

}
