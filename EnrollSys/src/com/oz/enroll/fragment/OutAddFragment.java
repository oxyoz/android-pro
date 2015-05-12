package com.oz.enroll.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.oz.bean.StatusMessage;
import com.oz.enroll.adapter.ItemAdapter;
import com.oz.enroll.bean.County;
import com.oz.enroll.bean.Entity;
import com.oz.enroll.bean.Major;
import com.oz.enroll.bean.Netherlands;
import com.oz.enroll.bean.Privnce;
import com.oz.enroll.bean.StudentInfo;
import com.oz.enroll.bean.Volunteer;
import com.oz.enroll.config.Action;
import com.oz.enroll.config.ActionCode;
import com.oz.enroll.data.AddressId;
import com.oz.enroll.data.CheckResult;
import com.oz.enroll.data.ListDataSet;
import com.oz.enroll.data.SubmitResult;
import com.oz.enroll.data.SubmitStudentInfo;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class OutAddFragment extends BasicFragment {

	private View mContainer;

	private HolderView mHolderView;

	int what = 0;

	private StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();

	AddressId ids = AddressId.getInstance();

	@Override
	public void actionReceiver(Context context, Intent intent) {

		this.what = intent.getIntExtra("what", 0);

		switch (this.what) {

		case ActionCode.OutAdd.SUBMIT_STUDENT_INFO: {

			if (SubmitResult.getSubmitResult().getStatus().equals("1")) {

				ListDataSet.getInstance().setItAll(null);

				replaceFragment(R.id.welcome_panel_content,
						new StudentInfoFragment());

			} else {

				DialogPropmt dp = new DialogPropmt(getActivity());

				dp.builderPrompt(SubmitResult.getSubmitResult().getStatus());

			}

		}
			break;

		case ActionCode.OutAdd.CHECK_EXAMINEES_RESULT: {

			StatusMessage sm = CheckResult.getCheckResult();

			if (sm != null) {

				if (sm.getStatus().equals("0")) {

					mHolderView.edtExaminees.setError(sm.getMessage());

				}

			}

		}
			break;

		case ActionCode.OutAdd.CHECK_SCHOOL_RESULT: {

			StatusMessage sm = CheckResult.getCheckResult();

			if (sm != null) {

				if (sm.getStatus().equals("0")) {

					DialogPropmt dp = new DialogPropmt(getActivity());

					dp.builderPrompt(sm.getMessage());

				}

			}

		}
			break;

		case ActionCode.OutAdd.PROVINCE_SELECT: {

			List<Privnce> list = ListDataSet.getInstance().getPrivnceList();

			if (list != null) {
				
				DialogPropmt<Privnce> items = new DialogPropmt<Privnce>(
						getActivity(), this.mHolderView.txtvProvince, list,
						DialogPropmt.INT_P);

				items.builderListDialog("省");

			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_OUTADD,
						ActionCode.OutAdd.PROVINCE_SELECT);

			}

		}
			break;

		case ActionCode.OutAdd.NETHERLANDS_SELECT: {

			List<Netherlands> list = ListDataSet.getInstance()
					.getNetherlandsList();

			if (list != null) {
				DialogPropmt<Netherlands> items = new DialogPropmt<Netherlands>(
						getActivity(), this.mHolderView.txtvNetherlands, list,
						DialogPropmt.INT_N);

				items.builderListDialog("地区");

			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_OUTADD,
						ActionCode.OutAdd.NETHERLANDS_SELECT);

			}

		}
			break;

		case ActionCode.OutAdd.COUNTY_SELECT: {

			List<County> list = ListDataSet.getInstance().getCountyList();

			if (list != null) {
				DialogPropmt<County> items = new DialogPropmt<County>(
						getActivity(), this.mHolderView.txtvCounty, list,
						DialogPropmt.INT_C);

				items.builderListDialog("市县");

			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_OUTADD,
						ActionCode.OutAdd.COUNTY_SELECT);

			}

		}
			break;

		case ActionCode.OutAdd.MAJOR_SELECT: {

			List<Major> list = ListDataSet.getInstance().getOutMajorList();

			if (list != null) {

				DialogPropmt<Major> items = new DialogPropmt<Major>(
						getActivity(), this.mHolderView.txtvMajor, list,
						DialogPropmt.INT_MAJOR);

				items.builderListDialog("专业");

			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_OUTADD,
						ActionCode.OutAdd.MAJOR_SELECT);

			}

		}
			break;

		case ActionCode.OutAdd.PREPARE: {

			if (ListDataSet.getInstance().getPropertyList() != null
					&& ListDataSet.getInstance().getOutMajorList() != null) {

				this.mHolderView.txtvProperty.setText(ListDataSet.getInstance()
						.getPropertyList().get(0).getMessage());

				this.mHolderView.txtvMajor.setText(ListDataSet.getInstance()
						.getOutMajorList().get(0).getMajorName());

				if(ListDataSet.getInstance()
						.getPropertyList().get(0).getMessage().equals("单招"))
				{
					
					this.mHolderView.txtvVolunteer.setText(null);
					
					this.mHolderView.txtvVolunteer.setHint("志愿不可选");
					
					this.mHolderView.txtvVolunteer.setEnabled(false);
						
				}
				else{
					
				this.mHolderView.txtvVolunteer.setText(ListDataSet.getInstance()
						.getVolunteerList().get(0).getVolunteer());

				}
				
				
			} else {

				sendActionReceiver(Action.receiver.RECEIVER_ENROLL_OUTADD,
						ActionCode.OutAdd.PREPARE);

			}

		}
			break;
		}

	}

	
	public void replaceFragment(int containerViewId, BasicFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();

		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fragmentTransaction.replace(containerViewId, fragment);

		fragmentTransaction.commit();

	}
	
	private class DialogPropmt<E extends Entity> {

		final static int INT_N = 0x0;
		final static int INT_C = 0x1;
		final static int INT_P = 0x2;
		final static int INT_MAJOR = 0x3;
		final static int INT_VOL = 0x4;
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

					if (changeType == DialogPropmt.INT_MAJOR) {

						studentInfo.setAttendProfessional(ListDataSet
								.getInstance().getOutMajorList().get(which)
								.getMajorName());

					}

					if (changeType == DialogPropmt.INT_VOL) {

						studentInfo.setVoluntarily(ListDataSet.getInstance()
								.getVolunteerList().get(which).getVolunteer());

					}

					if (changeType == DialogPropmt.INT_C) {

						String id = ListDataSet.getInstance()
								.getCountyList().get(which).getCid();
						
						studentInfo.setCounty(id);

						ids.setcId(id);
						
					}

					if (changeType == DialogPropmt.INT_P) {

						String id = ListDataSet.getInstance()
								.getPrivnceList().get(which).getPid();
						
						studentInfo.setProvince(id);
						
						ids.setpId(id);
						
						mHolderView.txtvNetherlands.setEnabled(true);
						
						mHolderView.txtvCounty.setText(null);
						
						mHolderView.txtvCounty.setEnabled(false);						
						
						mHolderView.txtvNetherlands.setText("请选择地区……");
						
					}

					if (changeType == DialogPropmt.INT_N) {

						String id = ListDataSet.getInstance()
								.getNetherlandsList().get(which).getNid();
						
						studentInfo.setNetherlands(id );

						ids.setnId(id);

						mHolderView.txtvCounty.setEnabled(true);
						
						mHolderView.txtvCounty.setText("请选择市/县……");
					
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

	private void sendActionReceiver(String action, int what, String checkContent) {

		Intent intent = new Intent(action);

		intent.putExtra("what", what);

		intent.putExtra("check", checkContent);

		this.getActivity().sendBroadcast(intent);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater.inflate(
				R.layout.fragment_add_student_info_fragment_out_add, container,
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
		initStudentInfo();
		// 实例化各个控件
		initWidget();
		// 设置各个控件的数据
		updateWidgetAttr();
		// 设置各个控件的事件监听器
		addWidgetEvent();

	}

	private void initStudentInfo() {

		// 设置省外添加
		studentInfo.setFlag("2");
		// 设置标示
		studentInfo.setIdNumber("省外");
		// 设置性别
		studentInfo.setSex("男");

		ids.setcId("1");

		ids.setpId("1");

		ids.setnId("1");

	}

	private void initWidget() {

		mHolderView = new HolderView(
				this.mContainer.findViewById(R.id.out_edt_examinees),
				this.mContainer.findViewById(R.id.out_edt_stuName),
				this.mContainer.findViewById(R.id.out_edt_id),
				this.mContainer.findViewById(R.id.out_edt_tel),
				this.mContainer.findViewById(R.id.out_txt_property),
				this.mContainer.findViewById(R.id.out_rdg_sex),
				this.mContainer.findViewById(R.id.out_txt_major),
				this.mContainer.findViewById(R.id.out_txt_volunteer),
				this.mContainer.findViewById(R.id.out_school_edt_schoolName),
				this.mContainer.findViewById(R.id.out_school_txt_county),
				this.mContainer.findViewById(R.id.out_school_txt_netherlands),
				this.mContainer.findViewById(R.id.out_school_txt_province),
				this.mContainer.findViewById(R.id.out_txt_reset),
				this.mContainer.findViewById(R.id.out_txt_submit));

	}

	private void updateWidgetAttr() {

		sendActionReceiver(Action.receiver.RECEIVER_ENROLL_OUTADD,
				ActionCode.OutAdd.PREPARE);
		
		this.mHolderView.txtvProvince.setText("请选择省……");
		
		this.mHolderView.txtvCounty.setText(null);
		
		this.mHolderView.txtvCounty.setEnabled(false);
		
		this.mHolderView.txtvNetherlands.setText(null);
		
		this.mHolderView.txtvNetherlands.setEnabled(false);
		
	}

	private void addWidgetEvent() {

		this.mHolderView.btnSubmit
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						String strExaminees = mHolderView.edtExaminees
								.getText().toString().trim();

						String strStuName = mHolderView.edtStuName.getText()
								.toString().trim();

						String strSchoolName = mHolderView.edtSchoolName
								.getText().toString().trim();

						if (strExaminees == null || strExaminees.equals("")
								|| strExaminees.length() != 14) {

							DialogPropmt dp = new DialogPropmt(getActivity());

							dp.builderPrompt("考生号输入不正确！");

						} else if (strExaminees == null
								|| strStuName.equals("")
								|| strStuName.length() < 2) {

							DialogPropmt dp = new DialogPropmt(getActivity());

							dp.builderPrompt("姓名输入不正确！");

						} else if (strSchoolName == null
								|| strSchoolName.equals("")
								|| strStuName.length() < 2) {

							DialogPropmt dp = new DialogPropmt(getActivity());

							dp.builderPrompt("请正确填写学校！");

						} else {

							studentInfo.setExamineeNumber(strExaminees);

							studentInfo.setStuName(strStuName);

							studentInfo.setSchoolName(strSchoolName);

							sendActionReceiver(
									Action.receiver.RECEIVER_ENROLL_OUTADD,
									ActionCode.OutAdd.SUBMIT_STUDENT_INFO);

						}

					}
				});

		this.mHolderView.rdgSex
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.out_rd_man:

							studentInfo.setSex("男");

							break;

						case R.id.out_rd_woman:

							studentInfo.setSex("女");

							break;
						}

					}
				});

		this.mHolderView.edtExaminees
				.setOnFocusChangeListener(new View.OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {

						if (!hasFocus) {

							EditText view = (EditText) v;

							String content = view.getText().toString().trim();

							if (!hasFocus) {
								if (content != null || !content.equals("")) {

									if (content.length() == 14) {

										sendActionReceiver(
												Action.receiver.RECEIVER_ENROLL_OUTADD,
												ActionCode.OutAdd.CHECK_EXAMINEES_RESULT,
												content);

									} else {

										view.setError("内容必须为14位数字");

									}

								} else {

									view.setError("不能为空！");

								}
							}
						}

					}
				});

		this.mHolderView.txtvMajor
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						List<Major> list = ListDataSet.getInstance()
								.getOutMajorList();

						if (list != null) {

							DialogPropmt<Major> items = new DialogPropmt<Major>(
									getActivity(), (TextView) v, list,
									DialogPropmt.INT_MAJOR);

							items.builderListDialog("专业");

						} else {

							sendActionReceiver(
									Action.receiver.RECEIVER_ENROLL_OUTADD,
									ActionCode.OutAdd.MAJOR_SELECT);

						}

					}

				});

		this.mHolderView.txtvVolunteer
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						DialogPropmt<Volunteer> items = new DialogPropmt<Volunteer>(
								getActivity(), (TextView) v, ListDataSet
										.getInstance().getVolunteerList(),
								DialogPropmt.INT_VOL);

						items.builderListDialog("志愿");

					}
				});

		
		this.mHolderView.txtvProvince
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						List<Privnce> list = ListDataSet.getInstance()
								.getPrivnceList();

						if (list != null) {
							DialogPropmt<Privnce> items = new DialogPropmt<Privnce>(
									getActivity(), (TextView) v, list,
									DialogPropmt.INT_P);

							items.builderListDialog("省");

							mHolderView.txtvNetherlands.setText("请选择地区……");
							
							mHolderView.txtvCounty.setText(null);
							
							mHolderView.txtvCounty.setEnabled(false);
							
						} else {

							sendActionReceiver(
									Action.receiver.RECEIVER_ENROLL_OUTADD,
									ActionCode.OutAdd.PROVINCE_SELECT);

						}

					}
				});

		this.mHolderView.txtvNetherlands
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						sendActionReceiver(
								Action.receiver.RECEIVER_ENROLL_OUTADD,
								ActionCode.OutAdd.NETHERLANDS_SELECT);

					}
				});

		this.mHolderView.txtvCounty
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						sendActionReceiver(
								Action.receiver.RECEIVER_ENROLL_OUTADD,
								ActionCode.OutAdd.COUNTY_SELECT);

					}
				});

	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	private class HolderView {

		TextView txtvProperty;

		TextView txtvMajor;

		TextView txtvVolunteer;

		TextView btnReset;

		TextView btnSubmit;

		EditText edtExaminees;

		EditText edtStuName;

		EditText edtId;

		EditText edtTel;

		RadioGroup rdgSex;

		TextView txtvCounty;

		EditText edtSchoolName;

		TextView txtvNetherlands;

		TextView txtvProvince;

		public HolderView() {
		}

		public HolderView(View... vs) {

			for (View v : vs) {

				switch (v.getId()) {

				case R.id.out_edt_examinees:

					this.edtExaminees = (EditText) v;

					break;

				case R.id.out_edt_stuName:

					this.edtStuName = (EditText) v;

					break;

				case R.id.out_edt_id:

					this.edtId = (EditText) v;

					break;

				case R.id.out_edt_tel:

					this.edtTel = (EditText) v;

					break;

				case R.id.out_txt_property:

					this.txtvProperty = (TextView) v;

					break;

				case R.id.out_rdg_sex:

					this.rdgSex = (RadioGroup) v;

					break;

				case R.id.out_txt_major:

					this.txtvMajor = (TextView) v;

					break;

				case R.id.out_txt_volunteer:

					this.txtvVolunteer = (TextView) v;

					break;

				case R.id.out_school_edt_schoolName:

					this.edtSchoolName = (EditText) v;

					break;

				case R.id.out_school_txt_county:

					this.txtvCounty = (TextView) v;

					break;
				case R.id.out_school_txt_netherlands:

					this.txtvNetherlands = (TextView) v;

					break;
				case R.id.out_school_txt_province:

					this.txtvProvince = (TextView) v;

					break;
				case R.id.out_txt_reset:

					this.btnReset = (TextView) v;

					break;

				case R.id.out_txt_submit:

					this.btnSubmit = (TextView) v;

					break;

				}

			}

		}

	}

}
